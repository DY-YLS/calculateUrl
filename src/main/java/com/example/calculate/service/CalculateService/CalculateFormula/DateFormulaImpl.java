package com.example.calculate.service.CalculateService.CalculateFormula;

import com.example.calculate.globalConstant.FormulaType;
import com.example.calculate.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateFormulaImpl implements CalculateFormula {
    private static String defaultDateFormatter = "yyyyMMddHHmmss";

    @Override
    public List handler(String formula) {
        String[] argsUrl = getFormulaArgsString(formula, FormulaType.Date.getFormulaName()).split(",");
        List<String> list = new ArrayList();
        int argsNum = argsUrl.length;
        LocalDateTime now = LocalDateTime.now();
        String formatter = defaultDateFormatter;
        if (argsNum == 1) {
            if (!argsUrl[0].isEmpty()) {
                formatter = argsUrl[0];
            }
        } else if (argsNum == 2) {
            if (checkAddDays(argsUrl[0])) {
                now = now.plusDays(Integer.parseInt(argsUrl[1]));
            }
        } else if (argsNum == 3) {
            if (checkAddDays(argsUrl[0])) {
                now = now.plusDays(Integer.parseInt(argsUrl[1]));
            }
            formatter = argsUrl[2];
        }
        list.add(LocalDateTimeUtil.format(now, formatter));
        return list;
    }

    public boolean checkAddDays(String operation) {
        return "AddDays".equals(operation);
    }
}
