package xudeyang.bawie.com.oc.view.activity;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.utils.TitleBar;
import xudeyang.bawie.com.oc.view.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener{


    TitleBar titlebarLoginlogin;
    private TextView text;
    private TextView qq;
    private TextView weixin;
    private TextView elsel;

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

         titlebarLoginlogin = findViewById(R.id.titlebar_loginlogin);
         qq = findViewById(R.id.qq_login);
         weixin = findViewById(R.id.weixin_long);
         elsel = findViewById(R.id.else_login);
         qq.setOnClickListener( this);
         titlebarLoginlogin = findViewById(R.id.titlebar_loginlogin);
         //返回
         titlebarLoginlogin.setBack(this);
         //设置右边的字
         text = titlebarLoginlogin.getText();
         text.setVisibility(View.GONE);

    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
                case R.id.qq_login:
                break;
                case R.id.weixin_long:
                break;
                case R.id.else_login:

                break;
        }
    }
}
