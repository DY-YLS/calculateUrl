package com.example.calculate.service.CalculateService;

public interface GeneralCalculate {
    default String formulaLeftFlag(String formulaName) {
        return new StringBuilder().append("{").append(formulaName).append("(").toString();
    }
}
