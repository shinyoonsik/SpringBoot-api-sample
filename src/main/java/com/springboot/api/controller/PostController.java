package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/post-method")
public class PostController {

    // map객체는 요청을 통해 어떤 값이 들어오게 될지 특정하기 어려울 때 주로 사용된다
    // curl -X POST -H "Content-Type: application/json" -d '{"name": "ys", "email": "ys@example.com"}' http://localhost:8080/api/post-method/unknown-target
    @PostMapping("/unknown-target")
    public String postUnknownTarget(@RequestBody Map<String, String> requestBody) {
        StringBuilder sb = new StringBuilder();
        requestBody.entrySet().forEach(body -> {
            sb.append(body.getKey() + " : " + body.getValue() + "\n");
        });

        return sb.toString();
    }

    // MemberDto의 멤버 변수를 요청 메시지의 key와 매핑해 값을 가져온다
    // curl -X POST -H "content-Type:application/json" -d '{"name":"유","email":"yu@naver.com","organization":"nc"}' http://localhost:8080/api/post-method/member
    @PostMapping("/member")
    public String postMemberDto(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

}
