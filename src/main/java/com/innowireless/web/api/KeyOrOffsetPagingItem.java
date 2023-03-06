package com.innowireless.web.api;

import com.innowireless.web.util.MybatisPostgreSQLPaginationInterceptor;
import com.innowireless.web.util.RegxPatterns;
import com.innowireless.web.util.ValidationUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://use-the-index-luke.com/sql/partial-results/fetch-next-page">key</a>
 * 혹은 offset paging query를 만들기 위한 정보.
 * key paging과 offset paging을 뭉쳐 놓은 이유는, 무한 scroll을 지원할 때 둘을 섞어서 사용하기 때문에,
 * 둘 간의 전환을 편하게 하기 위해서이다.
 * offset paging만을 하려면 RowBounds를 사용하면 된다. 단, 그 경우는 order by 구문을 원 SQL 문에 넣어야
 * 한다. 이 class object를 사용하는 경우는 key paging과의 호환을 위해 order by 구문을 이 class 내부에서
 * 생성한다.
 */
public class KeyOrOffsetPagingItem
{
    @Schema(title = "fetch할 줄 수")
    @NotNull
    public Integer count;

    public static class KeyColumn
    {
        public KeyColumn() {}

        public KeyColumn(String name)
        {
            this.name = name;
        }

        public KeyColumn(String name, boolean isDesc)
        {
            this.name = name;
            this.isDesc = isDesc;
        }

        public String name;
        public Boolean isDesc;  // null이거나 false이면 asc.
    }

    @Schema(
        title =
            "페이지 기준 컬럼 정보 목록. order by 순서대로 나열해야 함. " +
            "order by 문을 구성할 때와 key paging 시 where 절 구성에 사용된다.")
    @NotEmpty
    public List<KeyColumn> keyColumns;

    @Schema(title = "offset 페이징을 할 때 지정. 지정하지 않으면 key paging.")
    public Integer offset;

    @Schema(
        title = "key 페이징을 할 때 지정. 데이터를 가져올 방향. 현재 페이지, 다음 페이지, 이전 페이지.",
        type = "string",
        allowableValues = "cur, next, prev")
    public String op;

    @Schema(
        title =
            "key 페이징을 할 때 지정할 수 있다. " +
            "페이지의 기준이 될 키 값. keyColumns 순서에 대응되는 값이어야 한다. " +
            "op가 'cur'일 때는 null이어도 된다. 이때는 첫 페이지로 간주한다.")
    public List<Object> keyValues;

    /**
     * member variable들을 자유롭게 지정할 때 사용한다.
     */
    public KeyOrOffsetPagingItem()
    {
    }

    /**
     * key 값이 하나만 있고, ordering이 asc일 때 사용한다.
     * member variable들의 규약에 따라, offset이 null이 아닌 경우는 op와 keyValue가 사용되지 않는다.
     */
    public KeyOrOffsetPagingItem(int count, String keyColumnName, Integer offset, String op,
                                 Object keyValue)
    {
        this.count = count;
        keyColumns = Collections.singletonList(
            new KeyOrOffsetPagingItem.KeyColumn(keyColumnName));
        this.offset = offset;
        this.op = op;
        if (keyValue != null)
            keyValues = Collections.singletonList(keyValue);
        validate();
    }

    public void validate()
    {
        if (count == null)
            throw new ApiException(ErrorCodes.INVALID_ARGUMENT, "count is null");
        if ((keyColumns == null) || (keyColumns.isEmpty()))
            throw new ApiException(ErrorCodes.INVALID_ARGUMENT, "null or empty keyColumns");
        for (KeyColumn keyColumn : keyColumns)
            if (! keyColumn.name.matches(RegxPatterns.PATTERN_COLUMN_NAME))
                throw new ApiException(ErrorCodes.INVALID_ARGUMENT, "invalid keyColumn.name");
        if (offset == null)
        {
            if (! ValidationUtil.isStrIncludeIn(op, OP_CUR, OP_NEXT, OP_PREV))
                throw new ApiException(ErrorCodes.INVALID_ARGUMENT, "op");
            if (keyValues == null)
            {
                if (! StringUtils.equals(op, OP_CUR))
                    throw new ApiException(ErrorCodes.INVALID_ARGUMENT,
                        "keyValues must not be null");
            }
            else
            {
                for (Object value : keyValues)
                    if (value == null)
                        throw new ApiException(ErrorCodes.INVALID_ARGUMENT,
                            "keyValue must not be null");
            }
        }
    }

