package com.allen.dao.basic.level;

import com.allen.entity.basic.Level;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/7/6.
 */
public interface LevelDao extends CrudRepository<Level, Long> {
    public List<Level> findByCenterId(long centerId)throws Exception;
    public Level findByCenterIdAndName(long centerId, String name)throws Exception;
}
