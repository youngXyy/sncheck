package com.ruijie.sncheck.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * PageInfoVo
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:23
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PageInfoVo implements Serializable {
    private Integer pageSize = 20;
    private Integer pageIndex = 1;
    private Integer totalPage;
    private boolean isMore = false;
    private Long totalElement;
    @Ignore
    private Pageable pageable;

    public boolean isMore() {
        return isMore;
    }



    private Pageable getPageable() {
        if (this.pageable==null) {
            Pageable newPageable;
            newPageable = PageRequest.of(pageIndex>1?pageIndex-1:0, pageSize);
            this.pageable = newPageable;
        }

        return this.pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
        pageSize = pageable.getPageSize();
        pageIndex =pageable.getPageNumber()+1;
    }

    public Pageable pageableEntity() {
        return getPageable();
    }
}
