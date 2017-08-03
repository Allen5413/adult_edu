package com.allen.service.fee.studentfee;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
public interface ImportStudentFeeService {
    public JSONObject importStudentFee(HttpServletRequest request, long schoolId)throws Exception;
}
