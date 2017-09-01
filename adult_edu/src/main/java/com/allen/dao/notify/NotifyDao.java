package com.allen.dao.notify;

import com.allen.entity.notify.Notify;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/8/30.
 */
public interface NotifyDao extends CrudRepository<Notify, Long> {
}
