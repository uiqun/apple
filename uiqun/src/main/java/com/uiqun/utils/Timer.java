package com.uiqun.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时器类
 */
@Component
public class Timer {
    @Resource
    private RedisUtils redisUtils;
    /**
     * 每月1号触发事件
     * 事件:写出文件
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void overrideRfq(){

    }
}
