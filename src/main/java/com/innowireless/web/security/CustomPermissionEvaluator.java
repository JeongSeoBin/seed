package com.innowireless.web.security;

import com.innowireless.web.util.AuthorizationUtil;
import org.springframework.stereotype.Component;


/**
 * URL 및 method에 대한 access control 판정<br>
 *
 * <br>
 * 관련 링크
 * <br>
 * Method Security (5.8절 참조)<br>
 * https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/html/jc.html <br>
 * <br>
 * Expression-Based Access Control<br>
 * https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/html/el-access.html <br>
 * <br>
 * Spring Expression Language 링크<br>
 * https://docs.spring.io/spring/docs/4.2.3.RELEASE/spring-framework-reference/html/expressions.html
 *
 * @author owner
 */
@Component(value = "permissionEvaluator")
public class CustomPermissionEvaluator {

    public boolean isAdmin() {
        return AuthorizationUtil.hasAdminPermission();
    }
}
