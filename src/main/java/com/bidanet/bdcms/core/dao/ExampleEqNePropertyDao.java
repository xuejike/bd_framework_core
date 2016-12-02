package com.bidanet.bdcms.core.dao;

import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2016/12/2.
 */
public interface ExampleEqNePropertyDao<T> {
    List<T> findByExampleEqNeProperty(T example, Map<String, Object> neqProperty);

    List<T>  findByExampleEqNeProperty(T example, Map<String, Object> neqProperty, String order);

    List<T> findByExampleEqNeProperty(T example, Map<String, Object> neqProperty, Order order);

    /**
     * 通过eq查询实体，并包含不等于属性
     * @param example 条件
     * @return
     */
    List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize, Map<String, Object> neqProperty, Order order);

    List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize, Map<String, Object> neqProperty);

    List<T> findByExampleEqNeProperty(T example, int pageNo, int pageSize,
                                      Map<String, Object> neqProperty, String order);

    long countByExampleEqNeProperty(T example, Map<String, Object> neqProperty);
}
