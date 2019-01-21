package com.mall.service;

import com.mall.entity.SysSmsLogEntity;
import com.mall.service.*;

/**
 * 发送短信日志Service
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-12-16 23:38:05
 */
public interface SysSmsLogService extends com.mall.service.BaseService<SysSmsLogEntity> {

    SysSmsLogEntity sendSms(SysSmsLogEntity smsLog);
}
