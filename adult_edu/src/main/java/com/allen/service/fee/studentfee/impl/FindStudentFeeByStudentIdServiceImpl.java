package com.allen.service.fee.studentfee.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allen.dao.fee.studentfee.FindStudentFeeDao;
import com.allen.service.fee.studentfee.FindStudentFeeByStudentIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/3.
 */
@Service
public class FindStudentFeeByStudentIdServiceImpl implements FindStudentFeeByStudentIdService {

    @Autowired
    private FindStudentFeeDao findStudentFeeDao;

    @Override
    public JSONObject find(long studentId) throws Exception {
        List<Map> list = findStudentFeeDao.findByStudentId(studentId);
        JSONObject json = new JSONObject();
        JSONArray jsonArray = null;
        if(null != list && 0 < list.size()){
            String beforeFeeDate = "";
            Map<String, String> typeFeeMap = new HashMap<String, String>();
            int i=1;
            for(Map map : list){
                String typeName = map.get("name").toString();
                String fee = map.get("fee").toString();
                if(StringUtil.isEmpty(typeFeeMap.get(typeName))){
                    typeFeeMap.put(typeName, fee);
                }else{
                    typeFeeMap.put(typeName, new BigDecimal(typeFeeMap.get(typeName)).add(new BigDecimal(fee)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                }

                String feeDate = map.get("feeDate").toString();
                if(!beforeFeeDate.equals(feeDate)){
                    if(!StringUtil.isEmpty(beforeFeeDate)) {
                        json.put(beforeFeeDate, jsonArray);
                    }
                    jsonArray = new JSONArray();
                }
                map.put("sumFee", typeFeeMap.get(typeName));
                jsonArray.add(map);
                beforeFeeDate = feeDate;
                if(i == list.size()){
                    json.put(feeDate, jsonArray);
                }
                i++;
            }
        }
        return json;
    }
}
