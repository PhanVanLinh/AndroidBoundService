package toong.vn.androidboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhanVanLinh on 19/10/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class BoundService extends Service {
    public static String TAG = "BoundService";
    private final IBinder binder = new LocalBinder();
    List<BoundServiceClient> mBoundServiceClients = new ArrayList<>();

    public class LocalBinder extends Binder {

        public BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return this.binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    public void addBoundServiceClient(BoundServiceClient client) {
        mBoundServiceClients.add(client);
    }

    public void removeBoundServiceClient(BoundServiceClient client) {
        mBoundServiceClients.remove(client);
    }

    public void notifyClient(int value) {
        for (BoundServiceClient serviceClient : mBoundServiceClients) {
            serviceClient.doSomething(value);
        }
    }
}
