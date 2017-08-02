package com.allen.service.eduadmin.student;

import com.allen.entity.eduadmin.Student;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
public interface EditStudentService {
    public void edit(HttpServletRequest request, Student student, long centerId, int isAudit, long operateId, String editReson)throws Exception;
}
