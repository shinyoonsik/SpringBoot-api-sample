package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delete-method")
public class DeleteController {

    // curl -X DELETE 'http://localhost:8080/api/delete-method/members/10'
    @DeleteMapping("/members/{memberId}")
    public int deleteMember(@PathVariable int memberId) {
        return memberId;
    }

    // curl -X DELETE 'http://localhost:8080/api/delete-method/members?id=3'
    @DeleteMapping("/members")
    public int deleteMember2(@RequestParam int id) {
        return id;
    }
}
