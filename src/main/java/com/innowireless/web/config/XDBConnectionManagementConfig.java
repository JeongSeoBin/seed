package com.innowireless.web.config;

import com.innowireless.web.util.MybatisMapperUtil;
import com.innowireless.web.util.xdb.XDBConnection;
import com.innowireless.web.util.xdb.XDBConnectionManagementService;
import com.innowireless.web.util.xdb.XDBProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class XDBConnectionManagementConfig {

    private final XDBProperties properties;

    /**
     * 프로젝트마다 XDB 정보를 저장하는 방식이 다르므로 변경해준다.
     * @return list
     */
    private List<XDBConnection.XdbConnectionInfo> getList() {
        log.info("Initializing XDB Connection Service");

        /*
        List<Map<String, Object>> rows = xdbMapper.getEnabledXdbs();

        for (Map<String, Object> row : rows) {
            add(((BigDecimal) row.get("XDB_ID")).intValue(),
                (String) row.get("HOST"),
                ((BigDecimal) row.get("PORT")).intValue());
            log.info("add XdbConnection: id = {}, host = {}, port = {}",
                ((BigDecimal) row.get("XDB_ID")).intValue(),
                row.get("HOST"),
                ((BigDecimal) row.get("PORT")).intValue());
        }
        */

        final List<XDBConnection.XdbConnectionInfo> list = new ArrayList<>();

        if (Objects.nonNull(properties.getConfig())) {
            final XDBProperties.Config config = properties.getConfig();
            list.add(new XDBConnection.XdbConnectionInfo(config.getId(), config.getHost(), config.getPort()));
        } else {
            for (final XDBProperties.Config config : properties.getConfigs()) {
                list.add(new XDBConnection.XdbConnectionInfo(config.getId(), config.getHost(), config.getPort()));
            }
        }

        log.info("Initialized XDB Connection Service");

        return list;
    }

    @Bean(destroyMethod = "destroy")
    public XDBConnectionManagementService xdbConnectionManagementService(final MybatisMapperUtil mybatisMapperUtil) {
        return new XDBConnectionManagementService(getList(), mybatisMapperUtil);
    }
}
