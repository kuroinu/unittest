package ex.practice.calc.calucurator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ex.practice.calc.calucurator.Calcurator.PRECALFORMULA;

public class RootCalcurator extends AbstractSubCalcurator {

	@Override
	protected String _calcurate(String target) {
		Matcher matcher = Pattern.compile("√\\d+").matcher(target);
		String returnData = target;
		while (matcher.find()) {
			String value = matcher.group();
			String result = new Sqrt(value.replace(
					PRECALFORMULA.ROOT.getValue(), "")).join(new Equals("0"))
					.interpret();
			returnData = returnData.replace(value, result);
		}
		return returnData;
	}

	@Override
	protected boolean isTarget(String that) {
		return that.matches(".*[" + PRECALFORMULA.ROOT.getValue() + "].*");
	}

}
