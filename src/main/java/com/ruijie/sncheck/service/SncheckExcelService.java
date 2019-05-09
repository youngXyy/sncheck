package com.ruijie.sncheck.service;

import com.ruijie.sncheck.common.error.ApiException;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import com.ruijie.sncheck.service.repo.MaterialRepo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * SncheckExcelService
 *
 * @author {yuanwei}
 * @date 2019/5/8 23:06
 */
@Service
public class SncheckExcelService {

    private final MaterialRepo materialRepo;
    /**
     * 注册url
     */
    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";

    @Autowired
    public SncheckExcelService(MaterialRepo materialRepo) {
        this.materialRepo = materialRepo;
    }

    /**
     * 解析文件并存库
     * @param file
     * @return
     */
    public List<MaterialTableDto> sncheckExcel(MultipartFile file){

        List<MaterialTableDto> list = new ArrayList<>();
        if (file == null) {
            throw ApiException.badRequest( "对象不能为空");
        }
        //获取名字
        String originalFilename = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            if (originalFilename.endsWith(SUFFIX_2003)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(SUFFIX_2007)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw ApiException.badRequest("格式错误");
        }
        if (workbook == null) {
            throw ApiException.badRequest("格式错误");
        } else {
            //获取所有的工作表的的数量
            int numOfSheet = workbook.getNumberOfSheets();
            //遍历这些表
            for (int i = 0; i < numOfSheet; i++) {
                //获取一个sheet也就是一个工作簿
                Sheet sheet = workbook.getSheetAt(i);
                int lastRowNum = sheet.getLastRowNum();
                //从第一行开始第一行一般是标题
                for (int j = 1; j <= lastRowNum; j++) {
                    Row row = sheet.getRow(j);
                    MaterialTableDto dto = new MaterialTableDto();
                    //物料编码
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        dto.setMaterielCode(row.getCell(1).getStringCellValue());
                    }
                    //任务
                    if (row.getCell(2) != null) {
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        dto.setTask(row.getCell(2).getStringCellValue());
                    }
                    //箱号
                    if (row.getCell(3) != null) {
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        dto.setBoxCode(row.getCell(3).getStringCellValue());
                    }
                    //扫码SN
                    if (row.getCell(4) != null) {
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        dto.setSnCode(row.getCell(4).getStringCellValue());
                    }
                    //备料单号
                    if (row.getCell(5) != null) {
                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                        dto.setSpareCode(row.getCell(5).getStringCellValue());
                    }
                    //属性号
                    if (row.getCell(6) != null) {
                        row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                        dto.setAttributeCode(row.getCell(6).getStringCellValue());
                    }
                    list.add(dto);
                }
            }
        }
        if(list.size()==0){
            throw ApiException.badRequest("没有数据");
        }
        return materialRepo.batchsave(list);
    }
}
