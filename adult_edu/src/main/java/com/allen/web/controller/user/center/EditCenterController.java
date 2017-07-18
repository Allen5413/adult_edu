package com.allen.web.controller.user.center;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.entity.user.Center;
import com.allen.service.user.center.AddCenterService;
import com.allen.service.user.center.EditCenterService;
import com.allen.service.user.center.FindCenterByIdService;
import com.allen.util.UpLoadFileUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

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
    @Autowired
    private ConfigProp configProp;

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

    @RequestMapping(value = "openEditName")
    public String openEditName(HttpServletRequest request, @RequestParam("id")long id)throws Exception{
        Center center = findCenterByIdService.find(id);
        request.setAttribute("center", center);
        return "user/center/editName";
    }
    @RequestMapping(value = "editName")
    @ResponseBody
    public JSONObject editName(HttpServletRequest request, long id, String name) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Center center = findCenterByIdService.find(id);
        center.setName(name);
        editCenterService.edit(center, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "openEditEmail")
    public String openEditEmail(HttpServletRequest request, @RequestParam("id")long id)throws Exception{
        Center center = findCenterByIdService.find(id);
        request.setAttribute("center", center);
        return "user/center/editEmail";
    }
    @RequestMapping(value = "editEmail")
    @ResponseBody
    public JSONObject editEmail(HttpServletRequest request, long id, String email) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Center center = findCenterByIdService.find(id);
        center.setEmail(email);
        editCenterService.edit(center, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "openEditAddress")
    public String openEditAddress(HttpServletRequest request, @RequestParam("id")long id)throws Exception{
        Center center = findCenterByIdService.find(id);
        request.setAttribute("center", center);
        return "user/center/editAddress";
    }
    @RequestMapping(value = "editAddress")
    @ResponseBody
    public JSONObject editAddress(HttpServletRequest request, long id, String address) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Center center = findCenterByIdService.find(id);
        center.setAddress(address);
        editCenterService.edit(center, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "openEditRemark")
    public String openEditRemark(HttpServletRequest request, @RequestParam("id")long id)throws Exception{
        Center center = findCenterByIdService.find(id);
        request.setAttribute("center", center);
        return "user/center/editRemark";
    }
    @RequestMapping(value = "editRemark")
    @ResponseBody
    public JSONObject editRemark(HttpServletRequest request, long id, String remark) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Center center = findCenterByIdService.find(id);
        center.setRemark(remark);
        editCenterService.edit(center, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "openEditLogo")
    public String openEditLogo(HttpServletRequest request, @RequestParam("id")long id)throws Exception{
        Center center = findCenterByIdService.find(id);
        request.setAttribute("center", center);
        request.setAttribute("random", System.currentTimeMillis());
        return "user/center/editLogo";
    }
    @RequestMapping(value = "editLogo")
    @ResponseBody
    public JSONObject editLogo(HttpServletRequest request, long id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Center center = findCenterByIdService.find(id);
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getCenterLogo().get("url");
        String saveFileName = center.getCode()+"_logo";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);

        center.setLogo(filePath);
        editCenterService.edit(center, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "openEditBanner")
    public String openEditBanner(HttpServletRequest request, @RequestParam("id")long id)throws Exception{
        Center center = findCenterByIdService.find(id);
        request.setAttribute("center", center);
        request.setAttribute("random", System.currentTimeMillis());
        return "user/center/editBanner";
    }
    @RequestMapping(value = "editBanner")
    @ResponseBody
    public JSONObject editBanner(HttpServletRequest request, long id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Center center = findCenterByIdService.find(id);
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getCenterBanner().get("url");
        String saveFileName = center.getCode()+"_banner";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);

        center.setBanner(filePath);
        editCenterService.edit(center, UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
