package hello.framework;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomaTransactionManager {

	@Autowired
	protected Config	config;

	@Autowired
	protected LocalTransactionDataSource	dataSource;

	public LocalTransaction getLocalTransaction() {
		return dataSource.getLocalTransaction(config.getJdbcLogger());
	}

}
