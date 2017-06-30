package com.allen.service.user.center;

import com.allen.entity.user.Center;

/**
 * Created by Allen on 2017/6/29.
 */
public interface FindCenterByIdService {
    public Center find(long id)throws Exception;
}
