package com.spring.tktapp.application.dao;

import com.spring.tktapp.application.entity.MsgData;

import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings("rawtypes")
public class MsgDataDaoImpl implements MsgDataDao<MsgDataDao> {

    private EntityManager entityManager;

    public MsgDataDaoImpl(){
        super();
    }

    public MsgDataDaoImpl(EntityManager manager){
        entityManager = manager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MsgData> getAll(){
        return entityManager.createQuery("from MsgData").getResultList();
    }

    @Override
    public MsgData findById(long id){
        return (MsgData)entityManager.createQuery("from MsgData where id =" +id).getSingleResult();
    }
}
