package com.uiqun.service;

import com.uiqun.model.Hotstk;
import com.uiqun.model.User;
import com.uiqun.utils.Pager;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface HotstkService {

    /**
     * excel模板插入型号
     * @return
     */
    boolean insertHotstks(List<List<Object>> hotstks)throws Exception;

    boolean uploadHotstkListByUid(User user, MultipartFile pmultipartfile);

    /**
     * 查找全部
     * @return
     */
    Pager<Hotstk> queryHotstks(Pager<Hotstk> pager);


    /**
     * 前20个热卖
     * @return
     */
    List<Hotstk> queryHotstksFromFindPrice(Map<String, Object> condition);

    /**
     * 删除热卖库存
     * @param id
     * @return
     */
    boolean deletXhotstk(int id);

}
