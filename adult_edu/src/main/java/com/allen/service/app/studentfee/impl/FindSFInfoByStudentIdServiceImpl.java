package com.allen.service.app.studentfee.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.entity.eduadmin.Student;
import com.allen.service.app.studentfee.FindSFInfoByStudentIdService;
import com.allen.service.fee.studentfee.FindStudentFeeByStudentIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindSFInfoByStudentIdServiceImpl implements FindSFInfoByStudentIdService {

    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private StudentFeeDao studentFeeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private FindStudentFeeByStudentIdService findStudentFeeByStudentIdService;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入学生id");
        }
        Student student = studentDao.findOne(Long.parseLong(id));
        json.put("fee", feeTypeDao.findTotalFeeBySchoolIdAndTypeIdAndLevelIdAndTpId(student.getSchoolId(), student.getRecruitTypeId(), student.getLevelId(), student.getTeachPlanId()));
        json.put("payFee", studentFeeDao.findTotalFeeByStudentId(student.getId()));
        json.put("feeState", student.getFeeState());
        JSONObject feeJSON = findStudentFeeByStudentIdService.find(student.getId());
        List<JSONObject> feeList = new ArrayList<JSONObject>();
        Set<String> keys = feeJSON.keySet();
        for (String key : keys){
            JSONObject json2 = new JSONObject();
            BigDecimal totalFee = new BigDecimal(0);

            JSONArray ja = feeJSON.getJSONArray(key);
            if(null != ja && 0 < ja.size()){
                for(int i=0; i<ja.size(); i++){
                    JSONObject json3 = ja.getJSONObject(i);
                    BigDecimal fee = new BigDecimal(json3.get("fee").toString());
                    totalFee = totalFee.add(fee);
                }
            }
            json2.put("date", key);
            json2.put("totalFee", totalFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            json2.put("list", ja);
            feeList.add(json2);
        }
        json.put("feeList", feeList);
        json.put("status", 1);
        return json;
    }
}
