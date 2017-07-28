package com.allen.service.datachange.impl;

import com.allen.base.config.ConfigProp;
import com.allen.dao.datachange.AuditDataChangeDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.entity.datachange.DataChange;
import com.allen.service.datachange.AuditDataChangeService;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/3.
 */
@Service
public class AuditDataChangeServiceImpl implements AuditDataChangeService {

    @Autowired
    private DataChangeDao dataChangeDao;
    @Autowired
    private AuditDataChangeDao auditDataChangeDao;
    @Autowired
    private ConfigProp configProp;

    @Override
    @Transactional
    public void audit(HttpServletRequest request, long id, int state, String refuseContent)throws Exception{
        DataChange dataChange = dataChangeDao.findOne(id);
        dataChange.setState(state);
        dataChange.setRefuseContent(refuseContent);
        dataChangeDao.save(dataChange);
        if(state == DataChange.STATE_AUDIT_PASS){
            if(dataChange.getType() == DataChange.TYPE_DEL){
                String sql = "delete from "+dataChange.getChangeTable()+ " where id = "+dataChange.getChangeTableId();
                auditDataChangeDao.auditPassOperate(sql);
                if("teach_plan".equals(dataChange.getChangeTable())){
                    sql = "delete from teach_plan_course where teach_plan_id = "+dataChange.getChangeTableId();
                    auditDataChangeDao.auditPassOperate(sql);
                }

            }
            if(dataChange.getType() == DataChange.TYPE_EDIT && !StringUtil.isEmpty(dataChange.getChangeTableField())){
                //如果是修改报名表，那么报名表的上传照片的字段不需要修改，但是文件需要重新覆盖
                if("sign_up".equals(dataChange.getChangeTable())){
                    String changeTableField = dataChange.getChangeTableField();
                    if(changeTableField.indexOf("photo_url=") > 0){
                        String photoUrl = changeTableField.substring(changeTableField.indexOf("photo_url="), changeTableField.indexOf("@#@photo_url")+14);
                        String url = photoUrl.substring(photoUrl.indexOf("'")+1, photoUrl.indexOf("@#@"));
                        //删除文件
                        if(StringUtil.isEmpty(url)){
                            changeTableField = changeTableField.replace(photoUrl, "photo_url='',");
                            UpLoadFileUtil.delDir(request.getRealPath("")+configProp.getSignUp().get("photoUrl")+dataChange.getChangeTableId()+".png");
                        }else{
                            //替换文件
                            UpLoadFileUtil.custFile(request, url, configProp.getSignUp().get("photoUrl"), dataChange.getChangeTableId()+".png");
                            changeTableField = changeTableField.replace(photoUrl, "photo_url='"+configProp.getSignUp().get("photoUrl")+dataChange.getChangeTableId()+".png"+"',");
                        }
                    }
                    if(changeTableField.indexOf("id_card_front_url=") > 0){
                        String id_card_front_url = changeTableField.substring(changeTableField.indexOf("id_card_front_url="), changeTableField.indexOf("@#@id_card_front_url")+22);
                        String url = id_card_front_url.substring(id_card_front_url.indexOf("'")+1, id_card_front_url.indexOf("@#@"));
                        //删除文件
                        if(StringUtil.isEmpty(url)){
                            changeTableField = changeTableField.replace(id_card_front_url, "id_card_front_url='',");
                            UpLoadFileUtil.delDir(request.getRealPath("")+configProp.getSignUp().get("idCardFrontUrl")+dataChange.getChangeTableId()+".png");
                        }else{
                            //替换文件
                            UpLoadFileUtil.custFile(request, url, configProp.getSignUp().get("idCardFrontUrl"), dataChange.getChangeTableId()+".png");
                            changeTableField = changeTableField.replace(id_card_front_url, "id_card_front_url='"+configProp.getSignUp().get("idCardFrontUrl")+dataChange.getChangeTableId()+".png"+"',");
                        }
                    }
                    if(changeTableField.indexOf("id_card_back_url=") > 0){
                        String id_card_back_url = changeTableField.substring(changeTableField.indexOf("id_card_back_url="), changeTableField.indexOf("@#@id_card_back_url")+21);
                        String url = id_card_back_url.substring(id_card_back_url.indexOf("'")+1, id_card_back_url.indexOf("@#@"));
                        //删除文件
                        if(StringUtil.isEmpty(url)){
                            changeTableField = changeTableField.replace(id_card_back_url, "id_card_back_url='',");
                            UpLoadFileUtil.delDir(request.getRealPath("")+configProp.getSignUp().get("idCardBackUrl")+dataChange.getChangeTableId()+".png");
                        }else{
                            //替换文件
                            UpLoadFileUtil.custFile(request, url, configProp.getSignUp().get("idCardBackUrl"), dataChange.getChangeTableId()+".png");
                            changeTableField = changeTableField.replace(id_card_back_url, "id_card_back_url='"+configProp.getSignUp().get("idCardBackUrl")+dataChange.getChangeTableId()+".png"+"',");
                        }
                    }
                    if(changeTableField.indexOf("diploma_url=") > 0){
                        String diploma_url = changeTableField.substring(changeTableField.indexOf("diploma_url="), changeTableField.indexOf("@#@diploma_url")+16);
                        String url = diploma_url.substring(diploma_url.indexOf("'")+1, diploma_url.indexOf("@#@"));
                        //删除文件
                        if(StringUtil.isEmpty(url)){
                            changeTableField = changeTableField.replace(diploma_url, "diploma_url='',");
                            UpLoadFileUtil.delDir(request.getRealPath("")+configProp.getSignUp().get("diplomaUrl")+dataChange.getChangeTableId()+".png");
                        }else{
                            //替换文件
                            UpLoadFileUtil.custFile(request, url, configProp.getSignUp().get("diplomaUrl"), dataChange.getChangeTableId()+".png");
                            changeTableField = changeTableField.replace(diploma_url, "diploma_url='"+configProp.getSignUp().get("diplomaUrl")+dataChange.getChangeTableId()+".png"+"',");
                        }
                    }
                    if(changeTableField.indexOf("xxw_url=") > 0){
                        String xxw_url = changeTableField.substring(changeTableField.indexOf("xxw_url="), changeTableField.indexOf("@#@xxw_url")+12);
                        String url = xxw_url.substring(xxw_url.indexOf("'")+1, xxw_url.indexOf("@#@"));
                        //删除文件
                        if(StringUtil.isEmpty(url)){
                            changeTableField = changeTableField.replace(xxw_url, "xxw_url='',");
                            UpLoadFileUtil.delDir(request.getRealPath("")+configProp.getSignUp().get("xxwUrl")+dataChange.getChangeTableId()+".png");
                        }else{
                            //替换文件
                            UpLoadFileUtil.custFile(request, url, configProp.getSignUp().get("xxwUrl"), dataChange.getChangeTableId()+".png");
                            changeTableField = changeTableField.replace(xxw_url, "xxw_url='"+configProp.getSignUp().get("xxwUrl")+dataChange.getChangeTableId()+".png"+"',");
                        }
                    }
                    if(changeTableField.indexOf("yds_url=") > 0){
                        String yds_url = changeTableField.substring(changeTableField.indexOf("yds_url="), changeTableField.indexOf("@#@yds_url")+12);
                        String url = yds_url.substring(yds_url.indexOf("'")+1, yds_url.indexOf("@#@"));
                        //删除文件
                        if(StringUtil.isEmpty(url)){
                            changeTableField = changeTableField.replace(yds_url, "yds_url='',");
                            UpLoadFileUtil.delDir(request.getRealPath("")+configProp.getSignUp().get("ydsUrl")+dataChange.getChangeTableId()+".png");
                        }else{
                            //替换文件
                            UpLoadFileUtil.custFile(request, url, configProp.getSignUp().get("ydsUrl"), dataChange.getChangeTableId()+".png");
                            changeTableField = changeTableField.replace(yds_url, "yds_url='"+configProp.getSignUp().get("ydsUrl")+dataChange.getChangeTableId()+".png"+"',");
                        }
                    }
                    dataChange.setChangeTableField(changeTableField.substring(0, changeTableField.length()-1));
                }
                String sql = "update "+dataChange.getChangeTable()+ " set " + dataChange.getChangeTableField()+" where id = "+dataChange.getChangeTableId();
                auditDataChangeDao.auditPassOperate(sql);
            }
        }
    }
}
