package com.allen.service.basic.spec;

import com.allen.entity.basic.Spec;

/**
 * Created by Allen on 2017/7/10.
 */
public interface FindSpecByIdService {
    public Spec find(long id)throws Exception;
}
