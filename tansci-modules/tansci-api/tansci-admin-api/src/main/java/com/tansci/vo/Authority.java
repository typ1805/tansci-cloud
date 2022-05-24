package com.tansci.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： Authority.java
 * @ClassPath： com.tansci.vo.Authority.java
 * @Description：
 * @Author： tanyp
 * @Date： 2022/2/11 11:19
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements Serializable {

    private String authority;

}
