package net.zaolunzi.security.exception;

/**
 * 授权异常（当权限无效时抛出）
 *
 * @Author: SelectBook
 * @Date: 2022/5/22 17:48
 */
public class AuthzException extends RuntimeException {

    public AuthzException() {
        super();
    }

    public AuthzException(String message) {
        super(message);
    }

    public AuthzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthzException(Throwable cause) {
        super(cause);
    }
}
