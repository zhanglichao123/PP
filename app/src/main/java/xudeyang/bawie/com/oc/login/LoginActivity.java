package xudeyang.bawie.com.oc.login;

import android.Manifest;
import android.support.v4.app.ActivityCompat;

import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.view.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    public int  initView() {
        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        return R.layout.activity_login;
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {

    }
}
