package com.bidanet.bdcms.core.dao;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by xuejike on 2016/12/2.
 */
public interface ExampleLikeDao<T> {
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

    List<T> findByExampleLike(T example, int pageNo, int pageSize, Order order);

    long countByExampleLike(T example);

    long countByExampleLike(T example, MatchMode matchMode);
}
