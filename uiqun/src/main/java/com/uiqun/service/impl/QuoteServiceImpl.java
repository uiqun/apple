package com.uiqun.service.impl;

import com.uiqun.dao.QuoteDao;
import com.uiqun.model.Quote;
import com.uiqun.model.User;
import com.uiqun.service.QuoteService;
import com.uiqun.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Resource
    private QuoteDao quoteDao;

    @Override
    public boolean addQuote(Quote quote) {
        try {
            return quoteDao.insertQuote(quote)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Pager<Quote> queryQuotesByRfq(Pager<Quote> pager) {
        try {
            pager.setTotalCount(quoteDao.queryQuoteRows(pager));
            pager.setDatas(quoteDao.queryQuotesByRfq(pager));
            return pager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pager<Quote> queryQuoteByRfq(Pager<Quote> pager) {
        try {
            User user = (User)pager.getCondition().get("user");
            pager.setTotalCount(quoteDao.queryQuoteRowsByRfq(user));
            pager.setDatas(quoteDao.queryQuoteByRfq(user));
            return pager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
