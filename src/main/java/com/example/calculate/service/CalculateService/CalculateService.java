package com.example.calculate.service.CalculateService;


import com.example.calculate.entity.UrlEntity;


import java.util.List;
import java.util.Map;

public interface CalculateService extends GeneralCalculate {
    List calculate(UrlEntity urlEntity);

    List<String> calculateUrl(UrlEntity urlEntity);

    List<Map> calculateBody(UrlEntity urlEntity);

    List<String> calculateFormulaString(String formulaString);

    boolean checkExistFormula(String formulaString);

    default String formulaMatchRegex(String formulaName) {
        return new StringBuilder().append("\\{").append(formulaName).append("\\(.*").toString();
    }

    default String formulaRegexString(String formulaString) {
        return formulaString.replace("{", "\\{")
                .replace("}", "\\}")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("|", "\\|");
    }
}
