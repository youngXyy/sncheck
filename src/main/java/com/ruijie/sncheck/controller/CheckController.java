package com.ruijie.sncheck.controller;

import com.ruijie.sncheck.common.vo.ListVo;
import com.ruijie.sncheck.common.vo.PageInfoVo;
import com.ruijie.sncheck.common.util.CopyBean;
import com.ruijie.sncheck.common.vo.RespVo;
import com.ruijie.sncheck.controller.vo.MaterialVo;
import com.ruijie.sncheck.service.CheckService;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public RespVo<ListVo<MaterialVo>> meterialList(MaterialVo vo, PageInfoVo pageInfoVo){
        MaterialTableDto dto = CopyBean.simpleCopy(vo,MaterialTableDto.class);
        Page<MaterialTableDto> dtoPage = checkService.materialList(dto,pageInfoVo.pageableEntity());
        return RespVo.status(HttpStatus.OK).body(ListVo.pageToListVo(dtoPage.map(d->CopyBean.simpleCopy(d,MaterialVo.class))));
    }

    @GetMapping
    @RequestMapping("/allList")
    public RespVo<List<MaterialVo>> findAll(MaterialVo vo){
        MaterialTableDto dto = CopyBean.simpleCopy(vo,MaterialTableDto.class);
        List<MaterialTableDto> list = checkService.findByExample(dto);
        return RespVo.status(HttpStatus.OK).body(list.stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/meterial/check",method = RequestMethod.POST)
    public RespVo<MaterialVo> check(@RequestParam String boxCode,@RequestParam String snCode){
        MaterialTableDto dto = checkService.snCheck(boxCode,snCode);
        return RespVo.status(HttpStatus.OK).body(CopyBean.simpleCopy(dto,MaterialVo.class));
    }

    @GetMapping
    @RequestMapping( value ="/findByboxCode", method = RequestMethod.GET )
    public RespVo<List<MaterialVo>> findBYBoxCode(@RequestParam String boxCode){
        List<MaterialTableDto> list = checkService.findBYBoxCode(boxCode);
        return RespVo.status(HttpStatus.OK).body(list.stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList()));
    }



    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @GetMapping
    public RespVo<MaterialVo> edit(MaterialVo vo){
        MaterialTableDto dto = CopyBean.simpleCopy(vo,MaterialTableDto.class);
        return RespVo.status(HttpStatus.OK).body(CopyBean.simpleCopy(checkService.editMaterial(dto),MaterialVo.class));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public RespVo<Boolean> delete(@RequestParam String boxCode,@RequestParam String snCode){
        Boolean result = checkService.deleteMaterial(boxCode,snCode);
        return RespVo.ok(result);
    }

    @RequestMapping(value = "/batchDelete", method = RequestMethod.DELETE)
    @ResponseBody
    public RespVo<Boolean> batchDelete(@RequestParam List<String> boxCodes,@RequestParam List<String> snCodes){
        Boolean result = checkService.batchDelete(boxCodes,snCodes);
        return RespVo.ok(result);
    }

}
