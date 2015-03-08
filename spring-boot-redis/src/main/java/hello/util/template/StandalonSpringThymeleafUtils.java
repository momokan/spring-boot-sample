package hello.util.template;

import hello.config.BeanLoader;
import hello.form.SigninForm;

import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.naming.SpringContextVariableNames;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 *	Spring　用の WebContext を使いつつ（= spring-thymeleaf 用のテンプレートをレンダリングするために）
 *	Thymeleaf をスタンドアローンで利用するためのクラス
 */
public class StandalonSpringThymeleafUtils {

	public static SpringWebContext createContext(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object>	model = new HashMap<>();
		SpringWebContext	context = new SpringWebContext(request, response, request.getServletContext(), Locale.getDefault(), model, BeanLoader.getApplicationContext());

		context.setVariable(SpringContextVariableNames.SPRING_REQUEST_CONTEXT, new RequestContext(request, request.getServletContext()));
		
		return context;
	}
	
	public static void render(String templatePath, SpringWebContext context, Writer writer) {
		//	TemplateResolver などを Bean として登録している場合はそちらを利用するようにするとよい
		TemplateResolver	templateResolver = new TemplateResolver();
		TemplateEngine		engine = new TemplateEngine();
		
		templateResolver.setCacheable(false);
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setResourceResolver(new ClassLoaderResourceResolver());

		engine.setDialect(new SpringStandardDialect());
		engine.setTemplateResolver(templateResolver);
		engine.process(templatePath, context, writer);
	}
	
}
