package com.kuiper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuiper.domain.SysOrg;
import com.kuiper.domain.SysOrgRole;
import com.kuiper.dto.SysOrgDto;
import com.kuiper.mapper.SysOrgMapper;
import com.kuiper.service.SysOrgRoleService;
import com.kuiper.service.SysOrgService;
import com.kuiper.utils.UserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName： SysOrgServiceImpl.java
 * @ClassPath： com.kuiper.service.impl.SysOrgServiceImpl.java
 * @Description： 组织
 * @Author： tanyp
 * @Date： 2022/4/25 16:34
 **/
@Slf4j
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements SysOrgService {

    @Autowired
    private SysOrgRoleService sysOrgRoleService;

    @Override
    public List<SysOrg> list(SysOrgDto dto) {
        LambdaQueryWrapper queryWrapper = null;
        if (Objects.equals(0, UserInfoContext.getUser().getType())) {
            queryWrapper = Wrappers.<SysOrg>lambdaQuery()
                    .eq(SysOrg::getDelFlag, 0)
                    .like(Objects.nonNull(dto.getName()), SysOrg::getName, dto.getName())
                    .orderByDesc(SysOrg::getCreateTime);
        } else {
            List<SysOrg> orgList = this.baseMapper.getOrgChildrens(UserInfoContext.getUser().getOrgId());
            List<String> orgIds = new ArrayList<>();
            orgIds.add(UserInfoContext.getUser().getOrgId());
            if (Objects.nonNull(orgList) && orgList.size() > 0) {
                orgIds.addAll(orgList.stream().map(SysOrg::getId).collect(Collectors.toList()));
            }

            queryWrapper = Wrappers.<SysOrg>lambdaQuery()
                    .eq(SysOrg::getDelFlag, 0)
                    .in(SysOrg::getId, orgIds)
                    .like(Objects.nonNull(dto.getName()), SysOrg::getName, dto.getName())
                    .orderByDesc(SysOrg::getCreateTime);

        }
        List<SysOrg> orgList = this.baseMapper.selectList(queryWrapper);

        Map<String, List<SysOrg>> map = orgList.stream().collect(Collectors.groupingBy(SysOrg::getParentId, Collectors.toList()));
        orgList.stream().forEach(item -> item.setChildren(map.get(item.getId())));
        return map.get("0").size() > 0 ? map.get("0") : orgList;
    }

    @Override
    public boolean save(SysOrg sysOrg) {
        sysOrg.setCreateTime(LocalDateTime.now());
        sysOrg.setUpdateTime(LocalDateTime.now());
        sysOrg.setDelFlag(0);
        sysOrg.setCreator(UserInfoContext.getUser().getId());
        this.baseMapper.insert(sysOrg);
        return true;
    }

    @Override
    public Object delete(SysOrgDto dto) {
        List<String> ids = new ArrayList<>();
        ids.add(dto.getId());
        List<SysOrg> orgList = this.baseMapper.getOrgChildrens(dto.getId());
        if (Objects.nonNull(orgList) && orgList.size() > 0) {
            ids.addAll(orgList.stream().map(SysOrg::getId).collect(Collectors.toList()));
        }
        this.baseMapper.deleteBatchIds(ids);
        return sysOrgRoleService.remove(Wrappers.<SysOrgRole>lambdaQuery().in(SysOrgRole::getOrgId, ids));
    }

    @Override
    public Object update(SysOrg sysOrg) {
        sysOrg.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(sysOrg);
    }

    @Override
    public List<SysOrg> getOrgChildrens(String id) {
        return this.getOrgChildrens(id);
    }
}
