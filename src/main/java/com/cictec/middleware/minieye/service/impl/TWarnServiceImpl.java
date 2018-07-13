package com.cictec.middleware.minieye.service.impl;

import com.cictec.middleware.minieye.config.AbstractService;
import com.cictec.middleware.minieye.dao.TWarnMapper;
import com.cictec.middleware.minieye.entity.po.TWarn;
import com.cictec.middleware.minieye.service.TWarnService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by daxian on 2018/04/24.
 */
@Service
@Transactional
public class TWarnServiceImpl extends AbstractService<TWarn> implements TWarnService {
    @Resource
    private TWarnMapper tWarnMapper;

}
