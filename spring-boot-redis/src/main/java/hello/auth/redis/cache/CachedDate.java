package hello.auth.redis.cache;

import java.io.Serializable;

public class CachedDate implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String	dateString;

	public CachedDate(String dateString) {
		this.dateString = dateString;
	}

	public String getDateString() {
		return dateString;
	}

}
