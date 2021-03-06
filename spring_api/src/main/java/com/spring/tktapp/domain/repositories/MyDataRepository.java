package com.spring.tktapp.domain.repositories;

import com.spring.tktapp.application.entity.MyData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

    public Optional<MyData> findById(Long id);

    //クエリアノテーションを使うことによってこのメソッドを呼び出す際に、このクエリが走ることになり、クエリ文の設定ができる。
    @Query("SELECT d from MyData d ORDER BY d.name")
    List<MyData> findAllOrderByName();

    //クエリ文にパラメータを設定したいときは以下のようにすればよい
    @Query("SELECT d from MyData d WHERE age > :min AND age < :max")
    public List<MyData> finByAge(@Param("min") int min, @Param("max") int max);

    public Page<MyData> findAll(Pageable pageable);
}