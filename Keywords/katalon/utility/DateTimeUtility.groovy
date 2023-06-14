package katalon.utility
import java.text.SimpleDateFormat
import java.time.LocalDateTime

public class DateTimeUtility {

	public String getCurrentDateTime(String format = "ddMMyyHHmmss") {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	public static String getDateTimeFromCurrent(int days = 0) {
		def currentDateTime = LocalDateTime.now()
		def dateFromCurrent = currentDateTime.plusDays(days)
		return dateFromCurrent
	}

	public static String plusDays(int numberOfDays, String format = "ddMMyyHHmmss") {
		Date date = new Date().plus(numberOfDays);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	public static String currentDay(String format = "MMMMM d, YYYY") {
		plusDays(0, format);
	}

	public static String next30Days(String format = "MMMM d, YYYY") {
		plusDays(30, format);
	}
	
	public static String nextYear(String format = "MMMM d, YYYY") {
		def CurrentYear = Calendar.getInstance().get(Calendar.YEAR)
		if (CurrentYear % 4 == 0 && CurrentYear % 100 != 0 || CurrentYear % 400 == 0) {
			// Leap year: 366 days
			def days = 366 + 1
			plusDays(days, format);
		} else {
			// Non-leap year: 365 days
			def days = 365 + 1
			plusDays(days, format);
		}
	}
	
}
