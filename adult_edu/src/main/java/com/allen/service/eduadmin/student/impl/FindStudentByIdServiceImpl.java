package com.allen.service.eduadmin.student.impl;

import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.entity.eduadmin.Student;
import com.allen.service.eduadmin.student.FindStudentByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/8/1.
 */
@Service
public class FindStudentByIdServiceImpl implements FindStudentByIdService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student find(long id) throws Exception {
        return studentDao.findOne(id);
    }
}
