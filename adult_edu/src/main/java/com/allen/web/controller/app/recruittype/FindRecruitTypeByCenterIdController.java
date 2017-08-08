package com.allen.web.controller.app.recruittype;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByCenterIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/8/7.
 */
@RestController
public class FindRecruitTypeByCenterIdController extends BaseController {

    @Autowired
    private FindRecruitTypeByCenterIdService findRecruitTypeByCenterIdService;

    @RequestMapping(value = "/app/findRecruitTypeByCenterId")
    public JSONObject find(@RequestParam(value = "centerId", required = false)Long centerId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null == centerId || 1 > centerId){
            jsonObject.put("state", 1);
            throw new BusinessException("中心id为空");
        }
        jsonObject.put("state", 0);
        jsonObject.put("list", findRecruitTypeByCenterIdService.find(centerId));
        return jsonObject;
    }
}