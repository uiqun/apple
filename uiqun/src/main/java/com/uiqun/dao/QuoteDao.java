package com.uiqun.dao;

import com.uiqun.model.Quote;
import com.uiqun.model.User;
import com.uiqun.utils.Pager;

import java.util.List;

public interface QuoteDao {
    /**
     * 新增报价
     * @param quote
     * @return
     */
    int insertQuote(Quote quote);

    /**
     * 按照询价查报价
     * @param pager
     * @return
     */
    List<Quote> queryQuotesByRfq(Pager<Quote> pager);

    /**
     * 查询总行数
     * @param pager
     * @return
     */
    int queryQuoteRows(Pager<Quote> pager);

    /**
     * 后台管理分页查询总行数
     * @param pager
     * @return
     */
    int queryQuoteRowsByAdmin(Pager<Quote> pager);

    /**
     * 查询自己的报价
     * @param user
     * @return
     */
    List<Quote> queryQuoteByRfq(User user);


    int queryQuoteRowsByRfq(User user);

    /**
     * 后台管理分页查询所有报价
     * @param pager
     * @return
     */
    List<Quote> queryQuoteByAdmin(Pager<Quote> pager);

    /**
     * 删除报价
     * @param id
     * @return
     */
    int deletXquote(int id);
}
