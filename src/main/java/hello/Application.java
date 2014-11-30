package hello;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

//	読み込む properties ファイルを変更する
//@PropertySources(value = {@PropertySource("classpath:application2.properties")})
//@PropertySource("classpath:application.properties")
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableJpaRepositories
public class Application {

	/**
	 *	データソースを設定する
	 *	未設定なら自動でクラスパス上の組み込みデータベースが利用される。
	 */
//	@ConfigurationProperties(prefix = DataSourceAutoConfiguration.CONFIGURATION_PREFIX)
	@Bean
	public DataSource dataSource() {
		//	組み込みデータベースでなく、外部データベースを利用する
		//	application.properties に spring.jpa.hibernate.ddl-auto=none を記載し、hibernate による DB 初期化を無効化すること
		//	※ ddl-auto : none を設定すると hibernate はテーブルすら作らないので注意
		DataSource	dataSource = DataSourceBuilder
			.create(Application.class.getClassLoader())
			.driverClassName("org.hsqldb.jdbcDriver")
			.url("jdbc:hsqldb:databaseDir/database1")
			.username("user1")
			.password("password1")
			.build();

		return dataSource;
	}
	
	/**
	 *	メッセージリソースを UTF-8 で読み込む
	 */
	/*
	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		//	properties ファイルを指定する
		messageSource.setBasename("classpath:myMessages");
		//	properties の文字コードを指定する
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}
	*/

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
