package hello.auth.redis.cache;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CachedDateManager {
	private static final SimpleDateFormat	DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	
	@Cacheable("dates")
	public CachedDate getDate() {
		return newDate();
	}

	public CachedDate newDate() {
		return new CachedDate(DATE_FORMAT.format(new Date()));
	}
	
	@CachePut("dates")
	public CachedDate createDate() {
		//	キャッシュを生成する
		return newDate();
	}

	@CacheEvict("dates")
	public void deleteDate() {
		//	キャッシュを削除する
	}
}