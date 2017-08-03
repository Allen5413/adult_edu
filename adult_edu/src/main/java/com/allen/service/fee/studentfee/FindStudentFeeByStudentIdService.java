package com.allen.service.fee.studentfee;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Allen on 2017/8/3.
 */
public interface FindStudentFeeByStudentIdService {
    public JSONObject find(long studentId)throws Exception;
}
