package org.holmes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This class provides a way to extract {@link Date} informations.
 * 
 * @author arthuralmeida
 */
public class Diff {

	private Date date;

	private Diff(Date date) {
		this.date = date;
	}

	/**
	 * Initialize a diff.
	 * 
	 * @param date
	 * @return
	 */
	public static Diff to(Date date) {
		return new Diff(date);
	}

	/**
	 * Gets the day of the month in which the date is.
	 * 
	 * @return
	 */
	public int inDays() {
		return get(Calendar.DATE);
	}

	/**
	 * Gets the month of the year in which the date is.
	 * 
	 * @return
	 */
	public int inMonths() {
		return get(Calendar.MONTH);
	}

	/**
	 * Gets the year in which the date is.
	 * 
	 * @return
	 */
	public int inYears() {
		return get(Calendar.YEAR);
	}

	private int get(int type) {
		if (date == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(type);
	}

	public static void main(String[] args) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, 1970);

		Date teste = calendar.getTime();

		System.out.println(formatter.format(teste));
		System.out.println(teste.getTime());
		System.out.println(TimeUnit.MILLISECONDS.toDays(teste.getTime()));
	}
}
