package com.bidanet.bdcms.core.dao;

import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by xuejike on 2016/12/2.
 */
public interface ExampleEqDao<T> {
    /**
     * 通过 eq 查询实体
     * @param example 条件
     * @return
     */
    List<T> findByExampleEq(T example);

    /**
     *
     * @param example
     * @param order 倒序排序  字段
     * @return
     */
    List<T> findByExampleEq(T example, String order);

    List<T> findByExampleEq(T example, Order order);

    List<T> findByExampleEq(T example, int pageNo, int pageSize);

    List<T> findByExampleEq(T example, int pageNo, int pageSize, String order);

    List<T> findByExampleEq(T example, int pageNo, int pageSize, Order order);

    long countByExampleEq(T example);
}
