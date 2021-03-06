package therunningapp;

import android.app.Activity;
import android.provider.BaseColumns;

public class TrappContract extends Activity {

	public TrappContract(){
		
	}
	// String for database table and columns
	public static abstract class TrappEntry implements BaseColumns {
		
		public static final String TABLE_NAME = "entry";
		public static final String COLUMN_NAME_DATE = "date";
		public static final String COLUMN_NAME_DISTANCE = "distance";
		public static final String COLUMN_NAME_TIME = "time";
		public static final String COLUMN_NAME_CALORIES = "calories";
		public static final String COLUMN_NAME_WORKOUTTYPE = "workouttype";
		public static final String COLUMN_NAME_LOCATIONS = "locations";
		
		public static final String TABLE_NAMEPREF = "pref";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_HEIGHT = "height";
		public static final String COLUMN_NAME_WEIGHT = "weight";
		public static final String COLUMN_NAME_AGE = "age";
		public static final String COLUMN_NAME_GENDER = "gender";
		
		public static final String TABLE_TESTS = "test";
		public static final String COLUMN_NAME_TESTNAME = "name";
		public static final String COLUMN_NAME_TEST_DISTANCE = "distance";
		public static final String COLUMN_NAME_MIN = "min";
		public static final String COLUMN_NAME_SEC = "sec";
		public static final String COLUMN_NAME_TEST_TYPE = "test_type";
		public static final String COLUMN_NAME_CHOSE = "chose";

		public static final String TABLE_NAME_INTERVAL = "interval";
		//public static final String COLUMN_NAME_NAME = "name";		//Declared above
		public static final String COLUMN_NAME_RUN_TIME = "run";
		public static final String COLUMN_NAME_PAUSE_TIME = "pause";
		public static final String COLUMN_NAME_REPETITION = "repetition";
		public static final String COLUMN_NAME_INTERVALTYPE = "intervaltype";
		
		public static final String TABLE_NAME_LOCATIONS = "locations";
		public static final String COLUMN_NAME_WORKOUT = "workoutID";
		public static final String COLUMN_NAME_LATITUDE = "latitude";
		public static final String COLUMN_NAME_LONGITUDE = "longitude";
	}

}