package com.rockeycui.learn.boot.dao;

import com.rockeycui.learn.boot.dao.entity.BookInfo;
import com.rockeycui.learn.boot.dao.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author RockeyCui
 */
@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {

    UserInfo findByNameAndPwd(String name, String pwd);

    @Query(value = "SELECT new BookInfo (b,u) FROM BookInfo b"
            + " left join UserInfo u on b.createUser = u.id"
            + " WHERE 1=1"
            + " and (u.name like CONCAT('%',?1,'%') OR ?1 IS NULL)"
            + " and (b.name like CONCAT('%',?2,'%')  OR ?2 IS NULL)",
            countQuery = "SELECT COUNT(b.id) FROM BookInfo b"
                    + " left join UserInfo u on b.createUser = u.id"
                    + " WHERE 1=1"
                    + " and (u.name like CONCAT('%',?1,'%') OR ?1 IS NULL)"
                    + " and (b.name like CONCAT('%',?2,'%')  OR ?2 IS NULL)")
    Page<BookInfo> queryBookPage(String userName, String bookName, Pageable pageable);
}
