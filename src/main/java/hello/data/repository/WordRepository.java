package hello.data.repository;

import hello.data.entity.Word;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 *	Since we haven't defined a DataSource if Spring finds an in memory JDBC driver in the Classpath it will automatically create one for us using a set of predefined conventions
 * 
 * 	classpath 上の data.sql が起動時に自動実行される。
 */
public interface WordRepository extends CrudRepository<Word, Long> {

	public Iterable<Word> findWordsByType(@Param("type") Integer type);
	public Iterable<Word> findWordsByContent(@Param("content") String content);

}