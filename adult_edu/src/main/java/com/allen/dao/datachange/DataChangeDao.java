package com.allen.dao.datachange;

import com.allen.entity.datachange.DataChange;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/6/30.
 */
public interface DataChangeDao extends CrudRepository<DataChange, Long> {
}
