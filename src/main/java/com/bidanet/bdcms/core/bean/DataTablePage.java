package com.bidanet.bdcms.core.bean;

import com.bidanet.bdcms.core.vo.Page;

/**
 * Created by xuejike on 2016-05-24.
 */
public class DataTablePage {
    private int limit;
    private int offset;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    public<T> Page<T> getPage(){
        Page<T> page = new Page<>();
        page.setPageCurrent(offset/limit+1);
        page.setPageSize(limit);
        return page;
    }
}
