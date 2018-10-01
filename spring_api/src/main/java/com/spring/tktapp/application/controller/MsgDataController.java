package com.spring.tktapp.application.controller;

import com.spring.tktapp.application.dao.MsgDataDaoImpl;
import com.spring.tktapp.application.entity.MsgData;
import com.spring.tktapp.domain.repositories.MsgDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MsgDataController {


    private MsgDataRepository msgDataRepository;

//    @PersistenceContext
    EntityManager entityManager;

    MsgDataDaoImpl msgDataDao;

    @Autowired
    public void setMyDataRepository(MsgDataRepository msgDataRepository){
        this.msgDataRepository=msgDataRepository;
    }

    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public ModelAndView msg(ModelAndView mav){
        mav.setViewName("showMsgData");
        mav.addObject("title", "Sample");
        mav.addObject("msg", "MsgDataのサンプルです。");
        MsgData msgData = new MsgData();
        mav.addObject("formModel", msgData);
        List<MsgData> list = (List<MsgData>) msgDataDao.getAll();
        mav.addObject("datalist", list);
        return mav;
    }

    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    public ModelAndView msgform(@Valid @ModelAttribute MsgData msgdata, Errors result, ModelAndView mav){
        if(result.hasErrors()){
            mav.setViewName("showMsgData");
            mav.addObject("title", "Sample [ERROR]");
            mav.addObject("msg", "値をチェックしてください");
            return mav;
        } else {
            msgDataRepository.saveAndFlush(msgdata);
            return new ModelAndView("redirect:/msg");
        }
    }

    @PostConstruct
    public void init(){
        System.out.println("ok");
        msgDataDao = new MsgDataDaoImpl(entityManager);
    }
}
