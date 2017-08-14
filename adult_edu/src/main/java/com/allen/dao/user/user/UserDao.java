package com.allen.dao.user.user;

import com.allen.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2016/12/15.
 */
public interface UserDao extends CrudRepository<User, Long> {
    public User findByLoginNameAndPwd(String loginName, String pwd);

    public User findByLoginName(String loginName);

    public List<User> findByCenterId(long centerId)throws Exception;

    public List<User> findByCenterIdAndTypeAndState(long centerId, int type, int state)throws Exception;

    public User findByCenterIdAndCode(long centerId, String code)throws Exception;

    public User findByCenterIdAndName(long centerId, String name)throws Exception;

    public User findByPhone(String phone)throws Exception;

    @Query("select u from User u, UserGroupUser ugu where u.id = ugu.userId and u.state = 1 and u.centerId = ?1 and ugu.userGroupId = ?2")
    public List<User> findByCenterIdAndUgId(long centerId, long ugId)throws Exception;

    @Query("select u from User u where u.type between 1 and 2 and u.centerId = ?1 order by u.type")
    public List<User> findByCenterIdForCenterMan(long centerId)throws Exception;

    /**
     * 统计一个学习中心下的分销商数量
     * @param centerId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from user u where u.center_id = ?1 and u.type = 3")
    public BigInteger countNumForFxsByCenterId(long centerId)throws Exception;
}
