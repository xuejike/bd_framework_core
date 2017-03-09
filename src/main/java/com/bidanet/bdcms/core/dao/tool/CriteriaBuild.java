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
public class CriteriaBuild<T> {
    protected Session session;
    protected Class<T> tClass=ReflectUtil.getGenericClass(this);
    protected Map<String,Object> eqMap;
    protected Map<String,Object> likeMap;
    protected Map<String,List> neMap;


    public CriteriaBuild(Session session) {
        this.session = session;
    }



    public void  eq(QueryOne<T> queryOne){

    }
    public Criteria getCriteria(){
        Criteria criteria = session.createCriteria(tClass);
        buildEq(criteria);

        return criteria;
    }
    public Criteria list(int pageNo,int pageSize){

        return getCriteria();
    }
    public Criteria count(){

        return count("id");
    }
    public Criteria count(String property){
        Criteria criteria = getCriteria();
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
