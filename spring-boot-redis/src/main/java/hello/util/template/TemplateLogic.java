package hello.util.template;

import hello.data.entity.Word;

/**
 *	テンプレートで呼び出すロジックのサンプル
 */
public class TemplateLogic {
	
	/**
	 *	指定のオブジェクトの名前をハッシュ付きの文字列として返す
	 */
	public String getHash(Word word) {
		return Word.class.getCanonicalName() + "@" + word.hashCode();
	}

}
