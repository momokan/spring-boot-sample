package hello.config;

import hello.Application;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:datasource.properties")	//	別の properties ファイルから設定を読み込む
@EnableTransactionManagement
@EnableJpaRepositories("hello.data.repository")
public class DataSourceConfig {
	private static final String	CONFIGURATION_PREFIX = "spring.datasource";
	private static final String	PROPERTIES_KEY_PREFIX = CONFIGURATION_PREFIX + ".hello";

	@Value("${" + PROPERTIES_KEY_PREFIX + ".datadir}")
	protected String	datadir;

	@Value("${" + PROPERTIES_KEY_PREFIX + ".database}")
	protected String	database;

	@Value("${" + PROPERTIES_KEY_PREFIX + ".user}")
	protected String	user;
	
	@Value("${" + PROPERTIES_KEY_PREFIX + ".password}")
	protected String	password;

	/**
	 *	データソースを設定する
	 *	未設定なら自動でクラスパス上の組み込みデータベースが利用される。
	 */
	@Bean
	public DataSource dataSource() {
		//	組み込みデータベースでなく、外部データベースを利用する
		//	外部データベースがすでにある場合は、application.properties に spring.jpa.hibernate.ddl-auto=none を記載し、hibernate による DB 初期化を無効化すること
		//	※ ddl-auto : none を設定すると hibernate はテーブルすら作らないので注意
		//	外部データベースがまだない場合は、spring.jpa.hibernate.ddl-auto は未設定（デフォルト値）でよい。	
		DataSource	dataSource = DataSourceBuilder
			.create(Application.class.getClassLoader())
			.driverClassName("org.hsqldb.jdbcDriver")
			.url(String.format("jdbc:hsqldb:%s/%s", datadir, database))
			.username(user)
			.password(password)
			.build();

		return dataSource;
	}

}
