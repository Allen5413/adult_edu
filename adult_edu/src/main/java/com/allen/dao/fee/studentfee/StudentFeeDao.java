package com.allen.dao.fee.studentfee;

import com.allen.entity.fee.StudentFee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/7/17.
 */
public interface StudentFeeDao extends CrudRepository<StudentFee, Long> {
    public List<StudentFee> findByStudentId(long studentId)throws Exception;

    /**
     * 查询一个招生类型下的学生已经交的金额
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select sum(sf.fee) from student s, student_fee sf where s.id = sf.student_id and s.recruit_type_id = ?1")
    public BigInteger countTotalFeeByRtId(long rtId)throws Exception;


    /**
     * 查询一个招生类型下的相同批次年季学生已经交的金额
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select sum(sf.fee) from student s, student_fee sf, teach_plan tp " +
            "where s.id = sf.student_id and s.teach_plan_id = tp.id and s.recruit_type_id = ?1 and tp.year = ?2 and tp.term = ?3")
    public BigInteger countTotalFeeByRtIdAndYearAndTerm(long rtId, int year, int term)throws Exception;
}
