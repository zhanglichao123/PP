package xudeyang.bawie.com.oc.view.activity;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.view.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {

    }
}
