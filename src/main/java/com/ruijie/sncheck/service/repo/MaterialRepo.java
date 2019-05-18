package com.ruijie.sncheck.service.repo;

import com.ruijie.sncheck.dao.po.MaterialTablePoPK;
import com.ruijie.sncheck.service.entity.MaterialTableDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * MaterialRepo
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:19
 */
public interface MaterialRepo {
    List<MaterialTableDto> findAll(Example<MaterialTableDto> example, Pageable pageable);

    List<MaterialTableDto> findByExample(Example<MaterialTableDto> example);

    Optional<MaterialTableDto> findByBoxCodeAndSnCode(String boxCode, String sncode);

    List<MaterialTableDto> batchsave(List<MaterialTableDto> list);

    Optional<MaterialTableDto> findById(MaterialTablePoPK id);

    MaterialTableDto save(MaterialTableDto materialTableDto);

    Boolean delete(MaterialTableDto materialTableDto);

    Optional<List<MaterialTableDto>> findBYBoxCode(String boxCode);
}
