package com.weizhi.libra.web.model;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Partner {
    private String reqSeqNo;

    private Long   id;

    private String prtnCode;

    private Long   prtnId;

    private String prtnName;

    private String prtnAesKey;

    private String prtnHashKey;

    private String memo;

    private Date   createTime;

    private Date   updateTime;

    private String operator;
    /**
     * 状态，I：初始化，N：失败，Y：正常
     */
    private String status;

    /**
     * 客户账户号
     */
    private String custAcctCode;

    /**
     * 证件类型
     */
    private String certType;

    /**
     * 证件号码
     */
    private String certCode;

    /**
     * 合作商客户类型PRTN :
     */
    private String custType;

}
