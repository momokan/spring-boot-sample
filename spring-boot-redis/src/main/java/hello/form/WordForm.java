package hello.form;

import hello.data.entity.Word;

import java.util.List;

import javax.validation.constraints.NotNull;

public class WordForm {

	//	リクエストパラメーターをマッピングする Form に別のオブジェクトをマップすることもできる
	@NotNull
	private Word		word;

	private String	memo;

	private List<String>	relationalWord;

	public Word getWord() {
		return word;
	}
	public void setWord(Word word) {
		this.word = word;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<String> getRelationalWord() {
		return relationalWord;
	}
	public void setRelationalWord(List<String> relationalWord) {
		this.relationalWord = relationalWord;
	}
		
}
