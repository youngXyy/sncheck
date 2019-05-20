package com.ruijie.sncheck.service;

import com.ruijie.sncheck.service.entity.ZplPrinter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.omg.CORBA.portable.InputStream;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * PrintService
 *
 * @author {yuanwei}
 * @date 2019/5/16 22:03
 */
@Service
public class PrinterService {



    public Boolean printBarCode(String attributeCode){
        //设置打印机
        ZplPrinter p = new ZplPrinter("yw");
        //条码样式模板
        String bar0Zpl = "^FO110,110^BY6,3.0,280^BCN,,Y,N,N^FD${data}^FS";
        p.setBarcode(attributeCode, bar0Zpl);
        String zpl = p.getZpl();
        System.out.println(zpl);
        return p.print(zpl);
    }

}
