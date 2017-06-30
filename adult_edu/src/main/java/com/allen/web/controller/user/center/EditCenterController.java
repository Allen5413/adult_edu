package com.allen.web.controller.user.center;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.user.Center;
import com.allen.service.user.center.AddCenterService;
import com.allen.service.user.center.EditCenterService;
import com.allen.service.user.center.FindCenterByIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/editCenter")
public class EditCenterController extends BaseController {

    @Autowired
    private FindCenterByIdService findCenterByIdService;
    @Autowired
    private EditCenterService editCenterService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam("id")long id)throws Exception{
        Center center = findCenterByIdService.find(id);
        request.setAttribute("center", center);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "user/center/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, Center center) throws Exception {
        JSONObject jsonObject = new JSONObject();
        editCenterService.edit(center, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
