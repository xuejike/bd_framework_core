package com.bidanet.bdcms.core.dao;

import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/9.
 */
public interface LambdaExampleEqNePropertyDao<T> {
    List<T> findByExampleEqNeProperty(LambdaQueryWhere<T> queryWhere, Map<String, Object> neqProperty);

    List<T>  findByExampleEqNeProperty(LambdaQueryWhere<T> queryWhere, Map<String, Object> neqProperty, String order);

    List<T> findByExampleEqNeProperty(LambdaQueryWhere<T> queryWhere, Map<String, Object> neqProperty, Order order);

    /**
     * 通过eq查询实体，并包含不等于属性
     * @param queryWhere 条件
     * @return
     */
    List<T> findByExampleEqNeProperty(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize, Map<String, Object> neqProperty, Order order);

    List<T> findByExampleEqNeProperty(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize, Map<String, Object> neqProperty);

    List<T> findByExampleEqNeProperty(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize,
                                      Map<String, Object> neqProperty, String order);

    long countByExampleEqNeProperty(LambdaQueryWhere<T> queryWhere, Map<String, Object> neqProperty);
}
