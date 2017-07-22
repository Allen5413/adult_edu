package com.allen.web.controller.recruit.signup;

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
public class UploadPhotoController {

    @Autowired
    private ConfigProp configProp;

    @RequestMapping(value = "/uploadPhoto")
    public JSONObject photo(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getSignUp().get("tempUrl");
        String saveFileName = idCard+"_photo";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "/uploadIdCardFront")
    public JSONObject uploadIdCardFront(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getSignUp().get("tempUrl");
        String saveFileName = idCard+"_idCardFront";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "/uploadIdCardBack")
    public JSONObject uploadIdCardBack(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getSignUp().get("tempUrl");
        String saveFileName = idCard+"_idCardBack";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "/uploadDiploma")
    public JSONObject uploadDiploma(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getSignUp().get("tempUrl");
        String saveFileName = idCard+"_diploma";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "/uploadXxw")
    public JSONObject uploadXxw(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getSignUp().get("tempUrl");
        String saveFileName = idCard+"_xxw";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "/uploadYds")
    public JSONObject uploadYds(HttpServletRequest request, String idCard)throws Exception{
        JSONObject jsonObject = new JSONObject();
        MultipartRequest mulReu = (MultipartRequest) request;
        String url = configProp.getSignUp().get("tempUrl");
        String saveFileName = idCard+"_yds";
        //处理上传图片
        String filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
