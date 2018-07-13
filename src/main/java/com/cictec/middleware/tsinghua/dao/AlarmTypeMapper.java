package com.cictec.middleware.tsinghua.dao;

import com.cictec.middleware.tsinghua.config.Mapper;
import com.cictec.middleware.tsinghua.entity.po.AlarmType;

import java.util.List;

public interface AlarmTypeMapper extends Mapper<AlarmType> {

    List<AlarmType> findAllType();
    
    List<AlarmType> selectTSysDatadictList(AlarmType alarmType);

}