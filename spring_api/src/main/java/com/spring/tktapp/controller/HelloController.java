package com.spring.tktapp.controller;

import com.spring.tktapp.entity.MyData;
import com.spring.tktapp.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HelloController {

    private MyDataRepository myDataRepository;

    @Autowired
    public void setMyDataRepository(MyDataRepository myDataRepository){
        this.myDataRepository=myDataRepository;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav){
        mav.setViewName("index");
        mav.addObject("msg", "this is sample content");
        mav.addObject("formModel", mydata);
        Iterable<MyData> list = myDataRepository.findAll();
        mav.addObject("datalist", list);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    //@Transactionalをつけることでデータベースへのアクセスをトランザクション処理にできる。またreadOnly=falseをつけるとインサートやアップデートができるようになる。
    @Transactional(readOnly=false)
    //@ModelAttribute("formModel") MyData mydataとすることでMyDataクラスをインスタンス化してformModelの値を埋め込むことができ、あとはmydataをDBにインサートすればOK。
    public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata, BindingResult result, ModelAndView mav){
        ModelAndView res = null;
        if(!result.hasErrors()){
            myDataRepository.saveAndFlush(mydata);
            res = new ModelAndView("redirect:/");
        } else {
            mav.setViewName("index");
            mav.addObject("msg", "sorry, error is occured...");
            Iterable<MyData> list = myDataRepository.findAll();
            mav.addObject("datalist", list);
            res = mav;
        }
        return res;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mav){
        mav.setViewName("edit");
        mav.addObject("title", "edit mydata.");
        Optional<MyData> data = myDataRepository.findById((long)id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute MyData mydata, ModelAndView mav){
        myDataRepository.saveAndFlush(mydata);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id, ModelAndView mav){
        mav.setViewName("delete");
        mav.addObject("title", "delete mydata");
        Optional<MyData> data = myDataRepository.findById((long)id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView remove(@RequestParam long id, ModelAndView mav){
        myDataRepository.deleteById(id);
        return new ModelAndView("redirect:/");
    }
}