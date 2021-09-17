package ru.lisiy.springsource;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/start")
    public String startPage(){
        return "start_page";
    }
}
