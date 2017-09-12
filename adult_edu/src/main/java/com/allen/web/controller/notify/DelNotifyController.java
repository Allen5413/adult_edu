package com.allen.web.controller.notify;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.notify.DelNotifyService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Allen on 2017/8/30.
 */
@Controller
@RequestMapping(value = "/delNotify")
public class DelNotifyController extends BaseController {

    @Autowired
    private DelNotifyService delNotifyService;

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(long id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delNotifyService.del(id);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
