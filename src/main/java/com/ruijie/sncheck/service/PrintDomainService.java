package com.ruijie.sncheck.service;

import com.ruijie.sncheck.common.error.ApiException;
import org.springframework.stereotype.Service;

import javax.print.PrintService;
import java.awt.print.PrinterJob;

/**
 * PrintDomainService
 *
 * @author {yuanwei}
 * @date 2019/5/6 17:04
 */
@Service
public class PrintDomainService {

    //获取打印机
    protected PrintService getPrintService(String name){
        PrintService printService=null;

        //获得本台电脑连接的所有打印机
        PrintService[] printServices = PrinterJob.lookupPrintServices();
        if(printServices == null || printServices.length == 0) {
            throw ApiException.badRequest("打印失败，未找到可用打印机，请检查。");
        }
        if (name != null) {
            //匹配指定打印机
            for (int i = 0;i < printServices.length; i++) {
                System.out.println(printServices[i].getName());
                if (printServices[i].getName().contains(name)) {
                    printService = printServices[i];
                    break;
                }
            }
            if(printService==null){
                throw ApiException.badRequest("打印失败，未找到名称为" + name + "的打印机，请检查。");
            }
        }else {
            printService=printServices[0];
        }
        return printService;
    }
}
