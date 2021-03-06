package com.allen.web.controller.fee.feetype;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.fee.FeeType;
import com.allen.entity.user.User;
import com.allen.service.basic.level.FindLevelBySchoolIdAndTypeIdService;
import com.allen.service.basic.school.FindSchoolByCenterIdService;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeBySchoolIdService;
import com.allen.service.fee.feetype.EditFeeTypeService;
import com.allen.service.fee.feetype.FindFeeTypeByIdService;
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
@RequestMapping(value = "/editFeeType")
public class EditFeeTypeController extends BaseController {

    @Autowired
    private FindFeeTypeByIdService findFeeTypeByIdService;
    @Autowired
    private EditFeeTypeService editFeeTypeService;
    @Autowired
    private FindSchoolByCenterIdService findSchoolByCenterIdService;
    @Autowired
    private FindRecruitTypeBySchoolIdService findRecruitTypeBySchoolIdService;
    @Autowired
    private FindLevelBySchoolIdAndTypeIdService findLevelBySchoolIdAndTypeIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request,
                       @RequestParam(value = "reqParams", required = false)String reqParams,
                       @RequestParam("id")long id)throws Exception{
        FeeType feeType = findFeeTypeByIdService.find(id);
        request.setAttribute("feeType", feeType);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        request.setAttribute("schoolList", findSchoolByCenterIdService.find(UserUtil.getLoginUserForCenterId(request)));
        request.setAttribute("typeList", findRecruitTypeBySchoolIdService.find(feeType.getSchoolId()));
        request.setAttribute("levelList", findLevelBySchoolIdAndTypeIdService.find(feeType.getSchoolId(), feeType.getTypeId()));
        return "fee/feetype/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, FeeType feeType, double money, String batch, String editReson) throws Exception {
        JSONObject jsonObject = new JSONObject();
        int year = Integer.parseInt(batch.split("_")[0]);
        int term = Integer.parseInt(batch.split("_")[1]);
        feeType.setFee((long) money * 100);
        feeType.setYear(year);
        feeType.setTerm(term);
        feeType.setOperator(UserUtil.getLoginUserForName(request));
        editFeeTypeService.edit(feeType, UserUtil.getLoginUserForCenterId(request), UserUtil.getLoginUserForIsOperateAudit(request), UserUtil.getLoginUserForLoginId(request), editReson);
        jsonObject.put("state", 0);
        if(UserUtil.getLoginUserForIsOperateAudit(request) == User.ISOPERATEAUDIT_YES) {
            jsonObject.put("msg", "您的操作已经成功，请等待管理员进行审核！");
        }
        return jsonObject;
    }
}
