package zaolunzi.zhangyu.core.config;

/**
 * Bootstrap properties interface.
 * 
 * @Author: SelectBook
 * @Date: 2022/6/17 11:26
 */
public interface BootstrapPropertiesInterface {
    /**
     * Get enable.
     *
     * @return
     */
    default Boolean getEnable() {
        return null;
    }
    
    /**
     * Get username.
     *
     * @return
     */
    default String getUsername() {
        return null;
    }
    
    /**
     * Get password.
     *
     * @return
     */
    default String getPassword() {
        return null;
    }
    
    /**
     * Get namespace.
     *
     * @return
     */
    default String getNamespace() {
        return null;
    }
    
    /**
     * Get item id.
     *
     * @return
     */
    default String getItemId() {
        return null;
    }
    
    /**
     * Get server addr.
     *
     * @return
     */
    default String getServerAddr() {
        return null;
    }
    
    /**
     * Get banner.
     *
     * @return
     */
    default Boolean getBanner() {
        return null;
    }
}
