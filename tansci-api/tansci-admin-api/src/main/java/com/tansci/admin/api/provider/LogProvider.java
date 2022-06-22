package com.tansci.admin.api.provider;

import com.tansci.admin.api.domain.LogErrorInfo;
import com.tansci.admin.api.domain.LogInfo;
import com.tansci.admin.api.domain.LoginLog;
import com.tansci.common.core.utils.Wrapper;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName： LogProvider.java
 * @ClassPath： com.tansci.admin.api.provider.LogProvider.java
 * @Description： 日志记录
 * @Author： tanyp
 * @Date： 2022/04/22 9:15
 **/
public interface LogProvider {

    Wrapper<Object> logInfoSave(@RequestBody LogInfo info);

    Wrapper<Object> logErrorInfoSave(@RequestBody LogErrorInfo info);

    Wrapper<Object> loginLogSave(@RequestBody LoginLog loginLog);

}
