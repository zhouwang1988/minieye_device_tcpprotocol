package com.cictec.middleware.minieye.dao;

import com.cictec.middleware.minieye.config.Mapper;
import com.cictec.middleware.minieye.entity.po.AlarmType;

import java.util.List;

public interface AlarmTypeMapper extends Mapper<AlarmType> {

    List<AlarmType> findAllType();
    
    List<AlarmType> selectTSysDatadictList(AlarmType alarmType);

}