package ex.practice.calc.validator;

public abstract class Validator {
	private Validator nextHandler;

	public Validator setNextHandler(Validator nextHandler) {
		this.nextHandler = nextHandler;
		return this;
	}

	public boolean isCongruence(String target) {
		if (hasNext() && !nextHandler.valid(target)) {
			return false;
		} else {
			return valid(target);
		}
	}

	protected abstract boolean valid(String target);

	private boolean hasNext() {
		return nextHandler != null;
	}

}
