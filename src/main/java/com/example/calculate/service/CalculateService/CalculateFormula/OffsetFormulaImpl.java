package com.example.calculate.service.CalculateService.CalculateFormula;

import com.example.calculate.config.exceptionHandle.FormulaException;
import com.example.calculate.globalConstant.FormulaType;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class OffsetFormulaImpl implements CalculateFormula {
    @Override
    public List handler(String formula) {
        String[] argsUrl = getFormulaArgsString(formula, FormulaType.Offset.getFormulaName()).split(",");
        Queue<String> queue = new ConcurrentLinkedDeque();
        Collections.addAll(queue, argsUrl);
        String startToEnd = queue.poll();
        List<String> list = new ArrayList();
        if (startToEnd.matches("\\d+-\\d+")) {
            int start = Integer.parseInt(startToEnd.split("-")[0]);
            int end = Integer.parseInt(startToEnd.split("-")[1]);
            if (end - start <= 0) {
                // 抛出异常，end需要大于start
                throw new FormulaException(-1, "Offset公式第一个参数的区间范围，结束数字必须大于起始数字");
            }
            String stepStr = queue.poll();
            int step = 1;
            if (stepStr != null) {
                step = Integer.parseInt(stepStr);
            }
            String prefix = queue.poll();
            if (prefix == null) {
                prefix = "";
            }
            String needFirst = queue.poll();
            for (int i = start; i <= end; i = i + step) {
                StringBuilder builder = new StringBuilder();
                if (i == 1 && needFirst != null) {
                    if (needFirst.equalsIgnoreCase("N")) {
                    }
                } else {
                    builder.append(prefix).append(i);
                }

                list.add(builder.toString());
            }

        } else {
            // 抛出异常 提示
            throw new FormulaException(-1, "Offset公式第一个参数格式：非负数字-非负数字");
        }

        return list;
    }
}
