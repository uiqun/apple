package com.uiqun.service.impl;

import com.uiqun.dao.QuoteDao;
import com.uiqun.model.Quote;
import com.uiqun.service.QuoteService;
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
}
