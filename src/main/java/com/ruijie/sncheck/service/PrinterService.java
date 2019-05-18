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
        ZplPrinter p = new ZplPrinter("ZDesigner 105SLPlus-300dpi ZPL");
        //条码样式模板
        String bar0Zpl = "^FO110,110^BY6,3.0,280^BCN,,Y,N,N^FD${data}^FS";
        p.setBarcode(attributeCode, bar0Zpl);
        String zpl = p.getZpl();
        System.out.println(zpl);
        return p.print(zpl);
    }


//    public static void main(String[] args) {
//        FileInputStream textStream = null;
//        try {
//            textStream = new FileInputStream("地址");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (textStream != null) // 当打印内容不为空时
//        {
//            // 指定打印输出格式
//            DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;//SERVICE_FORMATTED.PRINTABLE
//            // 定位默认的打印服务
//            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
//            // 创建打印作业
//            DocPrintJob job = printService.createPrintJob();
//            // 设置打印属性
//            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
//            // 设置纸张大小,也可以新建MediaSize类来自定义大小
//            pras.add(MediaSizeName.ISO_A4);
//            DocAttributeSet das = new HashDocAttributeSet();
//            // 指定打印内容
//            Doc doc = new SimpleDoc(textStream, flavor, das);
//            // 不显示打印对话框，直接进行打印工作
//            try {
//                job.print(doc, pras); // 进行每一页的具体打印操作
//            } catch (PrintException pe) {
//                pe.printStackTrace();
//            }
//        } else {
//            // 如果打印内容为空时，提示用户打印将取消
//            JOptionPane.showConfirmDialog(null,
//                    "Sorry, Printer Job is Empty, Print Cancelled!",
//                    "Empty", JOptionPane.DEFAULT_OPTION,
//                    JOptionPane.WARNING_MESSAGE);
//        }
//    }


//    public static void main(String[] args) {
//        try
//        {
//            DocFlavor dof = DocFlavor.INPUT_STREAM.GIF;
//
//            Barcode b = BarcodeFactory.createCode128("HOS18051100012222");
//
//            BufferedImage bi = BarcodeImageHandler.getImage(b);
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            ImageIO.write(bi, "gif", os);
//            InputStream is = new ByteArrayInputStream(os.toByteArray());
//
//            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
//            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
//            pras.add(OrientationRequested.PORTRAIT);
//            pras.add(new Copies(1));
//            pras.add(PrintQuality.HIGH);
//            DocAttributeSet das = new HashDocAttributeSet();
//            // 设置打印纸张的大小（以毫米为单位）
//            das.add(new MediaPrintableArea(0, 0, 46, 30, MediaPrintableArea.MM));
//
//            Doc doc = new SimpleDoc(is, dof, das);
//
//            DocPrintJob job = ps.createPrintJob();
//
//            job.print(doc, pras);
//            is.close();
//
//            //PrinterJob job = PrinterJob.getPrinterJob();
//            //job.setPrintable(b);
//            //if (job.printDialog())
//            //{
//            //	job.print();
//            //}
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }


}
