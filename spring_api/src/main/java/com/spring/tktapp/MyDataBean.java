package com.spring.tktapp;

import com.spring.tktapp.application.entity.MyData;
import com.spring.tktapp.domain.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MyDataBean {

    @Autowired
    MyDataRepository myDataRepository;

    public String getTableTagById(Long id){
        Optional<MyData> opt = myDataRepository.findById(id);
        MyData data = opt.get();
        String result = "<tr><td>" + data.getName()
                + "</td><td>" + data.getMail()
                + "</td><td>" + data.getAge()
                + "</td><td>" + data.getMemo()
                + "</td></tr>";

        return result;
    }
}
