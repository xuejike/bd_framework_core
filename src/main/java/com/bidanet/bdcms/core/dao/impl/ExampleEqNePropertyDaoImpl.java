package com.bidanet.bdcms.core.dao.impl;

import com.bidanet.bdcms.core.dao.ExampleEqNePropertyDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/9.
 */
public class ExampleEqNePropertyDaoImpl<T> implements ExampleEqNePropertyDao<T> {
    protected BaseDaoImpl<T> baseDao;

    public ExampleEqNePropertyDaoImpl(BaseDaoImpl<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<T> findByExampleEqNeProperty(T example, Map<String, Object> neqProperty) {
        return findByExampleEqNeProperty(example, neqProperty,baseDao.orderId);
    }
    @Override
    public List<T>  findByExampleEqNeProperty(T example, Map<String, Object> neqProperty, String order) {
        return findByExampleEqNeProperty(example, neqProperty, Order.desc(order));
    }
    @Override
    public List<T> findByExampleEqNeProperty(T example, Map<String, Object> neqProperty, Order order) {
        Criteria criteria = baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example)).addOrder(order);
        addNeProperty(neqProperty, criteria);

        return criteria.list();
    }

    @Override
    public List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize,
                                             Map<String, Object> neqProperty, Order order) {
        Criteria criteria = baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example));
        baseDao.createPageOrder(criteria, pageNo, pageSize, order);
        addNeProperty(neqProperty, criteria);
        return criteria.list();
    }

    @Override
    public List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize,
                                             Map<String, Object> neqProperty) {
        return findByExampleEqNeProperty(example, pageNo,
                pageSize, neqProperty,baseDao.orderId);
    }
    @Override
    public List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize,
                                             Map<String, Object> neqProperty, String order) {
        return findByExampleEqNeProperty(example, pageNo, pageSize,
                neqProperty, Order.desc(order));
    }
    @Override
    public long countByExampleEqNeProperty(T example, Map<String, Object> neqProperty) {
        Criteria criteria = baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example));
        addNeProperty(neqProperty, criteria);

        return baseDao.countCriteria(criteria);
    }

    private void addNeProperty(Map<String, Object> neqProperty, Criteria criteria) {
        for (String key : neqProperty.keySet()) {
            Object data = neqProperty.get(key);
            if (data instanceof Iterable){
                for (Object o : ((Iterable) data)) {
                    criteria.add(Restrictions.ne(key,o));
                }
            }else{
                criteria.add(Restrictions.ne(key,data));
            }

        }
    }

}
