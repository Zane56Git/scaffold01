package com.zane.scaffold.sercvie.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zane.scaffold.entity.mp.Mp;
import com.zane.scaffold.mapper.MPServiceMapper;
import com.zane.scaffold.sercvie.MPService;

@Service
public class MPServiceImpl extends ServiceImpl<MPServiceMapper, Mp>implements MPService {

}
 