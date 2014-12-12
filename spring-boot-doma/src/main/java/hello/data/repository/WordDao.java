package hello.data.repository;

import hello.data.entity.Word;
import hello.framework.ConfigAutowireable;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

@Dao
@ConfigAutowireable
public interface WordDao {

	@Select
	public List<Word> selectAll();

	@Select
	public List<Word> selectByType(int type);

	@Select
	public List<Word> selectByContent(String content);

	@Select
	public List<Word> selectLikeContent(String content);

	@Insert
	public int insert(Word word);

}
