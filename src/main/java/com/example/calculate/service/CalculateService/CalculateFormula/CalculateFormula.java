package com.example.calculate.service.CalculateService.CalculateFormula;

import com.example.calculate.service.CalculateService.GeneralCalculate;

import java.util.List;

public interface CalculateFormula extends GeneralCalculate {
    List handler(String formula);

    default String getFormulaArgsString(String formula, String formulaName) {
        String left = formulaLeftFlag(formulaName);
        String right = ")}";
        return formula.replace(left, "").replace(right, "");
    }
}
