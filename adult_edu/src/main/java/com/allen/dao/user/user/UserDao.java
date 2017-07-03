package com.allen.dao.user.user;

import com.allen.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2016/12/15.
 */
public interface UserDao extends CrudRepository<User, Long> {
    public User findByLoginNameAndPwd(String loginName, String pwd);

    public User findByLoginName(String loginName);

    public List<User> findByCenterId(long centerId)throws Exception;
}
