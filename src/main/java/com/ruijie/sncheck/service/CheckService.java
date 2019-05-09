package com.ruijie.sncheck.service;

import com.ruijie.sncheck.common.error.ApiException;
import com.ruijie.sncheck.common.util.CopySameObject;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import com.ruijie.sncheck.service.repo.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Example<MaterialTableDto> example = Example.of(dto);
        return materialRepo.findAll(example,pageable);
    }

    public List<MaterialTableDto> findBYBoxCode(String boxCode){
        MaterialTableDto dto = new MaterialTableDto();
        dto.setBoxCode(boxCode);
        Example<MaterialTableDto> example = Example.of(dto);
        return materialRepo.findByExample(example);
    }

    public MaterialTableDto findBySnCode(String sncode){
        MaterialTableDto dto = new MaterialTableDto();
        dto.setBoxCode(sncode);
        Example<MaterialTableDto> example = Example.of(dto);
        List<MaterialTableDto> list = materialRepo.findByExample(example);
        if(list.size()>1){
            throw ApiException.badRequest("存在旧箱号，请扫描新箱号");
        }if(list.size()==0||list==null){
            throw ApiException.badRequest("任务不存在");
        }
        return list.get(0);
    }

    public MaterialTableDto findByBoxCodeAndSnCode(String boxCode,String sncode){
        return materialRepo.findByBoxCodeAndSnCode(boxCode,sncode).orElseThrow(()->ApiException.badRequest("不存在此任务"));
    }

    public MaterialTableDto findById(Integer id){
        return materialRepo.findById(id).orElseThrow(()->ApiException.badRequest("不存在"));
    }

    public MaterialTableDto editMaterial(MaterialTableDto dto){
        MaterialTableDto materialTableDto = materialRepo.findById(dto.getId()).orElseThrow(()->ApiException.badRequest("不存在"));
        CopySameObject.mergeObject(dto,materialTableDto);
        return materialRepo.save(materialTableDto);
    }


    public Boolean deleteMaterial(Integer id) {
        MaterialTableDto materialTableDto = materialRepo.findById(id).orElseThrow(()->ApiException.badRequest("不存在"));
        return materialRepo.delete(materialTableDto);
    }
}
