package com.ruijie.sncheck.common.vo;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;

/**
 * ListVo
 *
 * @author TangWeijie
 * @date 2019/1/24 11:33
 */
@Data
public class ListVo<RESULTS_TYPE> implements Serializable {

    private Collection<RESULTS_TYPE> results;
    private PageInfoVo pageInfo;
    public ListVo(Collection<RESULTS_TYPE> results, PageInfoVo pageInfo) {
        this.results = results;
        this.pageInfo = pageInfo;
    }

    public static  <T> ListVo<T> pageToListVo(Page<T> page) {
        PageInfoVo pageInfoVo = new PageInfoVo();
        pageInfoVo.setPageable(page.getPageable());
        pageInfoVo.setMore(page.hasNext());
        pageInfoVo.setTotalPage(page.getTotalPages());
        pageInfoVo.setTotalElement(page.getTotalElements());
        return new ListVo<>(page.getContent(),pageInfoVo);
    }
}
