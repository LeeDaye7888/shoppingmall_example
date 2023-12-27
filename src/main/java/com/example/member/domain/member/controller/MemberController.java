package com.example.member.domain.member.controller;

import com.example.member.domain.member.dto.MemberRequest;
import com.example.member.domain.member.dto.MemberResponse;
import com.example.member.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class MemberController {

    private final MemberService memberService;



    // todo: 토큰 필요한 REST API
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/members")
    public MemberResponse 회원_정보_조회(@RequestParam(required = false) Long id, @RequestParam(required = false) String email) {
        return memberService.회원_정보_조회(id, email);
    }

    // todo: @PageableDefault 사용법 알기
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/members/paging")
    public List<MemberResponse> 회원_정보_조회_페이징(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 100) Pageable paging) {
        return memberService.회원_정보_조회_페이징(paging);
    }


    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping("/members/{id}")
    public void 회원_정보_수정(@PathVariable long id, @RequestBody MemberRequest request) {
        memberService.회원_정보_수정(id, request);
    }



}
