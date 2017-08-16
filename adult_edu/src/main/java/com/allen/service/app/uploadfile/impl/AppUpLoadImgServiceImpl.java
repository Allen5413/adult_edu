package com.allen.service.app.uploadfile.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.service.app.uploadfile.AppUpLoadImgService;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class AppUpLoadImgServiceImpl implements AppUpLoadImgService {

    @Override
    public JSONObject upload(HttpServletRequest request, String url)throws Exception {
        JSONObject jsonObject = new JSONObject();
        String idCard = request.getParameter("idCard");
        String type = request.getParameter("type");
        if(StringUtil.isEmpty(idCard)){
            throw new BusinessException("没有传入学生身份证号");
        }
        if(StringUtil.isEmpty(type)){
            throw new BusinessException("没有传入图片类型");
        }
        if(!"0".equals(type)&&!"1".equals(type)&&!"2".equals(type)&&!"3".equals(type)&&!"4".equals(type)&&!"5".equals(type)){
            throw new BusinessException("图片类型传入参数错误");
        }

        MultipartRequest mulReu = (MultipartRequest) request;
        String filePath = "", saveFileName = "";
        if("0".equals(type)){
            saveFileName = idCard+"_photo";
        }
        if("1".equals(type)){
            saveFileName = idCard+"_idCardFront";
        }
        if("2".equals(type)){
            saveFileName = idCard+"_idCardBack";
        }
        if("3".equals(type)){
            saveFileName = idCard+"_diploma";
        }
        if("4".equals(type)){
            saveFileName = idCard+"_xxw";
        }
        if("6".equals(type)){
            saveFileName = idCard+"_yds";
        }
        //处理上传图片
        filePath = UpLoadFileUtil.uploadImg(request, mulReu.getFiles("img"), "jpg|png|jpeg", 10240, 10, url, saveFileName);
        jsonObject.put("url", filePath);
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
