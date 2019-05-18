package com.ruijie.sncheck.dao.jpa;

import com.ruijie.sncheck.dao.po.MaterialTablePo;
import com.ruijie.sncheck.dao.po.MaterialTablePoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * MateriaRepository
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:53
 */
public interface MateriaRepository extends JpaRepository<MaterialTablePo, MaterialTablePoPK>, JpaSpecificationExecutor<MaterialTablePo> {
    Optional<MaterialTablePo> findByBoxCodeAndSnCode(String boxCode, String sncode);

    Optional<List<MaterialTablePo>> findByBoxCode(String boxCode);
}
