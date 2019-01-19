package com.mall.oss;


import com.mall.service.SysConfigService;
import com.mall.utils.Constant;
import com.mall.utils.SpringContextUtils;

/**
 * 文件上传Factory
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-26 10:18
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
        if (config.getType() == Constant.CloudServiceEnum.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudServiceEnum.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudServiceEnum.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        }
        return null;
    }

}
