package com.example.calculate.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.Map;

@Data
public class UrlEntity {

    // @JsonProperty("Url")  // 序列化和反序列化都使用Url
    @JsonAlias("Url")   // 在反序列化时可以指定多个属性名，不会影响序列化,这里只在接收参数的时候取一个别名，不会影响返回的参数
    private String url;
    private Map<String, Object> body;
}
