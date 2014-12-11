package hello.config;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanLoader implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public BeanLoader() {
	}

	/**
	 *	指定の ID の Bean を返す
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(final String beanId) {
		assertContext();
		
		return (T)applicationContext.getBean(beanId);
	}
	
	/**
	 *	指定の ID の Bean を返す
	 */
	public static <T> T getBean(final Class<T> clazz) {
		assertContext();
		
		return (T)applicationContext.getBean(clazz);
	}
	
	/**
	 *	指定のクラスを継承する Bean　をすべて返す
	 */
	public static <T> Map<String, T> getBeansOfType(final Class<T> clazz) {
		assertContext();
		
		return applicationContext.getBeansOfType(clazz);
	}
	
	private static void assertContext() {
		if (applicationContext == null) {
			throw new UnsupportedOperationException("Set this class as bean to your @Configuration class.");
		}
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		BeanLoader.applicationContext = applicationContext;
	}

}
