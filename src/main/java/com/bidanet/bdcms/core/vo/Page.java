package com.bidanet.bdcms.core.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by xuejike on 2015/11/12.
 */
public class Page<T> {

    private List<T> list;
    private int pageCurrent=1;
    private int pageSize=30;
    private Long total=0L;
    private int pageCount=0;
    private Float avgScore=(float)0.0;

    @JSONField(name = "rows")
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }



    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    public Float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Float avgScore) {
        this.avgScore = avgScore;
    }

    public int getPageCount() {
        if (total==null){
            return 0;
        }
        if(pageSize >= total){
            pageCount=1;
            return pageCount;
        }
        if (pageCount==0&&total>0&&pageSize>0){
            pageCount= (int) (total/pageSize)+1;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
