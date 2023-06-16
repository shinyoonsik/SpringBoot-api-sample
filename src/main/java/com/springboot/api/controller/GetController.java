package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get-method")
public class GetController {

    // TEST: curl -X GET 'http://localhost:8080/api/v1/hello'
    @GetMapping("/hello")
    public String getHello() {
        return "Hellow World!";
    }


    // curl -X GET 'http://localhost:8080/api/v1/path-variable/yoonsik'
    @GetMapping("/path-variable/{var}")
    public String getPathVariable(@PathVariable String var) {
        return var;
    }

    @GetMapping("/path-variable2/{variable}")
    public String getPathVariable2(@PathVariable("variable") String var) {
        return var;
    }

    // curl -X GET 'http://localhost:8080/api/v1/query-parameter?name=sik&email=sik11@daum.net'
    @GetMapping("/query-parameter")
    public String getQueryParameter(@RequestParam String name, @RequestParam String email) {
        return String.format("이름: %s, \n이메일: %s", name, email);
    }

    // curl -X GET 'http://localhost:8080/api/v1/query-parameter2?name=sik&email=sik131@daum.net'
    // 만약 Query String에 어떤 값이 들어올지 모른다면 Map객체를 활용할 수 있다
    @GetMapping("/query-parameter2")
    public String getQueryParamater2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(queryParam -> {
            sb.append(queryParam.getKey() + " : " + queryParam.getValue() + "\n");
        });

        return sb.toString();
    }


}
