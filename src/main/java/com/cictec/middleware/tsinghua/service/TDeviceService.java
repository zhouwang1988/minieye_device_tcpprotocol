package com.cictec.middleware.tsinghua.service;
import com.cictec.middleware.tsinghua.config.Service;
import com.cictec.middleware.tsinghua.entity.po.TDevice;

import java.util.List;
import java.util.Map;


/**
 * Created by daxian on 2018/04/09.
 */
public interface TDeviceService extends Service<TDevice> {
    List<TDevice> findAllOnlineByLineUuid(Map<String, Object> param);

    List<TDevice> findAllDevice();

    void allDeviceOffline();

    TDevice findByDevCode(String id);
}
