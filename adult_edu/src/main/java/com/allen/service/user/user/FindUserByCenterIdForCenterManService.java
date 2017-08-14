package com.allen.service.user.user;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/18.
 */
public interface FindUserByCenterIdForCenterManService {
    public List<Map> find(long centerId)throws Exception;
}
