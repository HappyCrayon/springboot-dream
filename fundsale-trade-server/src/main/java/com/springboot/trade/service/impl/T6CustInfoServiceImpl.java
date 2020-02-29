package com.springboot.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.trade.entity.T6CustInfo;
import com.springboot.trade.mapper.T6CustInfoMapper;
import com.springboot.trade.service.T6CustInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-05-05
 */
@Service
public class T6CustInfoServiceImpl extends ServiceImpl<T6CustInfoMapper, T6CustInfo> implements T6CustInfoService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    public void xxx() {
        System.out.println("xxx");
        log.info("xxx");
        xx1();
    }

    @Transactional
    public void xx1(){
        System.out.println("xx1");
        log.info("xx1");
    }

}
