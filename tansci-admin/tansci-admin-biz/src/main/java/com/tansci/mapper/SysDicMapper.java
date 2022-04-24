package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SysDic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @path：com.tansci.mapper.SysDicMapper.java
 * @className：SysDicMapper.java
 * @description：字典管理
 * @author：tanyp
 * @dateTime：2022/2/15 11:42
 * @editNote：
 */
@Mapper
public interface SysDicMapper extends BaseMapper<SysDic> {

    List<SysDic> getDicChildrens(String id);
}
