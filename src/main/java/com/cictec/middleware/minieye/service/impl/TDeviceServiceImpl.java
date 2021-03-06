package com.cictec.middleware.minieye.service.impl;

import com.cictec.middleware.minieye.config.AbstractService;
import com.cictec.middleware.minieye.dao.TDeviceMapper;
import com.cictec.middleware.minieye.entity.po.TDevice;
import com.cictec.middleware.minieye.service.TDeviceService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by daxian on 2018/04/09.
 */
@Service
@Transactional
public class TDeviceServiceImpl extends AbstractService<TDevice> implements TDeviceService {
    @Resource
    private TDeviceMapper tDeviceMapper;

    @Override
    public List<TDevice> findAllOnlineByLineUuid(Map<String, Object> param) {
        return tDeviceMapper.findAllOnlineByLineUuid(param);
    }

    @Override
    public List<TDevice> findAllDevice() {
        return tDeviceMapper.findAllDevice();
    }

    @Override
    public void allDeviceOffline() {
        tDeviceMapper.allDeviceOffline();
    }

    @Override
    public TDevice findByDevCode(String id) {
        return tDeviceMapper.findByDevCode(id);
    }
}
