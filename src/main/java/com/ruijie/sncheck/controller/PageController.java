package com.ruijie.sncheck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageController
 *
 * @author: {xieyiyang}
 * @date: 2019/5/9 18:25
 * @description:
 */
@Controller
@RequestMapping("page")
public class PageController {
    @RequestMapping("import")
    public String toImport(){
        return "index";
    }
    @RequestMapping("toScan")
    public String toScan(){
        return "scan";
    }
    @RequestMapping("toWork")
    public String toBarcode(){
        return "snWork";
    }
}
