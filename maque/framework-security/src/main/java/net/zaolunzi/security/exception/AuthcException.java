package net.zaolunzi.security.exception;

/**
 * 认证异常（当非法访问时抛出）
 *
 * @Author: SelectBook
 * @Date: 2022/5/22 17:48
 */
public class AuthcException extends Exception {

    public AuthcException() {
        super();
    }

    public AuthcException(String message) {
        super(message);
    }

    public AuthcException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthcException(Throwable cause) {
        super(cause);
    }
}
