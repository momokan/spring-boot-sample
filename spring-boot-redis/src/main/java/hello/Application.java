package hello;

import hello.auth.redis.cache.RedisCacheConfig;
import hello.config.BeanLoader;
import hello.config.DataSourceConfig;
import hello.util.template.TemplateLogic;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.CharacterEncodingFilter;

//	読み込む properties ファイルを変更する
//@PropertySources(value = {@PropertySource("classpath:application2.properties")})
//@PropertySource("classpath:application.properties")
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableJpaRepositories
@Import({
	DataSourceConfig.class,
	RedisCacheConfig.class
})
public class Application {

	/**
	 *	エラーメッセージ用の MessageSource を登録する
	 */
	@Bean()
	public MessageSource errorMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		//	properties ファイルを指定する
		messageSource.setBasename("classpath:errorMessages");

		//	properties の文字コードを指定する
		messageSource.setDefaultEncoding("UTF-8");

		//	propteries または該当するメッセージが見つからない場合に、例外を投げずにキーを返す
		messageSource.setUseCodeAsDefaultMessage(true);

		//	メッセージリソースのキャッシュを無効にする
		messageSource.setCacheSeconds(0);

		return messageSource;
	}
	
	/**
	 *	テンプレート用ロジックの TemplateLogic を Bean として登録する
	 */
	@Bean
	public TemplateLogic templateLogic() {
		return new TemplateLogic();
	}

	/**
	 *	BeanContainer を Bean として登録する
	 */
	@Bean
	public BeanLoader beanContainer() {
		//	メソッド名を ID として Bean が取得できる
		return new BeanLoader();
	}
	
	/**
	 *	CharacterEncodingFilter を bean として登録する
	 */
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		
		//	HTTP リクエストパラメーターの文字コードを設定する 
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);

		return filter;
	}
	
	/**
	 *	WEB アプリの実行クラス（Tomcat 込み）
	 *	プロジェクトをパッケージするとこのクラスが実行される war が作られる。
	 *	開発時も、純粋にこのクラスを実行してデバッグすればよい。
	 */
	public static void main(String[] args) {
		//	実際はこれだけでいい
//		SpringApplication.run(Application.class);

		//	WEB アプリを走らせつついろいろやってみる
		ApplicationContext	ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[]			beanNames = ctx.getBeanDefinitionNames();

		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

}
