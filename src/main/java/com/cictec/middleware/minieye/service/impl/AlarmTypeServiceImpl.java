package com.cictec.middleware.minieye.service.impl;

import com.cictec.middleware.minieye.config.AbstractService;
import com.cictec.middleware.minieye.dao.AlarmTypeMapper;
import com.cictec.middleware.minieye.entity.po.AlarmType;
import com.cictec.middleware.minieye.service.AlarmTypeService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by daxian on 2018/04/09.
 */
@Service
@Transactional
public class AlarmTypeServiceImpl extends AbstractService<AlarmType> implements AlarmTypeService {
    @Resource
    private AlarmTypeMapper alarmTypeMapper;

    @Override
    public List<AlarmType> findAllType() {
        return alarmTypeMapper.selectAll();
    }
    
    @Override
    public List<AlarmType> findAllTypes(AlarmType alarmType) {
        return alarmTypeMapper.selectTSysDatadictList(alarmType);
    }

}
