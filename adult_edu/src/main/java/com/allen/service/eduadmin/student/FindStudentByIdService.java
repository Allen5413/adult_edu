package com.allen.service.eduadmin.student;

import com.allen.entity.eduadmin.Student;

/**
 * Created by Allen on 2017/8/1.
 */
public interface FindStudentByIdService {
    public Student find(long id)throws Exception;
}
