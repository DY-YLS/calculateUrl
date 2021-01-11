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
        List<LocalDateTime> dateTimeList = new ArrayList<>();
        String formatter = defaultDateFormatter;
        if (argsNum == 1) {
            if (!argsUrl[0].isEmpty()) {
                formatter = argsUrl[0];
            }
        } else if (argsNum == 2) {
            dateTimeList = refreshDateTimeList(argsUrl, now);
        } else if (argsNum == 3) {
            dateTimeList = refreshDateTimeList(argsUrl, now);
            formatter = argsUrl[2];
        }
        for (LocalDateTime dateTime : dateTimeList) {
            list.add(LocalDateTimeUtil.format(dateTime, formatter));
        }
        return list;
    }

    public List<LocalDateTime> refreshDateTimeList(String[] argsUrl, LocalDateTime now) {
        List<LocalDateTime> result = new ArrayList<>();
        if (checkAddDays(argsUrl[0])) {
            result.add(now.plusDays(Integer.parseInt(argsUrl[1])));
        } else if (checkAfterDays(argsUrl[0])) {
            int days = Integer.parseInt(argsUrl[1]);
            for (int i = 0; i <= days; i++) {
                result.add(now.plusDays(i));
            }
        } else if (checkBeforeDays(argsUrl[0])) {
            int days = Integer.parseInt(argsUrl[1]);
            for (int i = days; i >= 0; i--) {
                result.add(now.minusDays(i));
            }
        }
        return result;
    }

    public boolean checkAddDays(String operation) {
        return "AddDays".equals(operation);
    }

    public boolean checkAfterDays(String operation) {
        return "AfterDays".equals(operation);
    }

    public boolean checkBeforeDays(String operation) {
        return "BeforeDays".equals(operation);
    }
}
