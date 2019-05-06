package com.ruijie.sncheck.service.repo;

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
}
