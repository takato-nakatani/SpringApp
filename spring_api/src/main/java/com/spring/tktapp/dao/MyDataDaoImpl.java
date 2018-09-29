package com.spring.tktapp.dao;

import com.spring.tktapp.entity.MyData;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MyDataDaoImpl implements MyDataDao<MyData> {
    private static final long serialVersionUID = 1L;

    private EntityManager entityManager;

    public MyDataDaoImpl(){
        super();
    }

    public MyDataDaoImpl(EntityManager manager){
        this();
        entityManager = manager;
    }

    @Override
    public List<MyData> getAll(){
        Query query = entityManager.createQuery("from MyData");

        @SuppressWarnings("unchecked")
        List<MyData> list = query.getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public MyData findById(long id){
        //getSingleResultを使用しなければ結果が一つでもListで返ってくる。idは主キーで重複が許されないので結果が一つであると予想がつくのでgetSingleResult()を使用
        return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MyData> findByName(String name){
        //nameは重複する可能性があるのでgetResultList()を使用
        return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MyData> find(String fstr){
        List<MyData> list = null;
        String qstr = "from MyData where id = :fstr";
        //setParameterで上のクエリ文の「:fstr」へ値を挿入している１つ目の引数はどの部分に挿入するかで、2つ目の引数は何を挿入するかを指定している。
        Query query = entityManager.createQuery(qstr).setParameter("fstr", Long.parseLong(fstr));
        list = query.getResultList();
        return list;
    }
}
