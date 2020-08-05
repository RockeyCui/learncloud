package com.rockeycui.learn.boot.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author RockeyCui
 */
@Entity
@Table(name = "book_info")
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(10) COMMENT ''")
    private int id;

    @Column(name = "name", columnDefinition = "varchar(32) not null comment '书籍名称'")
    private String name;

    @Column(name = "create_user", columnDefinition = "int(10) not null COMMENT '创建人ID'")
    private int createUser;

    @Column(name = "create_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP not null COMMENT '创建时间'", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Transient
    private UserInfo userInfo;

    public BookInfo() {
    }

    public BookInfo(BookInfo bookInfo, UserInfo userInfo) {
        this.id = bookInfo.id;
        this.name = bookInfo.name;
        this.createUser = bookInfo.createUser;
        this.createTime = bookInfo.createTime;
        this.userInfo = userInfo;

    }

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

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
