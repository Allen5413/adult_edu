package com.allen.web.controller.basic.level;

import com.allen.entity.basic.Level;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.service.basic.level.FindLevelByIdService;
import com.allen.service.datachange.FindDataChangeByIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/findLevelById")
public class FindLevelByIdController extends BaseController {

    @Autowired
    private FindDataChangeByIdService findDataChangeByIdService;
    @Autowired
    private FindLevelByIdService findLevelByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(@RequestParam("id")long id,
                       @RequestParam(value = "dataChangeId", required = false)Long dataChangeId,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       HttpServletRequest request)throws Exception{
        Level level = findLevelByIdService.find(id);
        if(null != dataChangeId) {
            DataChange dataChange = findDataChangeByIdService.find(dataChangeId);
            request.setAttribute("dataChange", dataChange);
        }
        request.setAttribute("level", level);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "basic/level/info";
    }
}
