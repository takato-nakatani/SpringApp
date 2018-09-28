package com.spring.tktapp.controller;

import com.spring.tktapp.entity.MyData;
import com.spring.tktapp.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    private MyDataRepository myDataRepository;

    @Autowired
    public void setMyDataRepository(MyDataRepository myDataRepository){
        this.myDataRepository=myDataRepository;
    }


    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav){
        mav.setViewName("index");
        mav.addObject("msg", "this is sample content");
        Iterable<MyData> list = myDataRepository.findAll();
        mav.addObject("datalist", list);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    //@Transactionalをつけることでデータベースへのアクセスをトランザクション処理にできる。またreadOnly=falseをつけるとインサートやアップデートができるようになる。
    @Transactional(readOnly=false)
    //@ModelAttribute("formModel") MyData mydataとすることでMyDataクラスをインスタンス化してformModelの値を埋め込むことができ、あとはmydataをDBにインサートすればOK。
    public ModelAndView form(@ModelAttribute("formModel") MyData mydata, ModelAndView mav){
        myDataRepository.saveAndFlush(mydata);
        return new ModelAndView("redirect:/");
    }
}