package com.uiqun.service;

import com.uiqun.model.Quote;
import com.uiqun.utils.Pager;

public interface QuoteService {
    /**
     * 添加报价
     * @param quote
     * @return
     */
    boolean addQuote(Quote quote);

    /**
     * 按询价分页查询所有报价
     * @param pager
     * @return
     */
    Pager<Quote> queryQuotesByRfq(Pager<Quote> pager);

    /**
     * 查询自己的报价
     * @param pager
     * @return
     */
    Pager<Quote> queryQuoteByRfq(Pager<Quote> pager);

    /**
     * 分页查询所有报价
     * @param pager
     * @return
     */
    Pager<Quote> queryQuote(Pager<Quote> pager);

    /**
     * 删除报价
     * @param id
     * @return
     */
    boolean deletXquote(int id);

}
