package com.allen.web.controller.eduadmin.recruittype;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.service.eduadmin.recruittype.AddRecruitTypeService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addRecruitType")
public class AddRecruitTypeController extends BaseController {

    @Autowired
    private AddRecruitTypeService addRecruitTypeService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open()throws Exception{
        return "eduadmin/recruittype/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, RecruitType recruitType) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != recruitType) {
            recruitType.setCenterId(UserUtil.getLoginUserForCenterId(request));
            recruitType.setCerator(UserUtil.getLoginUserForName(request));
            recruitType.setOperator(UserUtil.getLoginUserForName(request));
            addRecruitTypeService.add(recruitType);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
