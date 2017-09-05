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
     * 查询一个学生一共交了好多钱
     * @param studentId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "SELECT round(sum(sf.fee)/100, 2) fee FROM student_fee sf where sf.student_id = ?1")
    public Double findTotalFeeByStudentId(long studentId)throws Exception;

    /**
     * 查询一个招生类型下的学生已经交的金额
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select ifnull(sum(sf.fee),0) from student s, student_fee sf where s.id = sf.student_id and s.recruit_type_id = ?1")
    public BigInteger countTotalFeeByRtId(long rtId)throws Exception;


    /**
     * 查询一个招生类型下的相同批次年季学生已经交的金额
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select ifnull(sum(sf.fee),0) from student s, student_fee sf, teach_plan tp " +
            "where s.id = sf.student_id and s.teach_plan_id = tp.id and s.recruit_type_id = ?1 and tp.year = ?2 and tp.term = ?3")
    public BigInteger countTotalFeeByRtIdAndYearAndTerm(long rtId, int year, int term)throws Exception;
}
