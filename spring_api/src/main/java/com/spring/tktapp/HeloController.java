package com.spring.tktapp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeloController {


    String[] names = {"tuyano", "hanako", "taro", "sachiko", "ichiro"};
    String[] mails = {"syoda@tuuyano.com", "hanako@flower", "taro@yamada", "sachiko@happy", "ichiro@baseball"};

    @RequestMapping("/{id}")
    public DataObject index(@PathVariable int id){
        return new DataObject(id, names[id], mails[id]);
    }
}

@Getter
@Setter
class DataObject{
    private int id;
    private String name;
    private String value;

    public DataObject(int id, String name, String value){
        super();
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
