package com.allen.web.controller.datachange;

import com.allen.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Allen on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/findDataChangeById")
public class FindDataChangeByIdController extends BaseController{

    @RequestMapping(value = "open")
    public void open(HttpServletResponse response,
                    @RequestParam("id")long id,
                    @RequestParam("table")String table,
                    @RequestParam("tableId")long tableId,
                    @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        if("recruit_type".equals(table)){
            response.sendRedirect("/findRecruitTypeById/open.html?id="+tableId+"&dataChangeId="+id+"&reqParams="+reqParams);
        }
    }
}
