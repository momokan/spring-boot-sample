package hello.exception;

import hello.config.BeanLoader;

import org.springframework.context.MessageSource;

public class HelloException extends Exception {

	public HelloException() {
		super(loadMessage());
	}
	
	public static String loadMessage() {
		MessageSource		messageSource = BeanLoader.getBean("errorMessageSource");
		Object[]			args = null;
		String				message = messageSource.getMessage(HelloException.class.getCanonicalName(), args, null);
		
		return message;
	}
}
