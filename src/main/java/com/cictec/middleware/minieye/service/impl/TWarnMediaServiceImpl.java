package com.cictec.middleware.minieye.service.impl;

import com.cictec.middleware.minieye.config.AbstractService;
import com.cictec.middleware.minieye.dao.TWarnMediaMapper;
import com.cictec.middleware.minieye.entity.po.TWarnMedia;
import com.cictec.middleware.minieye.service.TWarnMediaService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by daxian on 2018/05/07.
 */
@Service
@Transactional
public class TWarnMediaServiceImpl extends AbstractService<TWarnMedia> implements TWarnMediaService {
    @Resource
    private TWarnMediaMapper tWarnMediaMapper;

}
