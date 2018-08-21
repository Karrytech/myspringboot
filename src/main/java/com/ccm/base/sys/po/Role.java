package com.ccm.base.sys.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/21 15:02
 * @Description:
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    private Integer roleId;

    private String name;

}
