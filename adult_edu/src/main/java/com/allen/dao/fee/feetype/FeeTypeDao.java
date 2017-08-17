package com.allen.dao.fee.feetype;

import com.allen.entity.fee.FeeType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/7/17.
 */
public interface FeeTypeDao extends CrudRepository<FeeType, Long> {
    public FeeType findByNameAndSchoolIdAndTypeIdAndLevelIdAndYearAndTerm(String name, long schoolId, long typeId, long levelId, int year, int term)throws Exception;
    public List<FeeType> findBySchoolIdAndTypeIdAndLevelIdAndYearAndTerm(long schoolId, long typeId, long levelId, int year, int term)throws Exception;

    /**
     * 查询一个招生类型下的一个学校的一个层次的一个批次的学费总金额
     * @param schoolId
     * @param rtId
     * @param levelId
     * @param tpId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "SELECT " +
            "round(sum(ft.fee)/100, 2) fee " +
            "FROM fee_type ft, teach_plan tp " +
            "where ft.school_id = ?1 and ft.type_id = ?2 and ft.level_id = ?3 and tp.id = ?4 and tp.year = ft.year and tp.term = ft.term")
    public Double findTotalFeeBySchoolIdAndTypeIdAndLevelIdAndTpId(long schoolId, long rtId, long levelId, long tpId)throws Exception;

    /**
     * 统计一个招生类型下一共应该交多少钱
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select " +
            "ifnull(sum(ft.fee), 0) " +
            "from student s, fee_type ft " +
            "where ft.type_id = s.recruit_type_id and ft.school_id = s.school_id and ft.level_id = s.level_id and s.recruit_type_id = ?1")
    public BigInteger countTotalFeeByRtId(long rtId)throws Exception;

    /**
     * 统计一个招生类型下的相同年季的批次应缴费
     * @param rtId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select " +
            "sum(ft.fee) " +
            "from student s, fee_type ft " +
            "where ft.type_id = s.recruit_type_id and ft.school_id = s.school_id and ft.level_id = s.level_id and ft.type_id = ?1 and ft.year = ?2 and ft.term = ?3")
    public BigInteger countTotalFeeByRtIdAndYearAndTerm(long rtId, int year, int term)throws Exception;
}
