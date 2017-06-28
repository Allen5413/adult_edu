package com.allen.dao.user.center;

import com.allen.entity.user.Center;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2016/12/15.
 */
public interface CenterDao extends CrudRepository<Center, Long> {
    public Center findByCode(String code)throws Exception;
}
