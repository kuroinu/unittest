package ex.practice.variablepattern;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TDD第２回用<br/>
 * キャメルスタイルに変換
 *
 * @author mitsunorinaraoka
 *
 */
public class StringUtil {
	public String convertCamelCase(String target) throws ParseException {
		if (isErrorFormat(target)) {
			throw new ParseException("入力形式の誤り", 0);
		}
		Matcher m = Pattern.compile("_([a-z])").matcher(target.toLowerCase());

		StringBuffer sb = new StringBuffer(target.length());
		while (m.find()) {
			m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	private boolean isErrorFormat(String target) {
		return target.matches(".*_{2,}.*");
	}
}
