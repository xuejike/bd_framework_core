package com.bidanet.bdcms.core.dao;

import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by xuejike on 2017/3/9.
 */
public interface LambdaExampleEqDao<T> {
    /**
     * 通过 eq 查询实体
     * @param queryWhere 条件
     * @return
     */
    List<T> findByExampleEq(LambdaQueryWhere<T> queryWhere);

    /**
     *
     * @param queryWhere
     * @param order 倒序排序  字段
     * @return
     */
    List<T> findByExampleEq(LambdaQueryWhere<T> queryWhere, String order);

    List<T> findByExampleEq(LambdaQueryWhere<T> queryWhere, Order order);

    List<T> findByExampleEq(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize);

    List<T> findByExampleEq(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize, String order);

    List<T> findByExampleEq(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize, Order order);

    long countByExampleEq(LambdaQueryWhere<T> queryWhere);
}
