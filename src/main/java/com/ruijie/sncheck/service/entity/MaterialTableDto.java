package com.ruijie.sncheck.service.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * MaterialTableDto
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:01
 */
@Data
public class MaterialTableDto {
    private String materielCode;
    private String task;
    private String boxCode;
    private String snCode;
    private String spareCode;
    private String attributeCode;
    private String printer;
    private Date printDate;
    private String printTimes;
    private String lastPrinter;
    private Date lastPrintDate;
    private String lastPrintTime;
    private Date createDate;
    private Date updateDate;
    private String values1;
    private String values2;
    private String values3;
    private String values4;
    private Integer num;
    private String oldBocCode;
    private String powerCode;
    private String productName;

}
