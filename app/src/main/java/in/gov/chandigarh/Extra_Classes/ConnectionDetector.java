package in.gov.chandigarh.Extra_Classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by Microsoft on 10/25/2017.
 */

public class ConnectionDetector {

    /*** Function to check either mobile or wifi network is available or not. ***/

    public static boolean networkStatus(Context context) {

        return (ConnectionDetector.isWifiAvailable(context) || ConnectionDetector.isMobileNetworkAvailable(context));

    }

    public static boolean isMobileNetworkAvailable(Context ctx) {

        ConnectivityManager connecManager = (ConnectivityManager) ctx

                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo myNetworkInfo = connecManager

                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (myNetworkInfo.isConnected()) {

            return true;

        } else {

            return false;

        }

    }

    public static boolean isWifiAvailable(Context ctx) {

        ConnectivityManager myConnManager = (ConnectivityManager) ctx

                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo myNetworkInfo = myConnManager

                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (myNetworkInfo.isConnected())

            return true;

        else

            return false;

    }

    // check gps status
    public static boolean isGpsEnabled(Context ctx) {

        LocationManager locationManager = (LocationManager) ctx

                .getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))

            return true;

        else

            return false;

    }

    public static void displayNoNetworkDialog(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Internet connection is required to access the application. Please enable mobile network or Wi-Fi in Settings.").setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(
                                        Settings.ACTION_SETTINGS);
                                activity.startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        activity.finish();
                    }
                });

        builder.show();

    }
}