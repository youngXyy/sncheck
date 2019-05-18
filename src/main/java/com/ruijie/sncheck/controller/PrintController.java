package com.ruijie.sncheck.controller;

import com.ruijie.sncheck.common.vo.RespVo;
import com.ruijie.sncheck.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PrintController
 *
 * @author {yuanwei}
 * @date 2019/5/17 9:59
 */
@RestController
public class PrintController {
    @Autowired
    private PrinterService printerService;

    @RequestMapping(value = "/print", method = RequestMethod.POST)
    @ResponseBody
    public RespVo<Boolean> delete(@RequestParam String attributeCode){
        Boolean result = printerService.printBarCode(attributeCode);
        return RespVo.ok(result);
    }
}
