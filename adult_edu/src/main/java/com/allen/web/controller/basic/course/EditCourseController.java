package com.allen.web.controller.basic.course;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.Course;
import com.allen.entity.user.User;
import com.allen.service.basic.course.EditCourseService;
import com.allen.service.basic.course.FindCourseByIdService;
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
@RequestMapping(value = "/editCourse")
public class EditCourseController extends BaseController {

    @Autowired
    private EditCourseService editCourseService;
    @Autowired
    private FindCourseByIdService findCourseByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams, long id)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("course", findCourseByIdService.find(id));
        return "basic/course/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject edit(HttpServletRequest request, Course course,
                           @RequestParam(value = "editReson", required = false)String editReson) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != course) {
            course.setCreator(UserUtil.getLoginUserForName(request));
            course.setOperator(UserUtil.getLoginUserForName(request));
            editCourseService.edit(course, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request), editReson);
        }
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
