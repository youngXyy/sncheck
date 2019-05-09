package com.ruijie.sncheck.controller;

import com.ruijie.sncheck.common.vo.PageInfoVo;
import com.ruijie.sncheck.common.util.CopyBean;
import com.ruijie.sncheck.common.vo.RespVo;
import com.ruijie.sncheck.controller.vo.MaterialVo;
import com.ruijie.sncheck.service.CheckService;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CheckController
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:07
 */
@RestController
public class CheckController {
    @Autowired
    private CheckService checkService;

    /**
     * 查询列表
     * @param vo
     * @param pageInfoVo
     * @return
     */
    @GetMapping
    @RequestMapping("/meterialList")
    public RespVo<List<MaterialVo>> meterialList(MaterialVo vo,PageInfoVo pageInfoVo){
        MaterialTableDto dto = CopyBean.simpleCopy(vo,MaterialTableDto.class);
        List<MaterialTableDto> list = checkService.materialList(dto,pageInfoVo.pageableEntity());
        return RespVo.status(HttpStatus.OK).body(list.stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList()));
    }

    @GetMapping
    @RequestMapping("/meterial/{sncode}")
    public RespVo<MaterialVo> findBySncode(@PathVariable("sncode") String sncode){
        MaterialTableDto dto = checkService.findBySnCode(sncode);
        return RespVo.status(HttpStatus.OK).body(CopyBean.simpleCopy(dto,MaterialVo.class));
    }

    @GetMapping
    @RequestMapping("/meterialList/{boxCode}")
    public RespVo<List<MaterialVo>> findBYBoxCode(@PathVariable("boxCode") String boxcode){
        List<MaterialTableDto> list = checkService.findBYBoxCode(boxcode);
        return RespVo.status(HttpStatus.OK).body(list.stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList()));
    }

    @GetMapping
    @RequestMapping("/meterial/{sncode}/{boxCode}")
    public RespVo<MaterialVo> findByBoxCodeAndSnCode(@PathVariable("sncode") String sncode,@PathVariable("boxCode") String boxCode){
        MaterialTableDto dto = checkService.findByBoxCodeAndSnCode(sncode,boxCode);
        return RespVo.status(HttpStatus.OK).body(CopyBean.simpleCopy(dto,MaterialVo.class));
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @PutMapping
    public RespVo<MaterialVo> edit(@PathVariable("id") Integer id,@RequestParam MaterialVo vo){
        MaterialTableDto dto = CopyBean.simpleCopy(vo,MaterialTableDto.class);
        dto.setId(id);
        return RespVo.status(HttpStatus.OK).body(CopyBean.simpleCopy(checkService.editMaterial(dto),MaterialVo.class));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public RespVo<Boolean> delete(@PathVariable("id") Integer id){
        Boolean result = checkService.deleteMaterial(id);
        return RespVo.ok(result);
    }
}
