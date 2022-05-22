package net.zaolunzi.security.realm;

import net.zaolunzi.framework.helper.DatabaseHelper;
import net.zaolunzi.security.SecurityConfig;
import net.zaolunzi.security.password.Md5CredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;
/**
 * 基于 Smart 的 JDBC Realm（需要提供相关 smart.plugin.security.jdbc.* 配置项）
 *
 * @Author: SelectBook
 * @Date: 2022/5/22 17:48
 */
public class SmartJdbcRealm extends JdbcRealm {

    public SmartJdbcRealm() {
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }
}
