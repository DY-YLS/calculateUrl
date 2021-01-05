package com.example.calculate.service.CalculateService.CalculateFormula;

import com.example.calculate.globalConstant.FormulaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFormulaImpl implements CalculateFormula {
    @Override
    public List handler(String formula) {
        String[] argsUrl = getFormulaArgsString(formula, FormulaType.Random.getFormulaName()).split(",");
        List<String> list = new ArrayList();
        int argsNum = argsUrl.length;
        int min = 0;
        int max = Integer.MAX_VALUE;
        if (argsNum == 1) {
            if (!argsUrl[0].isEmpty()) {
                max = Integer.parseInt(argsUrl[0]);
            }
        } else if (argsNum == 2) {
            min = Integer.parseInt(argsUrl[0]);
            max = Integer.parseInt(argsUrl[1]);
        }
        Random random = new Random();

        int result = min + random.nextInt(max + 1 - min); //生成从min到Max的随机整数[m,n]
        list.add(Integer.toString(result));
        return list;
    }
}
