package hello.util;

import java.lang.reflect.Field;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class HibernateMessageDecoder {
	private static final String	UTF8 = "UTF-8";
	private static final String	ISO_8859_1 = "ISO-8859-1";

	public static void decodeObjectErrors(BindingResult bindingResult) {
		for (ObjectError error: bindingResult.getAllErrors()) {
			try {
				//	ObjectError に設定されるメッセージリソースは文字コード指定なしで Properties に読み込まれるため、
				//	一旦デコードして UTF8 に変換する
				byte[]	decoded = error.getDefaultMessage().getBytes(ISO_8859_1);
				String	encoded = new String(decoded, UTF8);

				//	DefaultMessageSourceResolvable.defaultMessage は private final フィールドなので、リフレクションで強制的に書き換える
				Field	field = DefaultMessageSourceResolvable.class.getDeclaredField("defaultMessage");

				field.setAccessible(true);
				field.set(error, encoded);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	}
	
}
