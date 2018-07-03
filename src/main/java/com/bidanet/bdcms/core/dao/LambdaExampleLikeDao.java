package com.bidanet.bdcms.core.dao;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by xuejike on 2017/3/9.
 */
public interface LambdaExampleLikeDao<T> {
    /**
     * 通过Like查询实体
     * @param queryWhere 条件
     * @return
     */
    List<T> findByExampleLike(LambdaQueryWhere<T> queryWhere);

    List<T> findByExampleLike(LambdaQueryWhere<T> queryWhere, String order);

    List<T> findByExampleLike(LambdaQueryWhere<T> queryWhere, MatchMode matchMode);

    List<T> findByExampleLike(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize);

    List<T> findByExampleLike(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize, String order);

    List<T> findByExampleLike(LambdaQueryWhere<T> queryWhere, int pageNo, int pageSize, Order order);

    long countByExampleLike(LambdaQueryWhere<T> queryWhere);

    long countByExampleLike(LambdaQueryWhere<T> queryWhere, MatchMode matchMode);
}
