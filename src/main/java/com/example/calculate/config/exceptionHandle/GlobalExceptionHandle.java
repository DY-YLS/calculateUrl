package com.example.calculate.config.exceptionHandle;


import com.example.calculate.config.ResultConfig.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 捕获全局异常
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        e.printStackTrace();
        if (e instanceof UserException) {
            UserException userException = (UserException) e;
            return Result.error(userException.getCode(), userException.getMessage());
        } else if (e instanceof FormulaException) {
            FormulaException formulaException = (FormulaException) e;
            return Result.error(formulaException.getCode(), formulaException.getMessage());
        }

        return Result.error(-1, e.getMessage());
    }
}

