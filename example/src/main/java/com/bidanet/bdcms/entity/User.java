package com.bidanet.bdcms.entity;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/10.
 */
@Entity
@Table(name = "_user")
public class User {
    private Long id;
    private String name;
    private String dept;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
