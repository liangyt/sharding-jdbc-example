package com.test.member.info.controller;

import com.test.member.info.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author liangyongtong
 * @since 2020-03-06
 */
@RestController
@RequestMapping("/member.info/member")
public class MemberController {
    @Autowired
    private MemberServiceImpl memberService;

    @GetMapping("/saveToSelect")
    public Object saveToSelect() {
        memberService.saveToSelect();
        return "成功";
    }

    @GetMapping("/saveToSelectNotransactional")
    public Object saveToSelectNotransactional() {
        memberService.saveToSelectNotransactional();
        return "成功";
    }

    @GetMapping("/selectToSaveToSelect")
    public Object selectToSaveToSelect() {
        memberService.selectToSaveToSelect();
        return "成功";
    }

    @GetMapping("/exception")
    public void exception() {
        memberService.selectToSaveToSelectException();
    }

    @GetMapping("/list")
    public Object list() {
        return memberService.list();
    }

}

