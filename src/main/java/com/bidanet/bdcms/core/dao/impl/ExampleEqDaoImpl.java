package com.bidanet.bdcms.core.dao.impl;

import com.bidanet.bdcms.core.dao.ExampleEqDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by xuejike on 2017/3/9.
 */
public class ExampleEqDaoImpl<T> implements ExampleEqDao<T> {
    protected BaseDaoImpl<T> baseDao;

    public ExampleEqDaoImpl(BaseDaoImpl<T> baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public List<T> findByExampleEq(T example) {
        return findByExampleEq(example,baseDao.orderId);
    }



    @Override
    public List<T> findByExampleEq(T example, String order) {
        return findByExampleEq(example, Order.desc(order));
    }

    @Override
    public List<T> findByExampleEq(T example, Order order) {
        List list = baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example)).addOrder(order).list();
        return list;
    }

    @Override
    public List<T> findByExampleEq(T example, int pageNo, int pageSize) {

        return findByExampleEq(example, pageNo, pageSize,baseDao.orderId);
    }

    @Override
    public List<T> findByExampleEq(T example, int pageNo, int pageSize, String order) {
        return findByExampleEq(example, pageNo, pageSize,Order.desc(order));
    }

    @Override
    public List<T> findByExampleEq(T example, int pageNo, int pageSize, Order order){
        Criteria criteria = baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example));
        List list = baseDao.createPageOrder(criteria, pageNo, pageSize, order).list();
        return list;
    }
    @Override
    public long countByExampleEq(T example) {
        Criteria criteria = baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example));

        return baseDao.countCriteria(criteria);
    }

}
