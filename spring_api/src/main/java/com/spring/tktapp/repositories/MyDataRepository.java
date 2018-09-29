package com.spring.tktapp.repositories;

import com.spring.tktapp.entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

    public Optional<MyData> findById(Long id);

    //クエリアノテーションを使うことによってこのメソッドを呼び出す際に、このクエリが走ることになり、クエリ文の設定ができる。
    @Query("SELECT d from MyData d ORDER BY d.name")
    List<MyData> findAllOrderByName();
}