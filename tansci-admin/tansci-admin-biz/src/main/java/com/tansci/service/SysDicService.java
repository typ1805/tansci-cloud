package com.tansci.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SysDic;
import com.tansci.dto.SysDicDto;

import java.util.List;


/**
 * @path：com.tansci.service.SysDicService.java
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
