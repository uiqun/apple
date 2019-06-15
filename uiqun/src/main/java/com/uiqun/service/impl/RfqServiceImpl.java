package com.uiqun.service.impl;

import com.alibaba.fastjson.JSON;
import com.uiqun.dao.RfqDao;
import com.uiqun.model.Rfq;
import com.uiqun.model.RfqDataLog;
import com.uiqun.service.RfqService;
import com.uiqun.utils.Pager;
import com.uiqun.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RfqServiceImpl implements RfqService {
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private RfqDao rfqDao;

    public Pager<Rfq> queryRfqList(Pager<Rfq> pager) {
        try {
            pager.setTotalCount(rfqDao.queryRfqRows(pager));
            pager.setDatas(rfqDao.queryRfqList(pager));
            return pager;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addRfq(Rfq rfq) {
        try {
            boolean flag  = rfqDao.insertRfq(rfq)>0;
            if(flag) {
                //设置/重新设置型号询价数据
                RfqDataLog rfqDataLog = JSON.parseObject((String) redisUtils.get(rfq.getPn()), RfqDataLog.class);
                //如果型号没有询价数据记录
                if (rfqDataLog == null) {
                    rfqDataLog = new RfqDataLog();
                    rfqDataLog.setRfqPn(rfq.getPn());
                    rfqDataLog.setStartDayByRfq(new Date());
                }
                //设置询价redis信息
                rfqDataLog.setRestartDayByRfq(rfqDataLog.getEndDayByRfq());
                rfqDataLog.setEndDayByRfq(null);
                rfqDataLog.setTodayQueryByRfq(rfqDataLog.getTodayQueryByRfq()+1);
                rfqDataLog.setSumQueryByRfq(rfqDataLog.getSumQueryByRfq()+1);
                //保存查询信息
                redisUtils.set(rfqDataLog.getRfqPn(),JSON.toJSONString(rfqDataLog));
                //添加询价排行榜
                redisUtils.setSortedSet("RfqSort", JSON.toJSONString(rfqDataLog), redisUtils.getSortScore("RfqSort", rfq.getPn()) + 1);
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleterfq(int rfqno) {
        try {
            return rfqDao.deleterfq(rfqno)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Rfq modifyrfq(int rfqno) {
        try {
            return rfqDao.modifyrfq(rfqno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Rfq queryRfq(int rfqno) {
        try {
            return rfqDao.queryRfq(rfqno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Rfq> queryRfqListFromFindPrice(Map<String, Object> condition) {
        try {
            return rfqDao.queryRfqListFromFindPrice(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deletXrfqByAdmin(int rfqid) {
        try {
            return rfqDao.deletXrfqByAdmin(rfqid)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Pager<Rfq> queryRfqByAdmin(Pager<Rfq> pager) {
        try {
            pager.setTotalCount(rfqDao.queryRfqRowsByAdmin(pager));
            pager.setDatas(rfqDao.queryRfqByAdmin(pager));
            return pager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
