package com.bidanet.bdcms.core.dao;

/**
 * Created by xuejike on 2017/3/9.
 */
public interface LambdaQueryWhere<T> {
    T where(T query);
}
