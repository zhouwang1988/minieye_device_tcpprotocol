package com.cictec.middleware.minieye.dao;

import com.cictec.middleware.minieye.config.Mapper;
import com.cictec.middleware.minieye.entity.po.TDevice;

import java.util.List;
import java.util.Map;

public interface TDeviceMapper extends Mapper<TDevice> {

    List<TDevice> findAllOnlineByLineUuid(Map<String, Object> param);

    List<TDevice> findAllDevice();

    void allDeviceOffline();

    TDevice findByDevCode(String devCode);

}