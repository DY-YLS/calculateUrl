package com.example.calculate.globalConstant;


import com.example.calculate.service.CalculateService.CalculateFormula.*;

public enum FormulaType {
    Date("Date", new DateFormulaImpl()),
    Offset("Offset", new OffsetFormulaImpl()),
    Random("Random", new RandomFormulaImpl()),
    Or("Or", new OrFormulaImpl());
    private String formulaName;

    public CalculateFormula getCalculateFormula() {
        return calculateFormula;
    }

    private CalculateFormula calculateFormula;

    public String getFormulaName() {
        return formulaName;
    }

    FormulaType(String formulaName, CalculateFormula calculateFormula) {
        this.formulaName = formulaName;
        this.calculateFormula = calculateFormula;
    }
}
