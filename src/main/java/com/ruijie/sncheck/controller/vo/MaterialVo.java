package com.ruijie.sncheck.controller.vo;

import lombok.Data;

import java.util.Date;

/**
 * MaterialVo
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:01
 */
@Data
public class MaterialVo {
    private Integer id;
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
    /**
     * 打印次数
     */
    private String lastPrintTime;

}
