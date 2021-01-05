package com.example.calculate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*//将拦截器设置为Bean,在拦截器中才能使用@AutoWired注解自动注入其他的类
    @Bean
    LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要拦截登录时打开
//        registry.addInterceptor(loginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login","/doc.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/**");
//


        //"/index.html"前面必须带/
                /*.excludePathPatterns("/assets/**", "/lib/**", "/服务员.png", "/", "/user/login", "/login",
                        "/doc.html", "/webjars/**", "/swagger-resources/**", "/service-worker.js").order(1);*/
        // order 给多个拦截器排序
       /* registry.addInterceptor(webAdminInterceptor())
                .addPathPatterns("/admin_**.html").order(2);
        registry.addInterceptor(webTeacherInterceptor())
                .addPathPatterns("/teacher_**.html").order(2);
        registry.addInterceptor(webStudentInterceptor())
                .addPathPatterns("/student_**.html").order(2);*/
    }
    // cors 跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         *
         方法类	             方法名称	    必填	请求头字段	                     说明
         CorsRegistry	    addMapping	    是	  无, 非Cors属性,属于SpringBoot配置	配置支持跨域的路径
         CorsRegistration	allowedOrigins	是	  Access-Control-Allow-Origin	    配置允许的源
         CorsRegistration	allowedMethods	是	  Access-Control-Allow-Methods	    配置支持跨域请求的方法,如：GET、POST，一次性返回
         CorsRegistration	maxAge	        否	  Access-Control-Max-Age	        配置预检请求的有效时间
         CorsRegistration	allowCredentials	否	Access-Control-Allow-Credentials	配置是否允许发送Cookie, 用于 凭证请求
         CorsRegistration	allowedHeaders	否	Access-Control-Request-Headers	     配置允许的自定义请求头, 用于 预检请求
         CorsRegistration	exposedHeaders	否	Access-Control-Expose-Headers	     配置响应的头信息,在其中可以设置其他的头信息
         */
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
