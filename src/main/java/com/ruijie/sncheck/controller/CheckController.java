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
        return RespVo.status(HttpStatus.OK).body(list.stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)
        ).collect(Collectors.toList()));
    }

    @GetMapping
    @RequestMapping("/tocheck")
    public RespVo<List<MaterialVo>> check(MaterialVo vo){
        MaterialTableDto dto = CopyBean.simpleCopy(vo,MaterialTableDto.class);
        List<MaterialTableDto> list = checkService.findByExample(dto);
        if(list.size()==0||list==null){
            return RespVo.status(HttpStatus.NO_CONTENT).body(null);
        }
        if(list.size()>1){
            return RespVo.status(HttpStatus.PARTIAL_CONTENT).body(list.stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList()));
        }
        return RespVo.status(HttpStatus.OK).body(list.stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/meterial/check",method = RequestMethod.POST)
    public RespVo<MaterialVo> check(@RequestParam String boxCode,@RequestParam String snCode){
        MaterialTableDto dto = checkService.findById(boxCode,snCode);
        return RespVo.status(HttpStatus.OK).body(CopyBean.simpleCopy(dto,MaterialVo.class));
    }

    @GetMapping
    @RequestMapping( value ="/findByboxCode", method = RequestMethod.GET )
    public RespVo<List<MaterialVo>> findBYBoxCode(@RequestParam String boxCode){
        List<MaterialTableDto> list = checkService.findBYBoxCode(boxCode);
        return RespVo.status(HttpStatus.OK).body(list.stream().map(d->CopyBean.simpleCopy(d,MaterialVo.class)).collect(Collectors.toList()));
    }

//    @GetMapping
//    @RequestMapping("/meterial/{sncode}/{boxCode}")
//    public RespVo<MaterialVo> findByBoxCodeAndSnCode(@PathVariable("sncode") String sncode,@PathVariable("boxCode") String boxCode){
//        MaterialTableDto dto = checkService.findByBoxCodeAndSnCode(sncode,boxCode);
//        return RespVo.status(HttpStatus.OK).body(CopyBean.simpleCopy(dto,MaterialVo.class));
//    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @GetMapping
    public RespVo<MaterialVo> edit( MaterialVo vo){
        MaterialTableDto dto = CopyBean.simpleCopy(vo,MaterialTableDto.class);
        return RespVo.status(HttpStatus.OK).body(CopyBean.simpleCopy(checkService.editMaterial(dto),MaterialVo.class));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public RespVo<Boolean> delete(@RequestParam String boxCode,@RequestParam String snCode){
        Boolean result = checkService.deleteMaterial(boxCode,snCode);
        return RespVo.ok(result);
    }
}
