package com.spring.tktapp.domain.service;

import java.io.Serializable;
import java.util.List;

public interface MyDataService <T> extends Serializable {

    public List<T> getAll();

    public T get(int num);

    public List<T> find(String fstr);
}
