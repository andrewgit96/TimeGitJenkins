package time;
//Andrew Pereira 991604016
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {
	
	
	@Test
	void testGetTotalSecondsGood() {
		//fail("Not yet implemented");
		int seconds = Time.getTotalSeconds("05:05:05:555");
		assertTrue("The seconds were not calculated properly", seconds==18305); //chose to keep whole number(int) instead of float with decimal
	}

	@Test
	void testGetTotalSecondsBad() {
		assertThrows(
			StringIndexOutOfBoundsException.class, ()-> { //pass it the class reference 
				Time.getTotalSeconds("10:00");});
		}
	
	@ParameterizedTest
	@ValueSource(strings = {"00:00:00:000", "23:59:59:999"}) 
	void TestGetTotalSecondsBoundary(String candidate){
		int seconds = Time.getTotalSeconds(candidate);
		assertTrue("seconds were not calculated properly", seconds >= 00 && seconds <= 86399 ); //hh:mm:ss max is 23:59:59
	}
	
	@Test
	void testGetSecondsGood() {
		int seconds = Time.getSeconds("05:05:30:000");
		assertTrue("seconds were not calculated properly", seconds == 30);
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows (StringIndexOutOfBoundsException.class, () -> {
			Time.getSeconds("88"); } );
		}
	
	@ParameterizedTest
	@ValueSource(strings = {"00:00:00", "00:00:30", "00:00:59"}) 
	void testGetSecondsBoundary(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue("The seconds were not calculated properly", seconds >= 0 && seconds <= 59);
	}
	
	
	@Test
	void testGetTotalMinutesGood() {
		int minutes = Time.getTotalMinutes("00:30:00");
		assertTrue("minutes not calculated properly", minutes == 30);
	}
	
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class, () -> {
			Time.getTotalMinutes("00:111:00:000"); //time.length > 12
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"05:03:00:000", "05:03:59:000"}) //testing lower and upper end of 3 minutes with 00 and 59 secs
	void TestGetMinutesBoundary(String candidate){
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue("minutes were not calculated properly", minutes ==3);
	}
	

	@Test
	void testGetTotalHoursGood() {
		int hours = Time.getTotalHours("12:00:00:000");
		assertTrue("hours not calculated properly", hours == 12);
	}
	
	@Test
	void testGetTotalHoursBad() {
		assertThrows (NumberFormatException.class, ()-> { 
			Time.getTotalHours(" 00 ");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"05:00:00:000", "05:59:59:999"}) //testing lower and upper end of 5 hours
	void TestGetTotalHoursBoundary(String candidate){
		int hours = Time.getTotalHours(candidate);
		assertTrue("hours were not calculated properly", hours ==5);
	}
	/* same as above
	@Test
	voidTestGetTotalHours2(){
		int hours = Time.getTotalHours("05:05:05");
		assertTrue("hours were not calculated properly", hours ==5);
	} */
	
	/**********************************************/
	//ICE-2 milliseconds
	
	//This was my failing test 
	//I added code to make this work in Time.java. Need to refactor code to accomodate milliseconds
	@Test
	void testGetMilliseconds() {
		int milliseconds = Time.getMilliseconds("10:59:59:005");
		assertTrue("The Milliseconds were not calculated properly", milliseconds == 5);
	}
	
	@Test
	void testGetMillisecondsGood() {
		int milliseconds = Time.getMilliseconds("05:05:30:555");
		assertTrue("milliseconds were not calculated properly", milliseconds == 555);
	}
	
	@Test
	void testGetMilliSecondsBad2() {
		assertThrows (StringIndexOutOfBoundsException.class, () -> {
			Time.getMilliseconds("8888"); } );
		}
	
	@ParameterizedTest
	@ValueSource(strings = {"05:00:00:000", "05:59:59:999"}) //testing lower and upper end of milliseconds
	void testGetMilliSecondsBoundary(String candidate){
		int milliseconds = Time.getMilliseconds(candidate);
		assertTrue("milliseconds were not calculated properly", milliseconds == 000 || milliseconds == 999);
	}

}

//lower boundary 10:00:00
// top end 10:59:59