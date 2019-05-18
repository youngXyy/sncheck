package com.ruijie.sncheck.service;

import com.ruijie.sncheck.common.error.ApiException;
import com.ruijie.sncheck.common.util.ObjectUtil;
import com.ruijie.sncheck.dao.po.MaterialTablePoPK;
import com.ruijie.sncheck.service.entity.FinalString;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import com.ruijie.sncheck.service.repo.MaterialRepo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CheckService
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:08
 */
@Service
public class CheckService {

    @Autowired
    private MaterialRepo materialRepo;

    /**
     * 查询列表
     * @param dto
     * @param pageable
     * @return
     */
    public List<MaterialTableDto> materialList(MaterialTableDto dto, Pageable pageable){

        Example<MaterialTableDto> example = null;
        try {
            example = Example.of(ObjectUtil.setNullValue(dto));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return materialRepo.findAll(example,pageable);
    }

    public List<MaterialTableDto> findByExample(MaterialTableDto dto){

        Example<MaterialTableDto> example = null;
        try {
            example = Example.of(ObjectUtil.setNullValue(dto));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return materialRepo.findByExample(example);
    }
    public List<MaterialTableDto> findBYBoxCode(String boxCode){
//        if(boxCode==""){boxCode=null;}
//        MaterialTableDto dto = new MaterialTableDto();
//        dto.setBoxCode(boxCode);
//        Example<MaterialTableDto> example = Example.of(dto);
        return materialRepo.findBYBoxCode(boxCode).orElseThrow(()->ApiException.badRequest("箱号不存在"));
    }

    public MaterialTableDto findBySnCode(String sncode){
        if(sncode==""){sncode=null;}
        MaterialTableDto dto = new MaterialTableDto();
        dto.setSnCode(sncode);
        Example<MaterialTableDto> example = Example.of(dto);
        List<MaterialTableDto> list = materialRepo.findByExample(example);
        if(list.size()>1){
            throw ApiException.badRequest("存在旧箱号，请扫描新箱号");
        }if(list.size()==0||list==null){
            throw ApiException.badRequest("任务不存在");
        }
        return list.get(0);
    }


    public MaterialTableDto findById(String boxcode,String sncode){
        MaterialTablePoPK pk = new MaterialTablePoPK();
        if(StringUtils.isBlank(boxcode)){
            boxcode= FinalString.DEFAULTBOSCODE;
        }
        pk.setBoxCode(boxcode);
        pk.setSnCode(sncode);
        return materialRepo.findById(pk).orElseThrow(()->ApiException.badRequest("任务不存在"));
    }

    public MaterialTableDto editMaterial(MaterialTableDto dto){
        MaterialTableDto materialTableDto = findById(dto.getBoxCode(),dto.getSnCode());
        ObjectUtil.mergeObject(dto,materialTableDto);
        return materialRepo.save(materialTableDto);
    }


    public Boolean deleteMaterial(String boxcode,String sncode) {
        MaterialTableDto materialTableDto = findById(boxcode,sncode);
        return materialRepo.delete(materialTableDto);
    }
}
