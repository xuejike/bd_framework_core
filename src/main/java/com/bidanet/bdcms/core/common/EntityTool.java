package com.bidanet.bdcms.core.common;


import com.bidanet.bdcms.core.bean.EntityToVo;
import com.bidanet.bdcms.core.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
public class EntityTool {
    public static ThreadLocal<Boolean> voInvoke = new ThreadLocal<>();

    public List<?> createEntityToVoList(Class<? extends EntityToVo> cs, List list){
        try {
            voInvoke.set(true);
            ArrayList<Object> rs = new ArrayList<>();
            for (Object o : list) {
                Object entityToVo = createEntityToVo(cs, o);
                rs.add(entityToVo);
            }
            return rs;
        }catch (Exception ex){
            throw ex;
        } finally {
            voInvoke.remove();
        }




    }
    public EntityToVo createEntityToVo(Class<? extends EntityToVo> cs,Object ...datas){

        try {
            voInvoke.set(true);
            EntityToVo entityToVo = cs.newInstance();
            entityToVo.loadData(datas);
            return entityToVo;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            voInvoke.set(false);
        }

        return null;
    }


    public Page<EntityToVo> getPageEntityToVo(Class<? extends EntityToVo> cs,Page<?> page){

        Page<EntityToVo> entityToVoPage = new Page<>();
        entityToVoPage.setTotal(page.getTotal());
        entityToVoPage.setPageCurrent(page.getPageCurrent());
        entityToVoPage.setPageSize(page.getPageSize());
        List list = createEntityToVoList(cs,page.getList());
        entityToVoPage.setList(list);

        return entityToVoPage;
    }
}
