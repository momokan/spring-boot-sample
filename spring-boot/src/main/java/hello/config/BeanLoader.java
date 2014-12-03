package hello.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanLoader implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public BeanLoader() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(final String beanId) {
		if (applicationContext == null) {
			throw new UnsupportedOperationException("Set this class as bean to your @Configuration class.");
		}
		
		return (T)applicationContext.getBean(beanId);
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		BeanLoader.applicationContext = applicationContext;
	}

}
