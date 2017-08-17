package com.allen.service.basic.school.impl;

import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.EditSchoolService;
import com.allen.util.DateUtil;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import com.allen.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditSchoolServiceImpl implements EditSchoolService {

    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private ConfigProp configProp;

    @Override
    public void edit(HttpServletRequest request, School school) throws Exception {
        School school2 = schoolDao.findByCenterIdAndCode(school.getCenterId(), school.getCode());
        if(null != school2 && school2.getId() != school.getId()){
            throw new BusinessException("编号已存在！");
        }
        school2 = schoolDao.findByCenterIdAndName(school.getCenterId(), school.getName());
        if(null != school2 && school2.getId() != school.getId()){
            throw new BusinessException("名称已存在！");
        }
        if(null == school2){
            school2 = schoolDao.findOne(school.getId());
        }
        school2.setLogo(school.getLogo());
        school2.setAddress(school.getAddress());
        school2.setLinkMan(school.getLinkMan());
        school2.setUserId(school.getUserId());
        school2.setPhone(school.getPhone());
        school2.setRemark(school.getRemark());
        school2.setOperator(UserUtil.getLoginUserForName(request));
        school2.setOperateTime(DateUtil.getLongNowTime());

        if(!StringUtil.isEmpty(school2.getLogo()) && 0 < school2.getLogo().indexOf("temp")) {
            //把上传的logo照片从临时目录剪切到正式目录，并把文件名改成id
            UpLoadFileUtil.custFile(request, school2.getLogo(), configProp.getSchool().get("logoUrl"), school2.getId() + ".png");
            school2.setLogo(configProp.getSchool().get("logoUrl") + school2.getId() + ".png");
        }
        schoolDao.save(school2);
    }
}
