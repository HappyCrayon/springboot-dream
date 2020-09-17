package com.springboot.trade.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyJobBusiness  {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Async
    public void execute() {
        log.info("异步执行");
    }
}
