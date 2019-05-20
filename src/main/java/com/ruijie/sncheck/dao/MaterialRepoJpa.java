package com.ruijie.sncheck.dao;

import com.ruijie.sncheck.common.util.CopyBean;
import com.ruijie.sncheck.dao.jpa.MateriaRepository;
import com.ruijie.sncheck.dao.po.MaterialTablePo;
import com.ruijie.sncheck.dao.po.MaterialTablePoPK;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import com.ruijie.sncheck.service.repo.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * MaterialRepoJpa
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:55
 */
@Repository
public class MaterialRepoJpa implements MaterialRepo {
    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public Page<MaterialTableDto> findAll(Example<MaterialTableDto> example, Pageable pageable) {
        MaterialTablePo po = CopyBean.simpleCopy(example.getProbe(),MaterialTablePo.class,"id");
        Example<MaterialTablePo> example1 = Example.of(po,example.getMatcher());
        Page<MaterialTablePo> page = materiaRepository.findAll(example1,pageable);
        return page.map(p->CopyBean.simpleCopy(p,MaterialTableDto.class));
    }

    @Override
    public List<MaterialTableDto> findByExample(Example<MaterialTableDto> example) {
        MaterialTablePo po = CopyBean.simpleCopy(example.getProbe(),MaterialTablePo.class,"id");
        Example<MaterialTablePo> example1 = Example.of(po,example.getMatcher());
        List<MaterialTablePo> page = materiaRepository.findAll(example1);
        return page.stream().map(p->CopyBean.simpleCopy(p,MaterialTableDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<MaterialTableDto> findByBoxCodeAndSnCode(String boxCode, String sncode) {
        Optional<MaterialTablePo> entity =  materiaRepository.findByBoxCodeAndSnCode(boxCode,sncode);
        return entity.map(p->CopyBean.simpleCopy(p,MaterialTableDto.class));
    }

    @Override
    public List<MaterialTableDto> batchsave(List<MaterialTableDto> list) {
        List<MaterialTableDto> listDto = new ArrayList<>();
        List<MaterialTablePo> listPo = list.stream().map(d->CopyBean.simpleCopy(d,MaterialTablePo.class,"createDate","updateDate")).collect(Collectors.toList());
        for (MaterialTablePo po : listPo) {
            listDto.add(CopyBean.simpleCopy(materiaRepository.save(po),MaterialTableDto.class));
        }
        return listDto;
    }

    @Override
    public Optional<MaterialTableDto> findById(MaterialTablePoPK id) {
        Optional<MaterialTablePo> entity = materiaRepository.findById(id);
        return entity.map(po->CopyBean.simpleCopy(po,MaterialTableDto.class));
    }

    @Override
    public MaterialTableDto save(MaterialTableDto materialTableDto) {
        MaterialTablePo po = CopyBean.simpleCopy(materialTableDto,MaterialTablePo.class);
        po=materiaRepository.save(po);
        return CopyBean.simpleCopy(po,MaterialTableDto.class);
    }

    @Override
    public Boolean delete(MaterialTableDto materialTableDto) {
        MaterialTablePo po = CopyBean.simpleCopy(materialTableDto,MaterialTablePo.class);
        materiaRepository.delete(po);
        return true;
    }

    @Override
    public Optional<List<MaterialTableDto>> findBYBoxCode(String boxCode) {
        Optional<List<MaterialTablePo>> po =materiaRepository.findByBoxCode(boxCode);
        return po.map(ps->ps.stream().map(p->CopyBean.simpleCopy(p,MaterialTableDto.class)).collect(Collectors.toList()));
    }

    @Override
    public Boolean batchDelete(List<MaterialTableDto> list) {
        List<MaterialTablePo> listPo = list.stream().map(d->CopyBean.simpleCopy(d,MaterialTablePo.class)).collect(Collectors.toList());
        for (MaterialTablePo po : listPo) {
            materiaRepository.delete(po);
        }
        return true;
    }
}
