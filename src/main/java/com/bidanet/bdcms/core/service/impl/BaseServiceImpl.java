package com.bidanet.bdcms.core.service.impl;


import com.bidanet.bdcms.core.dao.Dao;
import com.bidanet.bdcms.core.exception.CheckException;
import com.bidanet.bdcms.core.service.Service;
import com.bidanet.bdcms.core.vo.Page;
import com.bidanet.hibernate.lambda.core.LambdaCriteria;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2015/11/3.
 */
public abstract class BaseServiceImpl<T> implements Service<T> {

    @Override
    public T get(Serializable id) {
        if (id == null) {
            return null;
        }
        return (T) getDao().get(id);
    }

    protected abstract Dao getDao();

    @Override
    public LambdaCriteria<T> query(){
        return getDao().criteriaQuery();
    }

    @Override
    public T load(Serializable id) {
        return (T) getDao().load(id);
    }


    @Override
    public void queryEq(T entity, Page<T> page) {
        List list = getDao().findByExampleEq(entity, page.getPageCurrent(), page.getPageSize());
        long count = getDao().countByExampleEq(entity);
        page.setTotal(count);
        page.setList(list);
    }

    @Override
    public List<T> queryEq(T entity) {
        return getDao().findByExampleEq(entity);
//        return null;
    }

    @Override
    public void insertT(T t) {
        getDao().save(t);
    }

    @Override
    public void updateT(T t) {
        getDao().update(t);
    }


    @Override
    public List<T> getList() {
        return getDao().findAll();

    }

    @Override
    public void deleteByIdT(Serializable id) {
        canDelete(id);
        getDao().delete(id);

    }

    @Override
    public void deleteAllT() {

    }

    @Override
    public long count() {
        return getDao().count();
    }

    protected void checkNull(Object obj, String msg) {
        if (obj == null) {
            throw new CheckException(msg);
        }
    }

    protected void checkString(String str, String msg) {
        checkNull(str, msg);
        if (str.trim().isEmpty()) {
            throw new CheckException(msg);
        }
    }

    protected void checkTime(long time1, long time2, String msg) {
        if (time1 > time2) {
            throw new CheckException(msg);
        }
    }

    protected void checkPen(Float pensionPrice, String msg) {
        if (pensionPrice < 0) {
            throw new CheckException(msg);
        }
    }

    protected void errorMsg(String msg) {
        throw new CheckException(msg);
    }


    public void canDelete(Serializable id) {

    }

    @Override
    public boolean has(T t) {
        return getDao().has(t);
    }

    @Override
    public List<T> findByExampleLike(T example) {
        return getDao().findByExampleLike(example);
    }



    @Override
    public List<T> findByExampleEq(T example) {
        return getDao().findByExampleEq(example);
    }

    @Override
    public void getPageByExampleLike(T query, Page<T> page) {
        List<T> list = getDao().findByExampleLike(query, page.getPageCurrent(), page.getPageSize());
        long count = getDao().countByExampleLike(query);
        page.setList(list);
        page.setTotal(count);
    }

    @Override
    public void getPageByExampleEq(T query, Page<T> page) {
        List<T> list = getDao().findByExampleEq(query, page.getPageCurrent(), page.getPageSize());
        long count = getDao().countByExampleEq(query);
        page.setList(list);
        page.setTotal(count);
    }

    @Override
    public void getPageByExampleNe(T query, Page<T> page, Map<String, Object> neqProperty) {
        List<T> list = getDao().findByExampleEqNeProperty(query, page.getPageCurrent(), page.getPageSize(), neqProperty);
        long count = getDao().countByExampleEqNeProperty(query, neqProperty);
        page.setList(list);
        page.setTotal(count);
    }


}
