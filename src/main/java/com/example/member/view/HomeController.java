package com.example.member.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/views")
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public ModelAndView home(Model model) {
        // todo: 아래 경로는 resources 의 template 경로 입니다 즉, html 파일 경로랑 맵핑하는 것으로 controller 와 다른 경로 입니다
        return new ModelAndView("/app/home", model.asMap());
    }
}
