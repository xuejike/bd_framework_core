package com.bidanet.bdcms.controller;

import com.bidanet.bdcms.core.dao.impl.BaseDaoImpl;
import com.bidanet.bdcms.dao.UserDaoImpl;
import com.bidanet.bdcms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xuejike on 2017/3/10.
 */
@Controller
public class IndexController {

    @Autowired
    UserDaoImpl userDao;
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        //查询 name = 222 的user
        List<User> list = userDao.criteriaQuery()
                .ne(query -> {
                    query.setId(2L);
                    query.setId(3L);
                })
                .list();
        return "111";
    }
}
