package com.allen.service.eduadmin.student;

import java.util.Map;

/**
 * Created by Allen on 2017/8/24.
 */
public interface CountStudentNumForStateWhereByCenterIdAndUserIdService {
    public Map find(Long centerId, Long userId)throws Exception;
}
