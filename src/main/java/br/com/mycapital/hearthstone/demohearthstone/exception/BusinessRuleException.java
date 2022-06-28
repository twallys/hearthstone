package br.com.mycapital.hearthstone.demohearthstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessRuleException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String resourceName;
    private String reason;

    public BusinessRuleException(String resourceName, String reason) {
        super(String.format("%s - Business Rule: '%s'", resourceName, reason));
        this.resourceName = resourceName;
        this.reason = reason;
    }
}
