package hello.data.entity.repository;

import hello.data.entity.Word;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 *	Since we haven't defined a DataSource if Spring finds an in memory JDBC driver in the Classpath it will automatically create one for us using a set of predefined conventions
 * 
 * 	classpath 上の data.sql が起動時に自動実行される。
 */
public interface WordRepository extends CrudRepository<Word, Long> {

	public List<Word> findWordsByContent(@Param("content") String content);

}