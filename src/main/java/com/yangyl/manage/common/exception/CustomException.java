package com.yangyl.manage.common.exception;

import com.yangyl.manage.common.dto.Response;
import lombok.Getter;

/**
 * @author Joetao
 * Created at 2018/8/24.
 */
@Getter
public class CustomException extends RuntimeException{
    private Response response;

    public CustomException(Response response) {
        this.response = response;
    }
}