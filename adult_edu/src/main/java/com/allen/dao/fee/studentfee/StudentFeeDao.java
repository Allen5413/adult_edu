package com.allen.dao.fee.studentfee;

import com.allen.entity.fee.StudentFee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/7/17.
 */
public interface StudentFeeDao extends CrudRepository<StudentFee, Long> {
    public List<StudentFee> findByStudentId(long studentId)throws Exception;
}
