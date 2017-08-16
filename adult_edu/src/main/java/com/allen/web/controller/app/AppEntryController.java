package com.allen.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.service.app.datachange.AuditDCService;
import com.allen.service.app.datachange.FindDCByCenterIdAndStateService;
import com.allen.service.app.datachange.FindDCByIdService;
import com.allen.service.app.feetype.CountFeeByRtIdService;
import com.allen.service.app.index.AppIndexService;
import com.allen.service.app.level.FindLByCenterIdService;
import com.allen.service.app.level.FindLByRtIdAndScIdForTeachPlanService;
import com.allen.service.app.school.FindScByCenterIdService;
import com.allen.service.app.school.FindScByRtIdForTeachPlanService;
import com.allen.service.app.signup.*;
import com.allen.service.app.recruittype.FindRTByCenterIdService;
import com.allen.service.app.spec.FindSpByRtIdAndScIdAndLevelIdForTeachPlanService;
import com.allen.service.app.spec.FindSpBySchoolIdService;
import com.allen.service.app.student.CountPeopleNumByRtIdService;
import com.allen.service.app.student.FindSByIdService;
import com.allen.service.app.student.ListSService;
import com.allen.service.app.studentfee.CountFeeNumByRtIdAndYearAndTermService;
import com.allen.service.app.studentfee.FindSFDetailByStudentIdService;
import com.allen.service.app.studentfee.FindSFInfoByStudentIdService;
import com.allen.service.app.teachplan.FindTPByRtIdAndScIdAndLevelIdAndSpIdForTeachPlanService;
import com.allen.service.app.uploadfile.AppUpLoadImgService;
import com.allen.service.app.user.*;
import com.allen.service.app.center.FindCByIdService;
import com.allen.service.eduadmin.studentcourse.FindSCDetailByStudentIdAndCourseIdService;
import com.allen.util.MD5Util;
import com.allen.util.StringUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/7.
 */
@RestController
public class AppEntryController extends BaseController {

    @Autowired
    private FindRTByCenterIdService findRTByCenterIdService;
    @Autowired
    private FindUByPhoneService findUByPhoneService;
    @Autowired
    private CountPeopleNumForLevelAndTPByIdService countPeopleNumForLevelAndTPByIdService;
    @Autowired
    private CountPeopleNumByRtIdAndYearAndTermService countPeopleNumByRtIdAndYearAndTermService;
    @Autowired
    private CountPeopleNumForSchoolByRtIdAndYearAndTermService countPeopleNumForSchoolByRtIdAndYearAndTermService;
    @Autowired
    private CountPeopleNumForSpecByRtIdAndYearAndTermService countPeopleNumForSpecByRtIdAndYearAndTermService;
    @Autowired
    private CountFeeByRtIdService countFeeByRtIdService;
    @Autowired
    private CountFeeNumByRtIdAndYearAndTermService countFeeNumByRtIdAndYearAndTermService;
    @Autowired
    private CountPeopleNumByRtIdService countPeopleNumByRtIdService;
    @Autowired
    private ListSService listSService;
    @Autowired
    private FindSByIdService findSByIdService;
    @Autowired
    private FindDCByCenterIdAndStateService findDCByCenterIdAndStateService;
    @Autowired
    private FindDCByIdService findDCByIdService;
    @Autowired
    private AuditDCService auditDCService;
    @Autowired
    private AppIndexService appIndexService;
    @Autowired
    private ListUByWhereService listUByWhereService;
    @Autowired
    private FindFxsByIdService findFxsByIdService;
    @Autowired
    private AppResetPwdService appResetPwdService;
    @Autowired
    private DelUService delUService;
    @Autowired
    private FindCByIdService findCByIdService;
    @Autowired
    private FindSUByPhoneService findSUByPhoneService;
    @Autowired
    private AddSUService addSUService;
    @Autowired
    private FindBmkServiceByIdService findBmkServiceByIdService;
    @Autowired
    private FindSUByIdService findSUByIdService;
    @Autowired
    private FindSFInfoByStudentIdService findSFInfoByStudentIdService;
    @Autowired
    private FindSFDetailByStudentIdService findSFDetailByStudentIdService;
    @Autowired
    private FindSCDetailByStudentIdAndCourseIdService findSCDetailByStudentIdAndCourseIdService;
    @Autowired
    private AppUpLoadImgService appUpLoadImgService;
    @Autowired
    private FindScByRtIdForTeachPlanService findScByRtIdForTeachPlanService;
    @Autowired
    private FindLByRtIdAndScIdForTeachPlanService findLByRtIdAndScIdForTeachPlanService;
    @Autowired
    private FindSpByRtIdAndScIdAndLevelIdForTeachPlanService findSpByRtIdAndScIdAndLevelIdForTeachPlanService;
    @Autowired
    private FindTPByRtIdAndScIdAndLevelIdAndSpIdForTeachPlanService findTPByRtIdAndScIdAndLevelIdAndSpIdForTeachPlanService;
    @Autowired
    private FindLByCenterIdService findLByCenterIdService;
    @Autowired
    private FindScByCenterIdService findScByCenterIdService;
    @Autowired
    private FindSpBySchoolIdService findSpBySchoolIdService;
    @Autowired
    private ConfigProp configProp;


