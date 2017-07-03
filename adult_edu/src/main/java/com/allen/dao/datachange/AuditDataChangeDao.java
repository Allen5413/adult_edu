package com.allen.dao.datachange;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/6/30.
 */
@Service
public class AuditDataChangeDao extends BaseQueryDao {
    /**
     * 审核通过后，对数据的变更
     * @param sql
     * @throws Exception
     */
    public void auditPassOperate(String sql)throws Exception{
        super.editAndDelForNativeSql(sql);
    }
}
