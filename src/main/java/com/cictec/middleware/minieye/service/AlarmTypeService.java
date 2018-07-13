package com.cictec.middleware.minieye.service;
import com.cictec.middleware.minieye.config.Service;
import com.cictec.middleware.minieye.entity.po.AlarmType;

import java.util.List;


/**
 * Created by daxian on 2018/04/09.
 */
public interface AlarmTypeService extends Service<AlarmType> {

    List<AlarmType> findAllType();

    List<AlarmType> findAllTypes(AlarmType alarmType);
}
