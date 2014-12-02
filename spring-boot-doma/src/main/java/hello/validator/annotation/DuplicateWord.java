package hello.validator.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import hello.validator.DuplicateWordValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DuplicateWordValidator.class)
@Documented
public @interface DuplicateWord {

	public String message() default "{hello.validator.DuplicateWord}";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};

//	public CaseMode value();

}