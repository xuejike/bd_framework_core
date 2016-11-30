package com.bidanet.bdcms.core.dao;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2015/10/31.
 */
public interface Dao<T> {


    T load(Serializable id);

    T get(Serializable id);



    void persist(T entity);

    void save(T entity);
    void update(T entity);
    /**
     * 查询所有实体数据
     * @return
     */
    List<T> findAll();

    /**
     * 求所有数量
     * @return
     */
    int count();

    /**
     * 通过HQL查询
     * @param hql hql语句
     * @param params 参数
     * @return
     */
    List<T> findByHql(String hql, Object... params);

    /**
     * 通过HQL语句查询，带分页
     * @param hql HQL语句
     * @param pageNo 页号，从1起
     * @param pageSize 页大小
     * @param params 参数
     * @return
     */
    List<T> findByHqlPage(String hql, int pageNo, int pageSize, Object... params) ;

    /**
     * 通过Like查询实体
     * @param example 条件
     * @return
     */
    List<T> findByExampleLike(T example);
    List<T> findByExampleLike(T example, String order);
    List<T> findByExampleLike(T example, MatchMode matchMode);
    List<T> findByExampleLike(T example, int pageNo, int pageSize);
    List<T> findByExampleLike(T example, int pageNo, int pageSize, String order);

    long countByExampleLike(T example);

    List<T> findByExampleEq(T example);
    List<T> findByExampleEq(T example, String order);
    List<T> findByExampleEq(T example, MatchMode matchMode);
    List<T> findByExampleEq(T example, int pageNo, int pageSize);
    List<T> findByExampleEq(T example, int pageNo, int pageSize, String order);

    long countByExampleEq(T example);




    List<T> findByExampleNeProperty(T example, int pageNo, int pageSize, Map<String, Object> neqProperty, Order order);

    List<T> findByExampleNeProperty(T example, int pageNo, int pageSize, Map<String, Object> neqProperty);

    long countByExampleNeProperty(T example, Map<String, Object> neqProperty);

    List<T> findByExampleLike(T example, int pageNo, int pageSize, Order order);



    void delete(Serializable id);

    boolean has(T example);

    void flush();
    int execUpdateSQL(String sql);
}