    /**
     * query를 실행한 application code에서 이 함수를 call해야 한다.
     * key paging 시 prev일 때는 역순으로 해야 한다.
     * key paging 기법에서 prev 할때는 order by를 반대로 하기 때문.
     * TODO 이것도 interceptor에서 하자.
     */
    public void postprocessRows(List<Map<String, Object>> rows)
    {
        if ((offset == null) && StringUtils.equals(op, OP_PREV))
            Collections.reverse(rows);
    }

    // TODO 현재는 PostgreSQL 전용. DB 종류와 상관 없게 만들자.
    public String buildPaginationSql(String sql)
    {
        if (offset != null)
        {
            String sqlWithOrderBy = sql + buildOrderByClause();
            return MybatisPostgreSQLPaginationInterceptor.buildPaginationSql(sqlWithOrderBy,
                offset, count);
        }
        else
            return buildKeyPaginationSql(sql);
    }

    /**
     * sql을 key paging sql로 바꾼다.
     * 아래와 같이 만든다.
     *
     * select * from
     * (
     *     [mapper.xml 에서 작성한 sql]
     * )
     * where [key paging 방식에 맞는 적절한 값]
     * order by [keyColumns]
     * [fetch limit 구문]
     */
    private String buildKeyPaginationSql(String sql)
    {
        return "select * from (" + sql.trim() + ") t" +
            buildWhereClause() +
            buildOrderByClause() +
            " limit " + count;
    }

    // TODO value를 parameter로 넘겨주게 구현해 보자.
    private String buildWhereClause()
    {
        if (keyValues == null)
            return "";

        String ascOp, descOp;
        switch (op)
        {
        case OP_CUR:  ascOp = " >= "; descOp = " <= "; break;
        case OP_NEXT: ascOp = " > ";  descOp = " < ";  break;
        default:      ascOp = " < ";  descOp = " > ";  break;  // OP_PREV
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" where ");
        for (int i = 0; i < keyColumns.size(); i++)
        {
            if (i > 0)
            {
                sb.append(" or ");
                for (int j = 0; j < i; j++)
                {
                    sb.append(keyColumns.get(j).name)
                        .append("=")
                        .append(getKeyValueStr(keyValues.get(j)))
                        .append(" and ");
                }
            }
            KeyColumn keyColumn = keyColumns.get(i);
            sb.append(keyColumn.name)
                .append(((keyColumn.isDesc != null) && keyColumn.isDesc) ? descOp : ascOp)
                .append(getKeyValueStr(keyValues.get(i)));
        }
        return sb.toString();
    }

    private String getKeyValueStr(Object obj)
    {
        String objStr = obj.toString();
        if (obj instanceof String)
            return "'" + objStr.replaceAll("'", "''") + "'";
        else
            return objStr;
    }

    private String buildOrderByClause()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(" order by ");
        boolean isReverse = (offset == null) && (StringUtils.equals(op, OP_PREV));
        boolean isFirst = true;
        for (KeyColumn keyColumn : keyColumns)
        {
            if (! isFirst)
                sb.append(",");
            else
                isFirst = false;
            sb.append(keyColumn.name);
            if (((keyColumn.isDesc != null) && (keyColumn.isDesc)) ^ isReverse)
                sb.append(" desc");
        }
        return sb.toString();
    }

    public static final String OP_CUR = "cur";
    public static final String OP_NEXT = "next";
    public static final String OP_PREV = "prev";
}
