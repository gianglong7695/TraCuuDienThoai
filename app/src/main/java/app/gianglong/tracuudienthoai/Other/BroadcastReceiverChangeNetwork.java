package app.gianglong.tracuudienthoai.Other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;

import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.R;

/**
 * Created by Giang Long on 8/4/2016.
 */

public class BroadcastReceiverChangeNetwork extends BroadcastReceiver {
    public static boolean isCheckNetwork = false;
    AlertDialog dialog ;
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connec = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            isCheckNetwork = true;

        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            MainActivity.mActivity.setContentView(R.layout.screen_network_disconect);
            isCheckNetwork = false;
            MainActivity.dialogNetwork.show();

        }




    }
}
