package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


//@Configuration
public class ThymeleafConfig {

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ServletContextTemplateResolver	servletContextTemplateResolver = new ServletContextTemplateResolver();

		servletContextTemplateResolver.setPrefix("/WEB-INF/templates/");
		servletContextTemplateResolver.setSuffix(".html");
		servletContextTemplateResolver.setTemplateMode("HTML5");
		servletContextTemplateResolver.setCharacterEncoding("UTF-8");
		servletContextTemplateResolver.setCacheable(true);

		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(servletContextTemplateResolver);

		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setCharacterEncoding("utf-8");
		thymeleafViewResolver.setTemplateEngine(springTemplateEngine);

		return thymeleafViewResolver;
	}

}