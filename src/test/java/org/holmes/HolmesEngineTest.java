package org.holmes;

import java.util.Calendar;
import java.util.Date;

import org.holmes.evaluator.support.Diff;

public class HolmesEngineTest {

	public static void main(String[] args) {

		HolmesEngine e = HolmesEngine.init();

		Calendar c = Calendar.getInstance();
		c.set(1903, Calendar.APRIL, 18);
		Date birthDate = c.getTime();

		e.ensureThat(birthDate).applying(Diff.toNow().inYears()).isGreaterThanOrEqualTo(110).otherwise("age.violation");
		e.ensureThat(new byte[0]).isValidBy(new Validator<byte[]>() {

			@Override
			public boolean isValid(byte[] target) {
				return true;
			}
		}).otherwise("byte.violation");

		e.run();
	}

}
