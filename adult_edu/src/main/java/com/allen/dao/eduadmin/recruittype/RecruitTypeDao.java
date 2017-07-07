package com.allen.dao.eduadmin.recruittype;

import com.allen.entity.eduadmin.RecruitType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/6/29.
 */
public interface RecruitTypeDao extends CrudRepository<RecruitType, Long> {
    public List<RecruitType> findByCenterId(long centerId)throws Exception;
    public RecruitType findByCenterIdAndName(long centerId, String name)throws Exception;
}
