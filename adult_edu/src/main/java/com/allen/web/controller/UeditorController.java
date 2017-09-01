package com.allen.web.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Allen on 2017/8/30.
 */
@Controller
@RequestMapping(value = "/ueditor")
public class UeditorController {
    /**
     * @return
     */
    @RequestMapping(value = "controller")
    public void controller(HttpServletRequest request, HttpServletResponse response)throws Exception{
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type" , "text/html");
        String rootPath = request.getRealPath( "/" );
        out.write( new ActionEnter( request, rootPath ).exec() );
    }
}
