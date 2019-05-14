package com.ruijie.sncheck.controller;

import com.ruijie.sncheck.common.error.ApiException;
import com.ruijie.sncheck.common.util.CopyBean;
import com.ruijie.sncheck.common.vo.RespVo;
import com.ruijie.sncheck.controller.vo.MaterialVo;
import com.ruijie.sncheck.service.SncheckExcelService;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    public MaterialExcelController(SncheckExcelService sncheckExcelService) {
        this.sncheckExcelService = sncheckExcelService;
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
}
