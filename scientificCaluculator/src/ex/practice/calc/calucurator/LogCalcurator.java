package ex.practice.calc.calucurator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ex.practice.calc.calucurator.Calcurator.PRECALFORMULA;

public class LogCalcurator extends AbstractSubCalcurator {

	@Override
	protected String _calcurate(String target) {
		Matcher matcher = Pattern
				.compile(PRECALFORMULA.LOG.getValue() + "\\d+").matcher(target);
		String returnData = target;
		while (matcher.find()) {
			String value = matcher.group();
			String result = new Log(value.replace(PRECALFORMULA.LOG.getValue(),
					"")).join(new Equals("0")).interpret();
			returnData = returnData.replace(value, result);
		}
		return returnData;
	}

	@Override
	protected boolean isTarget(String that) {
		return that.matches(".*[" + PRECALFORMULA.LOG.getValue() + "].*");
	}

}
