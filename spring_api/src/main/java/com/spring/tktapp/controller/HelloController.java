package com.spring.tktapp.controller;

import com.spring.tktapp.dao.MyDataDaoImpl;
import com.spring.tktapp.entity.MyData;
import com.spring.tktapp.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class HelloController {

    private MyDataRepository myDataRepository;

    @Autowired
    public void setMyDataRepository(MyDataRepository myDataRepository){
        this.myDataRepository=myDataRepository;
    }

    @PersistenceContext
    EntityManager entityManager;

    private MyDataDaoImpl dao;

    @PostConstruct
    public void init(){
        dao = new MyDataDaoImpl(entityManager);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav){
        mav.setViewName("index");
        mav.addObject("msg", "this is sample content");
        mav.addObject("formModel", mydata);
        Iterable<MyData> list = myDataRepository.findAllOrderByName();
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

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView mav){
        mav.setViewName("find");
        mav.addObject("title", "find page");
        mav.addObject("msg", "MyDataのサンプルです");
        mav.addObject("value", "");
        Iterable<MyData> list = dao.getAll();
        mav.addObject("datalist", list);
        return mav;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView search(ModelAndView mav, HttpServletRequest request){
        mav.setViewName("find");
        String param = request.getParameter("fstr");
        if(param == ""){
            mav = new ModelAndView("redirect:/find");
        } else {
            mav.addObject("msg", "「" + param + "」の検索結果");
            mav.addObject("value", param);
            List<MyData> list = dao.find(param);
            mav.addObject("datalist", list);
        }
        return mav;
    }
}