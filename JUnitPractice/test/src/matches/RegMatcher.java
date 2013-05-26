package matches;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class RegMatcher extends BaseMatcher<String> {

	private String actual;
	private final String regEx;

	RegMatcher(String regEx) {
		this.regEx = regEx;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(this.actual + " is wrong format");
	}

	@Override
	public boolean matches(Object actual) {
		this.actual = actual.toString();
		return this.actual.matches(regEx);
	}

	public static RegMatcher regMatcher(
			String regEx) {
		return new RegMatcher(regEx);
	}

}
