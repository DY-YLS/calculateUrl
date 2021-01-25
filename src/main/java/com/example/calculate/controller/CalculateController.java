package com.example.calculate.controller;

import com.example.calculate.config.ResultConfig.Result;
import com.example.calculate.entity.UrlEntity;
import com.example.calculate.service.CalculateService.CalculateService;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/new")
public class CalculateController {
    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @ApiOperation(value = "calculateUrl", notes = "示例：\t\n 1.{Offset(1-5,1)} \t\n 2.{Or(a|b|c)} \t\n 3.{Date(AddDays,1,yyyy-MM-dd)} \t\n 4.{Random(3,30)}")
    @PostMapping("/calculateUrl")
    public Result calculateUrl(@RequestBody UrlEntity urlEntity) {
        List result = calculateService.calculate(urlEntity);
        return Result.success(result);
    }

    @PostMapping("checkExistFormula")
    public Result checkExistFormula(@RequestBody UrlEntity urlEntity) {
        boolean result = calculateService.checkExistFormula(urlEntity.toString());
        return Result.success(result);
    }


}
