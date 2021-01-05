package com.example.calculate.service.CalculateService.CalculateFormula;

import com.example.calculate.globalConstant.FormulaType;

import java.util.Arrays;
import java.util.List;

public class OrFormulaImpl implements CalculateFormula {
    @Override
    public List handler(String formula) {
        String[] argsUrl = getFormulaArgsString(formula, FormulaType.Or.getFormulaName()).split("\\|");
        return Arrays.asList(argsUrl);
    }
}
