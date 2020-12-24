package sk.user.auth.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisCacheRespository {

	@Autowired
	RedisTemplate redisTemplate;
	
	HashOperations<String, String, String> hashOps;
	
	public RedisCacheRespository() {
	}
	
	public HashOperations<String, String, String> getHashOps() {

		hashOps = redisTemplate.opsForHash();
		return hashOps;
	}
	
	
	public void putKey(String key, String uniqueKey, String value) {
		hashOps.put(key, uniqueKey, value);
		
	}
	
	public String getValue(String key, String uniqueKey) {
		return hashOps.get(key, uniqueKey);
		
	}
	
	public boolean expireKey(String uniqueKey) {
		return redisTemplate.expire(uniqueKey, 60, TimeUnit.SECONDS);
	}
	
	
}
