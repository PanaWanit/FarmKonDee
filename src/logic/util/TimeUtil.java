package logic.util;

/**
 * This class is a Time Utilities for the game
 */
public class TimeUtil {
	/**
	 * Utility return String of time format
	 * 
	 * @param time dayTime
	 * @return time format in 12 hour system<br>
	 *         time = 0 is 8:00 AM
	 */
	public static String timeFormat(int time) {
		int hour = 8 + time / 60;
		int displayHour = (hour - 1) % 12 + 1;
		int minute = time % 60;
		return String.format("%2d", displayHour) + ":" + String.format("%02d", minute) + " "
				+ (hour < 12 ? "AM" : "PM");
	}

	/**
	 * Utility return current hour
	 * 
	 * @param time dayTime
	 * @return hour time format in 24 hour system<br>
	 *         time = 0 is 8
	 */
	public static int currentHour(int time) {
		int hour = 8 + time / 60;
		return hour;
	}
}
