package org.holmes.evaluator.support;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a unit of time
 * 
 * @author diegossilveira
 */
public enum TimeUnit {

	MILLISECOND {

		@Override
		public Number fromMillis(long millis) {

			return millis;
		}
	},
	
	SECOND {
		
		@Override
		public Number fromMillis(long millis) {

			return divide(millis, MILLIS_PER_SECOND);
		}
	},
	
	MINUTE {

		@Override
		public Number fromMillis(long millis) {

			return divide(millis, MILLIS_PER_MINUTE);
		}
	},
	
	HOUR {

		@Override
		public Number fromMillis(long millis) {

			return divide(millis, MILLIS_PER_HOUR);
		}
	},
	
	DAY {

		@Override
		public Number fromMillis(long millis) {

			return divide(millis, MILLIS_PER_DAY);
		}
	},
	
	MONTH {

		@Override
		public Number fromMillis(long millis) {

			return divide(millis, MILLIS_PER_MONTH);
		}
	},
	
	YEAR {
		
		@Override
		public Number fromMillis(long millis) {

			return divide(millis, MILLIS_PER_YEAR);
		}
	};
	
	private static final long MILLIS_PER_SECOND = 1000;
	
	private static final long MILLIS_PER_MINUTE = MILLIS_PER_SECOND * 60;
	
	private static final long MILLIS_PER_HOUR = MILLIS_PER_MINUTE * 60;
	
	private static final long MILLIS_PER_DAY = MILLIS_PER_HOUR * 24;
	
	private static final long MILLIS_PER_MONTH = MILLIS_PER_DAY * 30;
	
	private static final long MILLIS_PER_YEAR = 31556928000l;

	/**
	 * Converts the milliseconds amount into the specific time unit.
	 * 
	 * @param millis
	 * @return
	 */
	public abstract Number fromMillis(long millis);
	
	private static Number divide(long numerator, long denominator) {
		
		return new BigDecimal(numerator).divide(new BigDecimal(denominator), 10, RoundingMode.UP).doubleValue();
	}

}
