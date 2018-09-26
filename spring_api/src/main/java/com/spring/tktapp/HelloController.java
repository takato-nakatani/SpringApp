package com.spring.tktapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {


    String[] names = {"tuyano", "hanako", "taro", "sachiko", "ichiro"};
    String[] mails = {"syoda@tuuyano.com", "hanako@flower", "taro@yamada", "sachiko@happy", "ichiro@baseball"};

    @RequestMapping("/{num}")
    public String index(@PathVariable int num, Model model){
        int res = 0;
        for(int i = 1;i <= num; i++){
            res += i;
        }
        model.addAttribute("msg", "total" + res);
        return "index";
    }
}