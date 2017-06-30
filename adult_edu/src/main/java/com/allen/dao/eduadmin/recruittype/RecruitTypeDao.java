package com.allen.dao.eduadmin.recruittype;

import com.allen.entity.eduadmin.RecruitType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/6/29.
 */
public interface RecruitTypeDao extends CrudRepository<RecruitType, Long> {

    public RecruitType findByCenterIdAndName(long centerId, String name)throws Exception;
}
