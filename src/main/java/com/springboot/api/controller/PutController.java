package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;
import java.util.Map;

@RestController
@RequestMapping("/api/put-method")
public class PutController {

    // 서버에 어떤 값이 들어올지 모르는 경우에는 Map객체를 활용해 값을 받을 수 있다.
    // String으로 반환하면 content-type=text/plain으로서 일반 문자열이 리턴된다
    @PutMapping("/unknown-target")
    public String putUnknownTarget(@RequestBody Map<String, String> requestBody) {
        StringBuilder sb = new StringBuilder();
        requestBody.entrySet().forEach(body -> {
            sb.append(body.getKey() + " : " + body.getValue() + "\n");
        });

        return sb.toString();
    }

    // 객체를 리턴하면 content-type=application/json형식으로 전달된다
    // 이는 @RestController에 지정된 @ResponseBody가 자동으로 리턴되는 데이터를 JSON형식으러 변환해서 전달하기 때문이다
    // 따라서, @RestController가 붙은 클래스는 @ResponseBody를 생략할 수 있다.
    @PutMapping("/member")
    public MemberDto putMemeberDto(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

    @PutMapping("/member2")
    public ResponseEntity<MemberDto> putMemberDto2(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
