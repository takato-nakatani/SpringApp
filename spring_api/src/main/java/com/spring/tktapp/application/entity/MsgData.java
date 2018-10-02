package com.spring.tktapp.application.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "msgdata")
@Getter
@Setter
public class MsgData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column
    private String title;

    @Column(nullable = false)
    @NotEmpty
    private String message;

    @ManyToOne
    private MyData mydata;

    public MsgData(){
        super();
        mydata = new MyData();
    }
}
