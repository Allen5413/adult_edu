package com.allen.web.controller.eduadmin.student;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.util.UpLoadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
@RestController
public class UploadStudentPhotoController {

    @Autowired
    private ConfigProp configProp;

    @RequestMapping(value = "/student/uploadXxw")
    public JSONObject uploadXxw(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getStudent().get("tempUrl");
        String saveFileName = idCard+"_xxw";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "/student/uploadZkzFront")
    public JSONObject uploadZkzFront(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getStudent().get("tempUrl");
        String saveFileName = idCard+"_zkzFront";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "/student/uploadZkzBack")
    public JSONObject uploadZkzBack(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getStudent().get("tempUrl");
        String saveFileName = idCard+"_zkzBack";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
