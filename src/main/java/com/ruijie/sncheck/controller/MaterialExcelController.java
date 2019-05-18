package com.ruijie.sncheck.controller;

import com.ruijie.sncheck.common.error.ApiException;
import com.ruijie.sncheck.common.util.CopyBean;
import com.ruijie.sncheck.common.vo.RespVo;
import com.ruijie.sncheck.controller.vo.MaterialVo;
import com.ruijie.sncheck.service.CheckService;
import com.ruijie.sncheck.service.SncheckExcelService;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import com.sun.deploy.net.URLEncoder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MaterialExcelController
 *
 * @author {yuanwei}
 * @date 2019/5/9 8:59
 */
@RestController
@RequestMapping("/sncheck")
public class MaterialExcelController {
    private final SncheckExcelService sncheckExcelService;
    private final CheckService checkService;

    @Autowired
    public MaterialExcelController(SncheckExcelService sncheckExcelService, CheckService checkService) {
        this.sncheckExcelService = sncheckExcelService;
        this.checkService = checkService;
    }

    /**
     * 文件上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public RespVo<List<MaterialVo>> uploadExcel(@RequestParam("file") MultipartFile file) {
        List<MaterialTableDto> result = new ArrayList<>();
        try {
            result = sncheckExcelService.sncheckExcel(file);
            //如果需要将文件放到服务其中加以下代码
           /* try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return RespVo.status(HttpStatus.CREATED).body(result.stream().map(d-> CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList()));
    }

    @RequestMapping(value="/excelExport")
    public ResponseEntity<Resource> excelExport(HttpServletResponse response, MaterialVo vo) {
        try {
            ClassPathResource cpr = new ClassPathResource("/templates/"+"导入导出报表.xls");
            InputStream is = cpr.getInputStream();
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet0 =workbook.getSheetAt(0);
            List <MaterialVo> list =  checkService.findByExample(CopyBean.simpleCopy(vo,MaterialTableDto.class)).
                    stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList());

            for(int i = 0;i<list.size();i++){
                Row row1 = sheet0.getRow(i+1);
                row1.createCell(0).setCellValue(i+1);
                row1.createCell(1).setCellValue("WL1");
                row1.createCell(2).setCellValue(list.get(i).getBoxCode());
                row1.createCell(3).setCellValue(list.get(i).getSnCode());
                row1.createCell(4).setCellValue(list.get(i).getNum());
                row1.createCell(5).setCellValue(list.get(i).getSpareCode());
                row1.createCell(6).setCellValue(list.get(i).getProductName());
                if(list.get(i).getSnCode().substring(0,2).equals("G1")||list.get(i).getSnCode().substring(0,2).equals("G1")){
                    row1.createCell(7).setCellValue("Y");
                }else{
                    row1.createCell(7).setCellValue("");
                }
                row1.createCell(8).setCellValue("Y");
                row1.createCell(11).setCellValue(list.get(i).getSnCode().substring(2,7));
            }
            String fileName = "snCheck.xls";
            downLoadExcel(fileName, response, workbook);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(HttpStatus.OK);
    }
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
