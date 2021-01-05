package com.example.calculate.controller;

import com.example.calculate.config.ResultConfig.Result;
import com.example.calculate.entity.UrlEntity;
import com.example.calculate.service.CalculateService.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculateController {
    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/calculateUrl")
    public Object calculateUrl(@RequestBody UrlEntity urlEntity) {
        List result = calculateService.calculate(urlEntity);
        return Result.success(result);
    }
}
