package hello.validator;

import hello.data.entity.Word;
import hello.data.repository.WordDao;
import hello.validator.annotation.DuplicateWord;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * カスタムバリデーターについての諸注意
 * 
 *	・Hibernate / JPA を利用する場合は、DB に格納される段階でのバリデーションで Spring による Dao の DI が機能しない（アクションが呼ばれる段階のバリデーションでは DI されている）が、
 * 	　Doma を利用する場合は Conrtoller からアクションが呼ばれる段階でしか Hibernate Validator が走らないので、問題なく動作する。
 *
 *	・アノテーションによるバリデーションでは、アノテーションのつけられたパラメーターのみ参照することができる。
 *	　複数のパラメーターの組み合わせをバリデーションする場合には、コントローラーにてバリデーションを行い、BindingResult にエラーを詰める方式がよさそう。
 */
@Component
public class DuplicateWordValidator implements ConstraintValidator<DuplicateWord, String> {
	private DuplicateWord constraintAnnotation;

	@Autowired
	protected WordDao	wordDao;

	public void initialize(DuplicateWord constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}

	public boolean isValid(String target, ConstraintValidatorContext constraintContext) {
		if (target == null) {
			return true;
		}

		//	すでに同じ単語が登録されていれば、エラーにする
		boolean			isValid = true;
		Iterable<Word>	word = wordDao.selectByContent(target);
		
		if (word.iterator().hasNext()) {
			isValid = false;
		}

		return isValid;
	}

}
