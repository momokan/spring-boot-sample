package hello.framework;

import org.seasar.doma.AnnotateWith;
import org.seasar.doma.Annotation;
import org.seasar.doma.AnnotationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//	@AnnotationWith で、Doma に自動生成される対象クラスに追加で付与するアノテーションを設定する
@AnnotateWith(annotations = {
		@Annotation(target = AnnotationTarget.CLASS, type = Component.class),
//		@Annotation(target = AnnotationTarget.CLASS, type = Repository.class),
		@Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Autowired.class)
})
public @interface ConfigAutowireable {
}