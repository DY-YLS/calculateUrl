package com.example.calculate.config.exceptionHandle;

import com.example.calculate.config.ResultConfig.ResultEnum;
import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class UserException extends RuntimeException {
    private Integer code;
    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
