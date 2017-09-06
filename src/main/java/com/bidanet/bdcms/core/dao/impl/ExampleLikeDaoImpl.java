package com.bidanet.bdcms.core.dao.impl;

import com.bidanet.bdcms.core.dao.ExampleLikeDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * Created by xuejike on 2017/3/9.
 */
public class ExampleLikeDaoImpl<T> implements ExampleLikeDao<T> {
    protected BaseDaoImpl<T> baseDao;

    public ExampleLikeDaoImpl(BaseDaoImpl<T> baseDao) {
        this.baseDao = baseDao;
    }

    Criteria createExampleLike(T example, int pageNo, int pageSize, Order order) {
        Criteria criteria = baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example).enableLike(MatchMode.ANYWHERE));
        return baseDao.createPageOrder(criteria,pageNo,pageSize,order);
    }


    @Override
    public List<T> findByExampleLike(T example){
        return findByExampleLike(example,MatchMode.ANYWHERE);
    }

    @Override
    public List<T> findByExampleLike(T example, String order){
        List list =  baseDao.getSession().createCriteria(baseDao.clazz)

                .add(Example.create(example).enableLike()).addOrder(Order.asc(order)).list();

        return list;
    }

    @Override
    public List<T> findByExampleLike(T example, MatchMode matchMode){

        List list = baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example)
                        .enableLike(matchMode)).list();
        return list;
    }



    @Override
    public List<T> findByExampleLike(T example, int pageNo, int pageSize){
        return findByExampleLike(example, pageNo, pageSize,baseDao.orderId);
    }

    public List<T> findByExampleLike(T example, int pageNo, int pageSize, String order){

        return findByExampleLike(example, pageNo, pageSize, Order.desc(order));
    }
    @Override
    public List<T> findByExampleLike(T example, int pageNo, int pageSize, Order order){
        List list = createExampleLike(example, pageNo, pageSize, order)
                .list();
        return list;
    }


    @Override
    public long countByExampleLike(T example) {

        return countByExampleLike(example,MatchMode.ANYWHERE);
    }

    @Override
    public long countByExampleLike(T example, MatchMode matchMode) {
        Long o = (Long) baseDao.getSession().createCriteria(baseDao.clazz)
                .add(Example.create(example)
                        .enableLike(matchMode))
                .setProjection(Projections.count("id")).uniqueResult();
        return o;
    }
}
