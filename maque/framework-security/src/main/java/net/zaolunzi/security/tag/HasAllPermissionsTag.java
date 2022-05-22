package net.zaolunzi.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * 判断当前用户是否拥有其中所有的权限（逗号分隔，表示“与”的关系）
 *
 * @Author: SelectBook
 * @Date: 2022/5/22 17:48
 */
public class HasAllPermissionsTag extends PermissionTag {

    private static final String PERMISSION_NAMES_DELIMITER = ",";

    @Override
    protected boolean showTagBody(String permNames) {
        boolean hasAllPermission = false;
        Subject subject = getSubject();
        if (subject != null) {
            if (subject.isPermittedAll(permNames.split(PERMISSION_NAMES_DELIMITER))) {
                hasAllPermission = true;
            }
        }
        return hasAllPermission;
    }
}
