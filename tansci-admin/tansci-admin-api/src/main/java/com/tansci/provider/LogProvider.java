package com.tansci.provider;

import com.tansci.domain.LogErrorInfo;
import com.tansci.domain.LogInfo;
import com.tansci.utils.Wrapper;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName： LogProvider.java
 * @ClassPath： com.tansci.provider.LogProvider.java
 * @Description： 日志记录
 * @Author： tanyp
 * @Date： 2022/04/22 9:15
 **/
public interface LogProvider {

    Wrapper<Object> logInfoSave(@RequestBody LogInfo info);

    Wrapper<Object> logErrorInfoSave(@RequestBody LogErrorInfo info);

}
