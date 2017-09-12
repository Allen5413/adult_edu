package com.allen.service.notify;

import com.allen.entity.notify.Notify;

/**
 * Created by Allen on 2017/9/11.
 */
public interface FindNotifyByIdService {
    public Notify find(long id)throws Exception;
}
