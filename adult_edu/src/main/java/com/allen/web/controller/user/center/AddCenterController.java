package com.allen.web.controller.user.center;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.user.Center;
import com.allen.service.user.center.AddCenterService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addCenter")
public class AddCenterController extends BaseController {

    @Autowired
    private AddCenterService addCenterService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "user/center/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, Center center) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != center) {
            center.setCerator(UserUtil.getLoginUserForName(request));
            center.setOperator(UserUtil.getLoginUserForName(request));
            addCenterService.add(center);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
