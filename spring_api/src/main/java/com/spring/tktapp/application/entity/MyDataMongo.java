package com.spring.tktapp.application.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

@Getter
public class MyDataMongo {
    @Id
    private String id;

    private String name;

    private String memo;

    private Date date;

    public MyDataMongo(String name, String memo){
        super();
        this.name = name;
        this.memo = memo;
        this.date = new Date();
    }
}
