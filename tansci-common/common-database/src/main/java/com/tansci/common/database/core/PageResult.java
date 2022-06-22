package com.tansci.common.database.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @path：com.tansci.common.database.core.PageResult.java
 * @className：PageResult.java
 * @description：分页工具类
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
@Data
@Accessors(chain = true)
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pageNum;

    private Integer pageSize;

    private Integer total;

    private List<T> data;

    public PageResult(Integer total, List<T> data) {
        this.total = total;
        this.data = data;
    }

}
