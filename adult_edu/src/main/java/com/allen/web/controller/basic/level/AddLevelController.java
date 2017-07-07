package com.allen.web.controller.basic.level;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.Level;
import com.allen.service.basic.level.AddLevelService;
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
@RequestMapping(value = "/addLevel")
public class AddLevelController extends BaseController {

    @Autowired
    private AddLevelService addLevelService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "basic/level/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, Level level) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != level) {
            level.setCenterId(UserUtil.getLoginUserForCenterId(request));
            level.setCreator(UserUtil.getLoginUserForName(request));
            level.setOperator(UserUtil.getLoginUserForName(request));
            addLevelService.add(level);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
