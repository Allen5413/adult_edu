package com.allen.service.app.school.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.school.FindSchoolDao;
import com.allen.service.app.school.FindScAndSpByCenterIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/17.
 */
@Service
public class FindScAndSpByCenterIdServiceImpl implements FindScAndSpByCenterIdService {

    @Autowired
    private FindSchoolDao findSchoolDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String centerId = request.getParameter("centerId");
        if(StringUtil.isEmpty(centerId)){
            throw new BusinessException("没有传入学习中心id");
        }
        List<Map> list = findSchoolDao.findByCenterIdForSchoolAndSpec(Long.parseLong(centerId));
        List<Map> schoolList = new ArrayList<Map>();
        if(null != list){
            List<Map> specList = null;
            Map schoolMap = null;
            Map specMap = null;
            long beforeScId = 0;
            int i=0;
            for (Map map : list){
                long scId = Long.parseLong(map.get("scId").toString());
                String scName = map.get("scName").toString();
                long spId = Long.parseLong(map.get("spId").toString());
                String spName = map.get("spName").toString();

                if(beforeScId != scId){
                    if(0 < i) {
                        schoolList.add(schoolMap);
                    }
                    schoolMap = new HashMap();
                    schoolMap.put("id", scId);
                    schoolMap.put("name", scName);

                    specMap = new HashMap();
                    specMap.put("id", spId);
                    specMap.put("name", spName);

                    specList = new ArrayList<Map>();
                    specList.add(specMap);

                    beforeScId = scId;
                }else{
                    specList = (List<Map>) schoolMap.get("list");
                    specMap = new HashMap();
                    specMap.put("id", spId);
                    specMap.put("name", spName);
                    specList.add(specMap);
                }
                schoolMap.put("list", specList);
                if(list.size()-1 == i) {
                    schoolList.add(schoolMap);
                }
                i++;
            }
        }
        json.put("list", schoolList);
        json.put("status", 1);
        return json;
    }
}
