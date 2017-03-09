package com.bidanet.bdcms.core.dao.tool;

import com.bidanet.bdcms.core.common.ReflectUtil;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/9.
 */
public class LambdaBuild<T> {
    protected Session session;
    protected Class<T> tClass=ReflectUtil.getGenericClass(this);
    protected Map<String,Object> eqMap;
    protected Map<String,Object> likeMap;
    protected Map<String,List> neMap;


    public LambdaBuild(Session session) {
        this.session = session;
    }



    public void  eq(QueryOne<T> queryOne){

    }

    public Criteria build(){
        Criteria criteria = session.createCriteria(tClass);
        buildEq(criteria);

        return criteria;
    }
    protected void buildEq(Criteria criteria){
        criteria.add(Restrictions.allEq(eqMap));

    }
    protected void  buildLike(Criteria criteria){


    }

    public interface QueryOne<T>{
        void where(T query);
    }

}
