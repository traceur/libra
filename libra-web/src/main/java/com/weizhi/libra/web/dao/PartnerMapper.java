package com.weizhi.libra.web.dao;

import com.weizhi.libra.web.model.Partner;

public interface PartnerMapper {

    int insert(Partner record);


    Partner selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(Partner record);


    int updateStatusByPrtnCode(Partner record);


    Long selectMinPrtnId();


    Partner selectByPrtnCode(String prtnCode);
}
