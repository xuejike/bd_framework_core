package com.bidanet.bdcms.core.service;



import com.bidanet.bdcms.core.vo.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2015/10/31.
 */
public interface Service<T> {


     T load(Serializable id);
     T get(Serializable id);


     /**
      * 通过 等号 进行查询
      * @param entity 条件实体
      * @param page 分页
      */
     void queryEq(T entity, Page<T> page);

     /**
      * 通过 等号 进行查询
      * @param entity 条件实体
      * @return 结果集合
      */
     List<T> queryEq(T entity);

     /**
      * 插入实体
      * @param t
      */
     void insertT(T t);

     /**
      * 更新实体，先get，再设置修改字段
      * @param t
      */
     void updateT(T t);

     /**
      * 获取所有实体集合
      * @return
      */
     List<T> getList();

     /**
      * 通过ID进行删除
      * @param id 实体ID
      */
     void deleteByIdT(Serializable id);

     /**
      * 删除所有实体
      */
     void deleteAllT();

     /**
      * 求所有数量
      * @return
      */
     long count();

     /**
      * 是否存在该条件实体
      * @param t 条件实体
      * @return
      */
     boolean has(T t);

     /**
      * 通过 like 实体条件查询
      * @param example
      * @return
      */
     List<T> findByExampleLike(T example);


     /**
      * 通过 eq 进行实体查询
      * @param example
      * @return
      */
     List<T> findByExampleEq(T example);

     /**
      * 通过 like 进行条件实体查询，进行分页
      * @param query
      * @param page
      */
     void getPageByExampleLike(T query, Page<T> page);

     /**
      *通过 等于 进行条件实体查询，进行分页
      * @param query
      * @param page
      */
     void getPageByExampleEq(T query, Page<T> page);

     /**
      *通过 不等于 进行条件实体查询，进行分页
      * @param query
      * @param page
      * @param neqProperty
      */
     void getPageByExampleNe(T query, Page<T> page, Map<String, Object> neqProperty);

}
