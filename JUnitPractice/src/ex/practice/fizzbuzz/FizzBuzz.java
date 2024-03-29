package ex.practice.fizzbuzz;

/**
 * TDD勉強会１回目のサンプル<br/>
 * TDD勉強会3回目のサンプル、actionForRandmNumberを追加<br/>
 * FizzBuzzの実行を行う
 * @author mitsunorinaraoka
 *
 */
public final class FizzBuzz {
	public String action(int number) {
		return new StringBuffer(number % 3 == 0 ? "Fizz" : "")
				.append(number % 5 == 0 ? "Buzz" : "")
				.append(number % 3 != 0 && number % 5 != 0 ? String
						.valueOf(number) : "").toString();
	}

	/**
	 * ランダムな数値とFizzBuzzの評価結果を返却
	 * @return
	 */
	public RtnData actionForRandomNumber() {
		return  getFizzBuzzRandomNumber(new Randomizer());
	}

	/**
	 * ランダム生成された文字列とその組み合わせを返却
	 * @param randmizer
	 * @return
	 */
	public RtnData getFizzBuzzRandomNumber(IRandmizer randomizer) {
		int number = randomizer.generic();
		return new RtnData(number,action(number));
	}

	public static class RtnData {
		public final int number;
		public final String result;
		public RtnData(int number,String result) {
			this.number = number;
			this.result = result;
		}
	}
}
