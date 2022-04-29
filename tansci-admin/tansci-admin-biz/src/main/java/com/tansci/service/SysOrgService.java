package com.kuiper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kuiper.domain.SysOrg;
import com.kuiper.dto.SysOrgDto;

import java.util.List;

/**
 * @ClassName： SysOrgService.java
 * @ClassPath： com.kuiper.service.SysOrgService.java
 * @Description： 组织
 * @Author： tanyp
 * @Date： 2022/4/25 16:25
 **/
public interface SysOrgService extends IService<SysOrg> {

    List<SysOrg> list(SysOrgDto dto);

    boolean save(SysOrg sysOrg);

    Object delete(SysOrgDto dto);

    Object update(SysOrg sysOrg);

    List<SysOrg> getOrgChildrens(String id);

}
