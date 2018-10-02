package com.spring.tktapp.application.entity;

import com.spring.tktapp.validation.annotation.Phone;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name="mydata")
//クエリアノテーションを利用することでDaoクラスに直接クエリ文を書かなくてよくなる。
//エンティティクラスにクエリ文を書くため、「このエンティティを操作するのに必要なものはすべてエンティティ自身に用意されている」という状態にすることができる。
@NamedQueries(
    @NamedQuery(
        name="findWithName",
        query="from MyData where name like :fname"
    )
)
@Getter
@Setter
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column(length = 50, nullable = false)
    @NotEmpty
    private String name;

    @Column(length = 200, nullable = true)
    @Email
    private String mail;

    @Column(nullable = true)
    @Min(0)
    @Max(200)
    private Integer age;

    @Column(nullable = true)
    @Phone(onlyNumber = true)
    private String memo;

    @OneToMany
    @Column(nullable = true)
    private List<MsgData> msgdatas;
}
