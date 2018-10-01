package com.spring.tktapp.application.dao;

import com.spring.tktapp.application.entity.MsgData;

import java.util.List;

public interface MsgDataDao<T> {

    public List<MsgData> getAll();
    public MsgData findById(long id);
}
