package com.allen.dao.datachange;

import com.allen.entity.datachange.DataChange;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/6/30.
 */
public interface DataChangeDao extends CrudRepository<DataChange, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "delete ?")
    public void delForAudit(String sql)throws Exception;
}
