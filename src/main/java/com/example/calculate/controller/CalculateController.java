package com.example.calculate.controller;

import com.example.calculate.config.ResultConfig.Result;
import com.example.calculate.entity.UrlEntity;
import com.example.calculate.service.CalculateService.CalculateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CalculateController {
    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @ApiOperation(value = "calculateUrl", notes = "示例：\t\n 1.{Offset(1-5,1)} \t\n 2.{Or(a|b|c)} \t\n 3.{Date(AddDays,1,yyyy-MM-dd)} \t\n 4.{Random(3,30)}")
    @PostMapping("/calculateUrl")
    public Object calculateUrl(@RequestBody UrlEntity urlEntity) {
        List result = calculateService.calculate(urlEntity);
        return Result.success(result);
    }
}
