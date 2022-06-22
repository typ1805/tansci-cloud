package com.tansci.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.admin.api.domain.SysDic;
import com.tansci.admin.api.dto.SysDicDto;

import java.util.List;


/**
 * @path：com.tansci.admin.service.SysDicService.java
 * @className：SysDicService.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2022/2/15 13:49
 * @editNote：
 */
public interface SysDicService extends IService<SysDic> {

    List<SysDic> dicList(SysDicDto dto);

    boolean del(String id);
}
