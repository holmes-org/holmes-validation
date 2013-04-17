package org.holmes.evaluator.support;

/**
 * A {@link Number} class that wraps an Number instance which is unknown at
 * first.
 * 
 * @author diegossilveira
 */
public class FutureNumber extends Number {

	private static final long serialVersionUID = 1L;

	private Number wrapped;

	@Override
	public int intValue() {

		selfCheck();
		return wrapped.intValue();
	}

	@Override
	public long longValue() {

		selfCheck();
		return wrapped.longValue();
	}

	@Override
	public float floatValue() {

		selfCheck();
		return wrapped.floatValue();
	}

	@Override
	public double doubleValue() {

		selfCheck();
		return wrapped.doubleValue();
	}

	@Override
	public String toString() {

		selfCheck();
		return wrapped.toString();
	}

	public void wrap(Number number) {

		wrapped = number;
	}

	private void selfCheck() {

		if (wrapped == null) {

			throw new IllegalStateException("Unknown wrapped number.");
		}
	}

}
