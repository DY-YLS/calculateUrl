package com.example.calculate.service.CalculateService.Impl;

import com.example.calculate.entity.UrlEntity;
import com.example.calculate.globalConstant.FormulaType;
import com.example.calculate.service.CalculateService.CalculateFormula.CalculateFormula;
import com.example.calculate.service.CalculateService.CalculateService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculateServiceImpl implements CalculateService {
    private CalculateFormula calculateFormula;

    @Override
    public List calculate(UrlEntity urlEntity) {
        List<String> urlList = calculateUrl(urlEntity);
        List<Map> bodyList = calculateBody(urlEntity);
        if (bodyList.size() == 0) {
            return urlList;
        }
        List<UrlEntity> urlEntities = new ArrayList<>();
        for (String url : urlList) {
            for (Map body : bodyList) {
                UrlEntity item = new UrlEntity();
                item.setUrl(url);
                item.setBody(body);
                urlEntities.add(item);
            }
        }
        return urlEntities;
    }

    @Override
    public List<String> calculateUrl(UrlEntity urlEntity) {
        String url = urlEntity.getUrl();
        return calculateFormulaString(url);
    }

    @Override
    public List<Map> calculateBody(UrlEntity urlEntity) {
        Map<String, Object> body = urlEntity.getBody();
        return handleMapField(body);
    }

    public List<Map> handleMapField(Map<String, Object> mapField) {
        List<Map> result = new ArrayList<>();
        if (mapField == null) return result;
        result.add(mapField);
        for (String key : mapField.keySet()) {
            Object value = mapField.get(key);
            if (value instanceof String) {
                List<String> formulaResultList = calculateFormulaString((String) value);
                result = refreshBodyList(key, result, formulaResultList);
            } else if (value instanceof Map) {
                List<Map> mapResultList = handleMapField((Map<String, Object>) value);
                result = refreshBodyList(key, result, mapResultList);
            }
        }
        return result;
    }

    public List refreshBodyList(String key, List<Map> oldList, List newList) {
        List<Map> temporaryList = new ArrayList<>();
        for (Object mapResult : newList) {
            for (Map map : oldList) {
                Map<String, Object> newMap = new HashMap<>();
                newMap.putAll(map);
                newMap.put(key, mapResult);
                temporaryList.add(newMap);
            }
        }
        return temporaryList;
    }

    @Override
    public List<String> calculateFormulaString(String formulaString) {
        List<String> urlList = Arrays.asList(formulaString);
        List<String> formulaList = new ArrayList<>();
        while (true) {
            // 查出符合条件的公式
            int start = -1;
            for (FormulaType formulaType : FormulaType.values()) {
                start = formulaString.indexOf(formulaLeftFlag(formulaType.getFormulaName()));
                if (start >= 0) break;
            }
            if (start < 0) {
                break;
            }
            int end = formulaString.indexOf(")}", start);
            String formula = formulaString.substring(start, end + 2);
            formulaList.add(formula);
            formulaString = formulaString.replace(formula, "");
        }
        for (String formula : formulaList) {
            ArrayList<String> objects = new ArrayList<>();
            for (FormulaType formulaType : FormulaType.values()) {
                if (formula.matches(formulaMatchRegex(formulaType.getFormulaName()))) {
                    calculateFormula = formulaType.getCalculateFormula();
                    break;
                }
            }
            List<String> results = calculateFormula.handler(formula);
            for (String url1 : urlList) {
                for (String result : results) {
                    String newUrl = url1.replace(formula, result);
                    objects.add(newUrl);
                }
            }
            urlList = objects;
        }
        return urlList;
    }
}
