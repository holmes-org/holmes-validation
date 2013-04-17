package org.holmes.evaluator.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeUnitTest {

	@Test
	public void testSuccessfulMillis2SecondsConversion() {
		
		assertEquals(1.0, TimeUnit.SECOND.fromMillis(1000l));
		assertEquals(1.5, TimeUnit.SECOND.fromMillis(1500l));
	}
	
	@Test
	public void testSuccessfulMillis2MinutesConversion() {
		
		assertEquals(1.0, TimeUnit.MINUTE.fromMillis(60000l));
		assertEquals(30.5, TimeUnit.MINUTE.fromMillis(1830000l));
	}
	
	@Test
	public void testSuccessfulMillis2HoursConversion() {
		
		assertEquals(1.0, TimeUnit.HOUR.fromMillis(3600000l));
		assertEquals(16.0, TimeUnit.HOUR.fromMillis(57600000l));
		assertEquals(16.85, TimeUnit.HOUR.fromMillis(60660000l));
	}
	
	@Test
	public void testSuccessfulMillis2DaysConversion() {
		
		assertEquals(1.0, TimeUnit.DAY.fromMillis(86400000l));
		assertEquals(285.0, TimeUnit.DAY.fromMillis(24624000000l));
	}
	
	@Test
	public void testSuccessfulMillis2MonthsConversion() {
		
		assertEquals(1.0, TimeUnit.MONTH.fromMillis(2592000000l));
		assertEquals(12.0, TimeUnit.MONTH.fromMillis(31104000000l));
		assertEquals(3.61, TimeUnit.MONTH.fromMillis(9357120000l));
	}
	
	@Test
	public void testSuccessfulMillis2YearsConversion() {
		
		assertEquals(1.0, TimeUnit.YEAR.fromMillis(31536000000l));
		assertEquals(0.98, TimeUnit.YEAR.fromMillis(30905280000l));
	}
	
}
