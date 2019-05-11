package com.uiqun.dao;

import com.uiqun.model.Quote;

public interface QuoteDao {
    /**
     * 新增报价
     * @param quote
     * @return
     */
    int insertQuote(Quote quote);
}