    @RequestMapping(value = "/appEntry")
    public JSONObject entry(HttpServletRequest request) throws Exception {
        String zz = request.getParameter("zz");
        String mobile = request.getParameter("mobile");
        String mac = request.getParameter("mac");
        if(StringUtil.isEmpty(request.getParameter("methodId"))){
            throw new BusinessException("协议号为空");
        }
        Integer methodId = Integer.parseInt(request.getParameter("methodId"));
        String key = configProp.getAttop().get("key");
        String query = request.getQueryString();
        query = query.substring(0, query.indexOf("&mac="));
        if(!mac.equals(MD5Util.getAttopMd5(query + key))){
            //throw new BusinessException("mac校验失败");
        }
        JSONObject jsonObject = new JSONObject();
        if(1 == methodId){
            //获取招生类型列表
            jsonObject = findRTByCenterIdService.find(request);
        }
        if(2 == methodId){
            //获取招生类型下的层次人数统计和批次人数统计
            jsonObject = countPeopleNumForLevelAndTPByIdService.find(request);
        }
        if(3 == methodId){
            //获取招生类型下的某一个批次下的分销商人数统计
            jsonObject = countPeopleNumByRtIdAndYearAndTermService.find(request);
        }
        if(4 == methodId){
            //获取招生类型下的某一个批次下的高校人数统计
            jsonObject = countPeopleNumForSchoolByRtIdAndYearAndTermService.find(request);
        }
        if(5 == methodId){
            //获取招生类型下的某一个批次下的专业人数统计
            jsonObject = countPeopleNumForSpecByRtIdAndYearAndTermService.find(request);
        }
        if(6 == methodId){
            //获取招生类型下的缴费情况统计
            jsonObject = countFeeByRtIdService.find(request);
        }
        if(7 == methodId){
            //获取招生类型下的某一个批次的缴费情况统计
            jsonObject = countFeeNumByRtIdAndYearAndTermService.find(request);
        }
        if(8 == methodId){
            //获取招生类型下的学生统计信息
            jsonObject = countPeopleNumByRtIdService.find(request);
        }
        if(9 == methodId){
            //通过筛选条件查询学生信息
            jsonObject = listSService.find(request);
        }
        if(10 == methodId){
            //获取一个学生的详细信息
            jsonObject = findSByIdService.find(request);
        }
        if(11 == methodId){
            //获取审核列表
            jsonObject = findDCByCenterIdAndStateService.find(request);
        }
        if(12 == methodId){
            //获取审核详情数据
            jsonObject = findDCByIdService.find(request);
        }
        if(13 == methodId){
            //审核数据
            jsonObject = auditDCService.audit(request);
        }
        if(14 == methodId){
            //获取用户数据
            jsonObject = listUByWhereService.find(request);
        }
        if(15 == methodId){
            //获取分销商详情数据
            jsonObject = findFxsByIdService.find(request);
        }
        if(16 == methodId){
            //中心子账户密码重置
            jsonObject = appResetPwdService.edit(request);
        }
        if(17 == methodId){
            //删除中心子账户
            jsonObject = delUService.del(request);
        }
        if(18 == methodId){
            //用户登录验证
            jsonObject = findUByPhoneService.find(mobile);
        }
        if(19 == methodId){
            //获取学习中心信息
            jsonObject = findCByIdService.find(request);
        }
        if(20 == methodId){
            //获取学生报名信息
            jsonObject = findSUByPhoneService.find(mobile);
        }
        if(21 == methodId){
            //提交学生报名信息
            jsonObject = addSUService.add(request);
        }
        if(22 == methodId){
            //获取学生报名卡
            jsonObject = findBmkServiceByIdService.find(request);
        }
        if(23 == methodId){
            //获取学生报名信息详情
            jsonObject = findSUByIdService.find(request);
        }
        if(24 == methodId){
            //获取学生缴费信息
            jsonObject = findSFInfoByStudentIdService.find(request);
        }
        if(25 == methodId){
            //获取学生缴费详情
            jsonObject = findSFDetailByStudentIdService.find(request);
        }
        if(26 == methodId){
            //获取学生学籍详情
            jsonObject = findSByIdService.find(request);
        }
        if(27 == methodId){
            //获取学生课程详情
            jsonObject = findSCDetailByStudentIdAndCourseIdService.find(request);
        }
        if(28 == methodId){
            //上传学生报名信息图片
            jsonObject = appUpLoadImgService.upload(request, configProp.getSignUp().get("tempUrl"));
        }
        if(29 == methodId){
            //首页各种统计
            jsonObject = appIndexService.find(request);
        }
        if(30 == methodId){
            //报名时，获取一个招生类型下的当前时间能招生的高校信息
            jsonObject = findScByRtIdForTeachPlanService.find(request);
        }
        if(31 == methodId){
            //报名时，获取一个招生类型下的一个高校下的当前时间能招生的层次信息
            jsonObject = findLByRtIdAndScIdForTeachPlanService.find(request);
        }
        if(32 == methodId){
            //报名时，获取一个招生类型下的一个高校下的一个层次下当前时间能招生的专业信息
            jsonObject = findSpByRtIdAndScIdAndLevelIdForTeachPlanService.find(request);
        }
        if(33 == methodId){
            //报名时，获取一个招生类型下的一个高校下的一个层次下的一个专业下当前时间能招生的批次信息
            jsonObject = findTPByRtIdAndScIdAndLevelIdAndSpIdForTeachPlanService.find(request);
        }
        if(34 == methodId){
            //查询学生时，返回一个学习中心下的层次信息
            jsonObject = findLByCenterIdService.find(request);
        }
        if(35 == methodId){
            //查询学生时，返回一个学习中心下的高校信息
            jsonObject = findScByCenterIdService.find(request);
        }
        if(35 == methodId){
            //查询学生时，返回一个学习中心下的高校信息
            jsonObject = findSpBySchoolIdService.find(request);
        }
        return jsonObject;
    }
}