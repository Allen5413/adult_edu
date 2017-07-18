package com.allen.web.controller.user.center;

import com.allen.entity.user.Center;
import com.allen.entity.user.User;
import com.allen.service.user.center.FindCenterByIdService;
import com.allen.service.user.user.FindUserByCenterIdForCenterManService;
import com.allen.service.user.user.FindUserByIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/18.
 */
@Controller
@RequestMapping(value = "/findCneterById")
public class FindCneterByIdController extends BaseController {

    @Autowired
    private FindCenterByIdService findCenterByIdService;
    @Autowired
    private FindUserByIdService findUserByIdService;
    @Autowired
    private FindUserByCenterIdForCenterManService findUserByCenterIdForCenterManService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request)throws Exception{
        Center center = findCenterByIdService.find(UserUtil.getLoginUserForCenterId(request));
        User user = findUserByIdService.find(UserUtil.getLoginUserForLoginId(request));
        request.setAttribute("center", center);
        request.setAttribute("user", user);
        request.setAttribute("userList", findUserByCenterIdForCenterManService.find(center.getId()));
        request.setAttribute("random", System.currentTimeMillis());
        return "user/center/info";
    }
}
