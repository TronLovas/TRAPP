package therunningapp;

import java.util.Locale;

import project.therunningapp.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity extends FragmentActivity {
	public static String soundpackage = "en";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void settings(View view) { // Onclick function for the settings
										// button
		Intent intent = new Intent(this, Settings.class);
		startActivity(intent); // Start Settings activity

	}

	public void history(View view) { // Onclick function for the history button
		Intent intent = new Intent(this, History.class);
		startActivity(intent); // Start History activity

	}

	// Onclick function for the start workout button
	public void StartSetupWorkout(View view) {
		if (servicesConnected() && haveNetworkConnection()) { // If google play
																// services are
																// available,
			Intent intent = new Intent(this, SetupWorkout.class); // and device
																	// is
																	// connected
																	// to the
																	// internet.
			startActivity(intent); // -> Start WorkoutStart activity
		} else { // If device not connected to the internet -> short message to
					// the user.
			Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT)
					.show();
		}

	}

	// The following code is retrieved from developer.android.com,
	// to check if google play services are available.
	// Global constants
	/*
	 * Define a request code to send to Google Play services This code is
	 * returned in Activity.onActivityResult
	 */
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

	// Define a DialogFragment that displays the error dialog
	public static class ErrorDialogFragment extends DialogFragment {
		// Global field to contain the error dialog
		private Dialog mDialog;

		// Default constructor. Sets the dialog field to null
		public ErrorDialogFragment() {
			super();
			mDialog = null;
		}

		// Set the dialog to display
		public void setDialog(Dialog dialog) {
			mDialog = dialog;
		}

		// Return a Dialog to the DialogFragment.
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}

	/*
	 * Handle results returned to the FragmentActivity by Google Play services
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Decide what to do based on the original request code
		switch (requestCode) {

		case CONNECTION_FAILURE_RESOLUTION_REQUEST:
			/*
			 * If the result code is Activity.RESULT_OK, try to connect again
			 */
			switch (resultCode) {
			case Activity.RESULT_OK:
				/*
				 * Try the request again
				 */

				break;
			}
		}
	}

	private boolean servicesConnected() {
		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			// In debug mode, log the status
			Log.d("Location Updates", "Google Play services is available.");
			// Continue
			return true;
			// Google Play services was not available for some reason
		} else {
			// Get the error dialog from Google Play services
			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
					resultCode, this, CONNECTION_FAILURE_RESOLUTION_REQUEST);

			// If Google Play services can provide an error dialog
			if (errorDialog != null) {
				// Create a new DialogFragment for the error dialog
				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
				// Set the dialog in the DialogFragment
				errorFragment.setDialog(errorDialog);
				// Show the error dialog in the DialogFragment
				errorFragment.show(getSupportFragmentManager(),
						"Location Updates");
			}
			return false;
		}

	}

	// Code to check if the device got internet connection active (either wifi
	// or mobile network)
	// This function is fetched from stackoverflow
	private boolean haveNetworkConnection() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	@Override
	public void onDestroy() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putInt("destroyed", 1);
		editor.commit();
		super.onDestroy();
	}

	public void norwegian(View view) {
		language("no");
	}

	public void english(View view) {
		language("en");
	}

	//function for language, changes the default language on your phone
	public void language(String ch) {
		Locale mLocale = null;

		if (ch == "en") {
			mLocale = new Locale("");
			soundpackage = "en";
		} else if (ch == "no") {
			mLocale = new Locale("no");
			soundpackage = "no";
		}

		Locale.setDefault(mLocale);
		Configuration config = getBaseContext().getResources()
				.getConfiguration();
		if (!config.locale.equals(mLocale)) {
			config.locale = mLocale;
			getBaseContext().getResources().updateConfiguration(config, null);
		}

		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
}
