package com.spring.tktapp.application.controller;

import com.spring.tktapp.application.entity.MyData;
import com.spring.tktapp.domain.service.MyDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//このコントローラーにリクエストを投げるとレスポンスがJSON形式で返る
@RestController
public class MyDataRestController {

    private MyDataServiceImpl myDataService;

    @Autowired
    public MyDataRestController(MyDataServiceImpl service){
        myDataService = service;
    }

    @RequestMapping("/rest")
    @SuppressWarnings("unchecked")
    public List<MyData> restAll(){
        return myDataService.getAll();
    }

    @RequestMapping("/rest/{num}")
    public MyData restBy(@PathVariable int num){
        return myDataService.get(num);
    }

}
