package com.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mall.dao.SysLogDao;
import com.mall.entity.SysLogEntity;
import com.mall.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
public class SysLogServiceImpl extends com.mall.service.impl.BaseServiceImpl<SysLogEntity,SysLogDao> implements SysLogService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<SysLogEntity> queryList(Map<String, Object> map) {
        List<SysLogEntity> list = getDao().queryList(map);

        for (SysLogEntity sysLogEntity : list) {
        	sysLogEntity.setIp(getIpDetails(sysLogEntity.getIp()));
        }
        return list;
    }
    /**
     * 向指定URL发送GET方法的请求
     */
    public  String getIpDetails(String ip) {
    	String str=null;
    	
    	if(ip.startsWith("0:") ||ip.startsWith("0.") || ip.startsWith("127.") ){
    		return str;
    	}
		try {
			str = restTemplate.getForObject("http://ip.taobao.com/service/getIpInfo.php?ip="+ip, String.class);
	    	JSONObject jsonObject = JSONObject.parseObject(str.toString());
	    	
	    	//{"code":0,"data":{"ip":"1.1.1.1","country":"澳大利亚","area":"","region":"XX","city":"XX","county":"XX","isp":"XX","country_id":"AU","area_id":"","region_id":"xx","city_id":"xx","county_id":"xx","isp_id":"xx"}}
	    	jsonObject =(JSONObject) jsonObject.get("data");
	    	
	    	str =ip + ":" + jsonObject.getString("country") + " " + jsonObject.getString("region") + " "
            + jsonObject.getString("city") + " " + jsonObject.getString("county") + " " + jsonObject.getString("isp");
		} catch (RestClientException e) {
			str=ip;
  		}
    	return str;   
    }
}
