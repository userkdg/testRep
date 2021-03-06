package com.ishop.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ishop.common.util.IshopResult;
import com.ishop.sso.dao.JedisClient;
import com.ishop.sso.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public IshopResult syncContent(long contentCid) {
		try {
		 jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
		} catch (Exception e) {
			e.printStackTrace();
			return IshopResult.build(500, "Exception!!!");
		}
		return IshopResult.ok();
	}

}
