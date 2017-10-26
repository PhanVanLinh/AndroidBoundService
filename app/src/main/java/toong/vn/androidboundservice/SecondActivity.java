package toong.vn.androidboundservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity implements BoundServiceClient {
    private String TAG = getClass().getSimpleName();
    private ServiceHandler serviceHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    @Override
    protected void onStart() {
        super.onStart();
        serviceHandler = new ServiceHandler(this);
        serviceHandler.bindService(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        serviceHandler.unbind();
    }

    @Override
    public void doSomething(int value) {
        Log.i(TAG, "value = " + value);
    }
}
