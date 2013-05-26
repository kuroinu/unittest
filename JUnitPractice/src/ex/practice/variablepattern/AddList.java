package ex.practice.variablepattern;

import java.util.ArrayList;
import java.util.List;

/**
 * TDD第２回<br/>
 * void型のテスト
 *
 * @author mitsunorinaraoka
 *
 */
public class AddList {
	private final List<Integer> l = new ArrayList<Integer>();

	public void add() {
		l.add(l.size());
	}

	public List<Integer> getList() {
		return new ArrayList<Integer>(l);
	}
}
