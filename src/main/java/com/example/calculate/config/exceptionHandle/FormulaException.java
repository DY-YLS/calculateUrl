package com.example.calculate.config.exceptionHandle;

import com.example.calculate.config.ResultConfig.ResultEnum;
import lombok.Data;

@Data
public class FormulaException extends RuntimeException {
    private Integer code;

    public FormulaException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public FormulaException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
