package net.zaolunzi.framework;

/**
 * @Author: SelectBook
 * @Date: 2022/5/19 22:09
 */
public interface ConfigConstant {
    
    String CONFIG_FILE = "maque.properties";
    
    String JDBC_DRIVER = "maque.framework.jdbc.driver";
    String JDBC_URL = "maque.framework.jdbc.url";
    String JDBC_USERNAME = "maque.framework.jdbc.username";
    String JDBC_PASSWORD = "maque.framework.jdbc.password";
    
    String APP_BASE_PACKAGE = "maque.framework.app.base_package";
    String APP_JSP_PATH = "maque.framework.app.jsp_path";
    String APP_ASSET_PATH = "maque.framework.app.asset_path";
    String APP_UPLOAD_LIMIT = "maque.framework.app.upload_limit";
}
