package com.rockeycui.learn.boot.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author RockeyCui
 */
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(10) COMMENT ''")
    private int id;

    @Column(name = "name", columnDefinition = "varchar(32) not null comment '用户名称'")
    private String name;

    @Column(name = "pwd", columnDefinition = "varchar(32) not null comment '用户密码'")
    private String pwd;

    @Column(name = "create_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP not null COMMENT '创建时间'", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
