package com.allen.service.basic.spec;

import java.util.List;

/**
 * Created by Allen on 2017/8/23.
 */
public interface FindSpecForNameByCenterIdService {
    public List<String> find(long centerId)throws Exception;
}
