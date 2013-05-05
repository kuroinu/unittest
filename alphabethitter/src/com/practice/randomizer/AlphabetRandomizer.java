package com.practice.randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlphabetRandomizer {
	private static final String[] ALPHABETS = { "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	private final List<String> generateList;

	private AlphabetRandomizer() {
		generateList = new ArrayList<String>();
	}

	public static final AlphabetRandomizer newInstance() {
		return new AlphabetRandomizer();
	}

	/**
	 * ユニークなアルファベットを返却
	 *
	 * @return
	 */
	public String getUniqueRandomAlphabet() {
		while (true) {
			String alphabet = getRandomAlphabet();
			if (!generateList.contains(alphabet)) {
				generateList.add(alphabet);
				return alphabet;
			}
		}
	}

	public void clearance() {
		generateList.clear();
	}

	/**
	 * アルファベットをランダムで返却
	 *
	 * @return
	 */
	public String getRandomAlphabet() {
		return ALPHABETS[randomIndex()];
	}

	private int randomIndex() {
		return new Random().nextInt(ALPHABETS.length);
	}
}
