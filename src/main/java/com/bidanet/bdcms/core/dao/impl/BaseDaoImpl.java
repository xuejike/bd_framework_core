package com.bidanet.bdcms.core.dao.impl;


import com.bidanet.bdcms.core.dao.Dao;
import com.bidanet.bdcms.core.dao.ExampleEqDao;
import com.bidanet.bdcms.core.dao.ExampleEqNePropertyDao;
import com.bidanet.bdcms.core.dao.ExampleLikeDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * 基础DAO层
 */
public class BaseDaoImpl<T> implements Dao<T> {
    protected String coutId = "id";
    protected String orderId="id";
    protected Logger logger=Logger.getLogger(this.getClass());
    protected Class<T> clazz;

    public final  ExampleEqDao<T> eq=new ExampleEqDaoImpl<T>(this);
    public final ExampleLikeDao<T> like=new ExampleLikeDaoImpl<T>(this);
    public final ExampleEqNePropertyDao<T> eqNe=new ExampleEqNePropertyDaoImpl<T>(this);



    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
        logger.debug("DAO的真实实现类是：" + this.clazz.getName());
    }
    /**
     * 向DAO层注入SessionFactory
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取当前工作的Session
     */
    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }


    @Override
    public T load(Serializable id) {
        return getSession().load(clazz, id);
    }

    @Override
    public T get(Serializable id) {
        T t = getSession().get(clazz, id);

        return t;
    }



    @Override
    public void persist(T entity) {
        getSession().persist(entity);
    }


    @Override
    public void save(T entity) {
        Serializable save = getSession().save(entity);
//        return (PK) save;
    }

    @Override
    public void update(T entity) {


        getSession().update(entity);
    }


    @Override
    public List<T> findAll() {
        return getSession().createCriteria(clazz).list();
    }

    /**
     * 求所有数量
     * @return
     */
    @Override
    public int count() {
        return (int) getSession().createQuery(
                "select count("+coutId+") from "+clazz.getSimpleName())
                .uniqueResult();
    }



    @Override
    public List<T> findByHql(String hql, Object... params) {
        Query query = getQuery(hql, params);
        return query.list();
//        return null;
    }

    private Query getQuery(String hql, Object[] params) {
        Query query = this.getSession().createQuery(hql);
        for (int i = 0; params != null && i < params.length; i++) {
            query.setParameter(i, params[i]);
        }

        return query;
    }

    public List<T> findByHqlPage(String hql, int pageNo, int pageSize, Object... params) {
        Query query = getQuery(hql, params);

        query.setFirstResult((pageNo-1)*pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }


    @Override
    public void delete(Serializable id) {
        T load = getSession().load(clazz, id);
        getSession().delete(load);
    }


    Criteria createExampleLike(T example, int pageNo, int pageSize, Order order) {
        Criteria criteria = getSession().createCriteria(clazz)
                .add(Example.create(example).enableLike(MatchMode.ANYWHERE));
        return createPageOrder(criteria,pageNo,pageSize,order);
    }


    @Override
    public List<T> findByExampleLike(T example){
        return like.findByExampleLike(example,MatchMode.ANYWHERE);
    }

    @Override
    public List<T> findByExampleLike(T example, String order){
        return like.findByExampleLike(example, order);
    }

    @Override
    public List<T> findByExampleLike(T example, MatchMode matchMode){
        return like.findByExampleLike(example, matchMode);
    }



    @Override
    public List<T> findByExampleLike(T example, int pageNo, int pageSize){
        return like.findByExampleLike(example, pageNo, pageSize,orderId);
    }

    public List<T> findByExampleLike(T example, int pageNo, int pageSize, String order){

        return  like.findByExampleLike(example, pageNo, pageSize, Order.desc(order));
    }
    @Override
    public List<T> findByExampleLike(T example, int pageNo, int pageSize, Order order){

        return  like.findByExampleLike(example, pageNo, pageSize,order);
    }


    @Override
    public long countByExampleLike(T example) {
        return like.countByExampleLike(example,MatchMode.ANYWHERE);
    }

    @Override
    public long countByExampleLike(T example, MatchMode matchMode) {
        return like.countByExampleLike(example, matchMode);
    }


    @Override
    public List<T> findByExampleEq(T example) {
        return eq.findByExampleEq(example,orderId);
    }



    @Override
    public List<T> findByExampleEq(T example, String order) {
       return eq.findByExampleEq(example,Order.desc(order));
    }

    @Override
    public List<T> findByExampleEq(T example, Order order) {
        return eq.findByExampleEq(example, order);
    }

    @Override
    public List<T> findByExampleEq(T example, int pageNo, int pageSize) {

        return eq.findByExampleEq(example, pageNo, pageSize,orderId);
    }

    @Override
    public List<T> findByExampleEq(T example, int pageNo, int pageSize, String order) {
        return eq.findByExampleEq(example, pageNo, pageSize,Order.desc(order));
    }

    @Override
    public List<T> findByExampleEq(T example, int pageNo, int pageSize, Order order){

        return eq.findByExampleEq(example, pageNo, pageSize, order);
    }
    @Override
    public long countByExampleEq(T example) {
        return eq.countByExampleEq(example);
    }


    @Override
    public List<T>  findByExampleEqNeProperty(T example, Map<String, Object> neqProperty) {
        return eqNe.findByExampleEqNeProperty(example, neqProperty,orderId);
    }
    @Override
    public List<T>  findByExampleEqNeProperty(T example, Map<String, Object> neqProperty, String order) {
        return eqNe.findByExampleEqNeProperty(example, neqProperty,Order.desc(order));
    }
    @Override
    public List<T> findByExampleEqNeProperty(T example, Map<String, Object> neqProperty, Order order) {


        return eqNe.findByExampleEqNeProperty(example, neqProperty, order);
    }

    @Override
    public List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize,
                                             Map<String, Object> neqProperty, Order order) {
        return eqNe.findByExampleEqNeProperty(example, pageNo, pageSize, neqProperty, order);
    }

    @Override
    public List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize,
                                             Map<String, Object> neqProperty) {
        return eqNe.findByExampleEqNeProperty(example, pageNo,
                pageSize, neqProperty,orderId);
    }
    @Override
    public List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize,
                                             Map<String, Object> neqProperty, String order) {
        return eqNe.findByExampleEqNeProperty(example, pageNo, pageSize,
                neqProperty, Order.desc(order));
    }
    @Override
    public long countByExampleEqNeProperty(T example, Map<String, Object> neqProperty) {
        return eqNe.countByExampleEqNeProperty(example, neqProperty);
    }

    /**
     * 为Criteria 添加 排序以及分页
     * @param criteria 查询Criteria
     * @param pageNo 页号
     * @param pageSize 页大小
     * @param order 排序
     * @return
     */
    protected Criteria createPageOrder(Criteria criteria,int pageNo, int pageSize, Order order){
        return criteria.setFirstResult((pageNo-1) * pageSize)
                .setMaxResults(pageSize)
                .addOrder(order);
    }
    protected long countCriteria(Criteria criteria){
        Long result = (Long) criteria.setProjection(Projections.count(coutId)).uniqueResult();
        if (result==null){
            result= 0L;
        }
        return result;
    }


//    public long countByExample(T example){
//        return (long) getSession().createCriteria(clazz)
//                .add(Example.create(example).enableLike(MatchMode.ANYWHERE))
//                .setProjection(Projections.count("1")).uniqueResult();
//    }

    @Override
    public boolean has(T example){
        long count = countByExampleEq(example);
        return count>0;
    }
    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public int execUpdateSQL(String sql) {
        return getSession().createSQLQuery(sql).executeUpdate();
    }
}
