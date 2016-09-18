package hello.config;

import hello.framework.DomaTransactionManager;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.NoCacheSqlFileRepository;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.SqliteDialect;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:datasource.properties")	//	別の properties ファイルから設定を読み込む
@EnableTransactionManagement
//@EnableJpaRepositories("hello.data.repository")
public class DataSourceConfig {
	private static final String	PROPERTIES_KEY_PREFIX = "spring.datasource.hello";

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
	public LocalTransactionDataSource dataSource() {
		//	組み込みデータベースでなく、外部データベースを利用する
		//	外部データベースがすでにある場合は、application.properties に spring.jpa.hibernate.ddl-auto=none を記載し、hibernate による DB 初期化を無効化すること
		//	※ ddl-auto : none を設定すると hibernate はテーブルすら作らないので注意
		//	外部データベースがまだない場合は、spring.jpa.hibernate.ddl-auto は未設定（デフォルト値）でよい。
		/*
		DataSource	dataSource = DataSourceBuilder
			.create(Application.class.getClassLoader())
			.driverClassName("org.hsqldb.jdbcDriver")
			.url(String.format("jdbc:hsqldb:%s/%s", datadir, database))
			.username(user)
			.password(password)
			.build();
		*/

		// application.propertiesにあるDB設定キーから値を取得し設定します。
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driverClassName"));
		dataSource.setDriverClassName("org.sqlite.JDBC");
		dataSource.setUrl(String.format("jdbc:sqlite:%s/%s.db", datadir, database));
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		// TransactionAwareDataSourceProxy でラッピングしないと Doma 側でコネクションがおかしくなる
//		return new TransactionAwareDataSourceProxy(dataSource);
		return new LocalTransactionDataSource(dataSource);
	}

	//	使ってる？？
	/*
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	*/

	@Bean
	public Dialect dialect() {
		// DBの方言はここで設定します。
		return new SqliteDialect();
	}

	@Bean
	public SqlFileRepository sqlFileRepository() {
		return new NoCacheSqlFileRepository();
	}
	
	@Bean
	public DomaTransactionManager domaTransactionManager() {
		return new DomaTransactionManager();
	}

	@Bean
	public Config config() {

		//	Doma の設定を返す
		return new Config() {

			@Override
			public Dialect getDialect() {
				return dialect();
			}

			@Override
			public DataSource getDataSource() {
				return dataSource();
			}

			@Override
			public SqlFileRepository getSqlFileRepository() {
				return sqlFileRepository();
			}

		};
	}
	
}
