package test.kmj.com.bluetoothspp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

public class MainActivity extends AppCompatActivity {
    BluetoothSPP bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = new BluetoothSPP(this);
        if (!bt.isBluetoothEnabled()) {
            bt.enable();
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            }
        }
        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener()

        {
            public void onDeviceConnected(String name, String address) {

                //연결됐을때
                Log.e("test", name+address);
                Log.e("test", "connect");

            }

            public void onDeviceDisconnected() {    //연결끊김
                Log.e("test", "consad");

            }

            public void onDeviceConnectionFailed() {
                //연결 실패했을때
                Log.e("test", "sad");
            }
        });
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                //ㄷㅔ이터 받아질때 매번 실행
                Log.e("test", "1");

            }
        });


    }

    public void setup() {
        bt.autoConnect("HC-06");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bt.stopService();
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) {
            bt.enable();
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            }
        }
    }

}
