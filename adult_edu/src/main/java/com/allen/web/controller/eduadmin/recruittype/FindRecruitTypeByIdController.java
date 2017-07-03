package com.allen.web.controller.eduadmin.recruittype;

import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.RecruitType;
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
@RequestMapping(value = "/findRecruitTypeById")
public class FindRecruitTypeByIdController  extends BaseController {

    @Autowired
    private FindDataChangeByIdService findDataChangeByIdService;
    @Autowired
    private FindRecruitTypeByIdService findRecruitTypeByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(@RequestParam("id")long id,
                       @RequestParam(value = "dataChangeId", required = false)Long dataChangeId,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       HttpServletRequest request)throws Exception{
        RecruitType recruitType = findRecruitTypeByIdService.find(id);
        if(null != dataChangeId) {
            DataChange dataChange = findDataChangeByIdService.find(dataChangeId);
            request.setAttribute("dataChange", dataChange);
        }
        request.setAttribute("recruitType", recruitType);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "eduadmin/recruittype/info";
    }
}
