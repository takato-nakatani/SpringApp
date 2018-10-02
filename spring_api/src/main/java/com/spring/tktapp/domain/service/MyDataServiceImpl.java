package com.spring.tktapp.domain.service;

import com.spring.tktapp.application.entity.MyData;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

//サービスクラスを作成するときに付与するアノテーション
@Service
public class MyDataServiceImpl implements MyDataService<MyData>{

    //EntityManagerのBeanを割り当てるためのもの
    @PersistenceContext
    private EntityManager entityManager;

    public MyDataServiceImpl(){
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MyData> getAll(){
        return (List<MyData>)entityManager.createQuery("from MyData").getResultList();
    }

    @Override
    public MyData get(int num){
        return (MyData)entityManager.createQuery("from MyData where id = " + num).getSingleResult();
    }

    @Override
    public List<MyData> find(String fstr){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        query.select(root).where(builder.equal(root.get("name"), fstr));
        List<MyData> list = null;
        list = (List<MyData>)entityManager.createQuery(query).getResultList();
        return list;
    }
}
