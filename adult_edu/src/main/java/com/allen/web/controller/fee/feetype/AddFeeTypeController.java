package com.allen.web.controller.fee.feetype;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.fee.FeeType;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.fee.feetype.AddFeeTypeService;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addFeeType")
public class AddFeeTypeController extends BaseController {

    @Autowired
    private AddFeeTypeService addFeeTypeService;
    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        return "fee/feetype/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, FeeType feeType, double money, String batch) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != feeType && !StringUtil.isEmpty(batch)) {
            int year = Integer.parseInt(batch.split("_")[0]);
            int term = Integer.parseInt(batch.split("_")[1]);
            feeType.setCenterId(UserUtil.getLoginUserForCenterId(request));
            feeType.setFee((long)money*100);
            feeType.setYear(year);
            feeType.setTerm(term);
            feeType.setCreator(UserUtil.getLoginUserForName(request));
            feeType.setOperator(UserUtil.getLoginUserForName(request));
            addFeeTypeService.add(feeType);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
