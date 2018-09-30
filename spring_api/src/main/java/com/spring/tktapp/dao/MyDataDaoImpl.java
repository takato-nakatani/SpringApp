package com.spring.tktapp.dao;

import com.spring.tktapp.entity.MyData;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        List<MyData> list = null;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        query.select(root).orderBy(builder.asc(root.get("name")));
        list = (List<MyData>)entityManager.createQuery(query).getResultList();
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
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        //SQLを書かなくても、下のqueryの部分でwhereとかを設定することができる。
        query.select(root).where(builder.equal(root.get("name"), fstr));
        list = (List<MyData>)entityManager.createQuery(query).getResultList();
        return list;
    }
}
