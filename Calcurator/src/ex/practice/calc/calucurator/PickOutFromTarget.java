package ex.practice.calc.calucurator;

import java.util.ArrayList;
import java.util.List;

class PickOutFromTarget {

	private PickOutFromTarget() {

	}

	static PickOutFromTarget newInstance() {
		return new PickOutFromTarget();
	}

	/**
	 * 数値の抽出
	 *
	 * @param target
	 * @return
	 */
	List<String> numbers(String[] target) {
		List<String> list = new ArrayList<String>();
		for (String str : target) {
			list.add(str);
		}
		return list;
	}
}
