package com.kuiper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuiper.domain.SysOrg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName： SysOrgMapper.java
 * @ClassPath： com.kuiper.mapper.SysOrgMapper.java
 * @Description： 组织
 * @Author： tanyp
 * @Date： 2022/4/25 16:21
 **/
@Mapper
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    List<SysOrg> getOrgChildrens(String id);

}
