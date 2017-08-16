package com.allen.service.app.uploadfile;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/16.
 */
public interface AppUpLoadImgService {
    public JSONObject upload(HttpServletRequest request, String url)throws Exception;
}
