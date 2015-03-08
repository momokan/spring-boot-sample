package hello.auth.redis.cache;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.jredis.JredisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
@PropertySource("redis.properties")
public class RedisCacheConfig extends CachingConfigurerSupport {

	@Value("${redis.host}")
	private String	redisHost;

	@Value("${redis.port}")
	private int		redisPort;

	@Value("${redis.database}")
	private int		redisDatabase;
	
	@Value("${redis.cache.expiration}")
	private long	redisExpiration;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

		redisConnectionFactory.setHostName(redisHost);
		redisConnectionFactory.setPort(redisPort);
		redisConnectionFactory.setDatabase(redisDatabase);
		redisConnectionFactory.setUsePool(true);
		
		return redisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();

		redisTemplate.setConnectionFactory(redisConnectionFactory());

		return redisTemplate;
	}
	
	@Bean
	public CacheManager cacheManager() {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
		
		//	デフォルトの有効期限 [秒] を設定する。無制限の場合は 0
		cacheManager.setDefaultExpiration(redisExpiration);

		//	キャッシュ毎の有効期限を設定する
		HashMap<String, Long> expiresMap = new HashMap<>();
		
		expiresMap.put("dates", redisExpiration);
		
		cacheManager.setExpires(expiresMap);

		return cacheManager;
	}
}
