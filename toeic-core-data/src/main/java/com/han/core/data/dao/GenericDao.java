package com.han.core.data.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by VO VAN NHAN on 5/21/2018.
 */
public interface GenericDao<ID extends Serializable,T> {
    List<T> findAll();

    T update(T entity);

    void save(T entity);

    T findById(ID var1);

    public Object[] findByProperty(HashMap<String,Object> property, String sortExpression, String sortDirection , Integer offset, Integer limit );

     Integer delete(List<ID> ids);
}
