package com.allen.web.controller.basic.school;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.util.UpLoadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by Allen on 2017/7/12.
 */
@RestController
public class UploadSchoolLogoController {

    @Autowired
    private ConfigProp configProp;

    @RequestMapping(value = "/uploadScLogo")
    public JSONObject upLogo(HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getSchool().get("tempUrl");
        String saveFileName = UUID.randomUUID().toString()+"_logo";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
