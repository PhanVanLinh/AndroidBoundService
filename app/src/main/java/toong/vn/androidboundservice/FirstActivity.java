package toong.vn.androidboundservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class FirstActivity extends AppCompatActivity implements BoundServiceClient {
    private String TAG = getClass().getSimpleName();
    private ServiceHandler serviceHandler;
    private boolean test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        findViewById(R.id.start_second_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
                test = true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!test) {
            serviceHandler = new ServiceHandler(this);
            serviceHandler.bindService(this);
        }
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
