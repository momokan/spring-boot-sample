package hello.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Word {

	@Id
	@GeneratedValue
	private Long	id;

	@NotNull				//	必須として規定する
	@Size(min=2, max=30)	// 文字列の長さを規定する
	private String	content;

	@NotNull(message = "{net.chocolapod.springbootsample.Word.content.NotNull}")
	@Min(1)				//	最小値を規定する
    @Max(2)				//	最大値を規定する
	private Integer	type;

	/**
	 *	テンプレートからオブジェクトの private フィールドにアクセスする際にはアクセサメソッドが必要になる
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}