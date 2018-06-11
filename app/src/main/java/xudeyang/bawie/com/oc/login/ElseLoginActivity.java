package xudeyang.bawie.com.oc.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.login.bean.LoginBean;
import xudeyang.bawie.com.oc.utils.RetrofitUtil;
import xudeyang.bawie.com.oc.utils.TitleBar;
import xudeyang.bawie.com.oc.view.activity.MainActivity;
import xudeyang.bawie.com.oc.view.base.BaseActivity;

public class ElseLoginActivity extends BaseActivity {

    @BindView(R.id.else_login_title)
    TitleBar elseLoginTitle;
    @BindView(R.id.mobile_login)
    EditText mobileLogin;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_login)
    Button loginLogin;
    @BindView(R.id.visitors_login)
    TextView visitorsLogin;
    private TextView text;
    private SharedPreferences logins;

    @Override
    public void initView() {
        setContentView(R.layout.activity_else_login);
        ButterKnife.bind(this);
    }

    @Override
    public void initHttp() {
        logins = getSharedPreferences("login", MODE_PRIVATE);
    }

    @Override
    public void initData() {
        //返回
        elseLoginTitle.setBack(this);
        text = elseLoginTitle.getText();
        text.setText("注册账号");
        //跳转到注册页面
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElseLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.init_fo_register, R.anim.out_to);
            }
        });
    }


    @OnClick({R.id.login_login, R.id.visitors_login, R.id.forget_pwd_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_login://登录
                String login = mobileLogin.getText().toString();
                String pwd = loginPwd.getText().toString();
                boolean verification = verification(login, pwd);
                if (verification) {
                    initNetHttp(login, pwd);
                }
                break;
            case R.id.visitors_login://注册
                Intent intent = new Intent(ElseLoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.forget_pwd_login://忘记密码
                Intent intent1 = new Intent(this, PassActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.init_fo_pass, R.anim.out_to);
                break;
        }
    }

    /**
     * 请求网络
     *
     * @param login
     * @param pwd
     */
    private void initNetHttp(final String login, String pwd) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", login);
        map.put("password", pwd);
        Flowable<LoginBean> login1 = RetrofitUtil.getInstance().getLogin(map);
        login1.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        Toast.makeText(ElseLoginActivity.this, loginBean.getCode(), Toast.LENGTH_SHORT).show();
                        //跳转主页面
                        if (loginBean.getCode().equals("0")) {
                            int uid = loginBean.getData().getUid();
                            String token = loginBean.getData().getToken();
                            logins.edit().putInt("loginUid", uid).putString("loginToken", token).commit();
                            Intent intent = new Intent(ElseLoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(ElseLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /***
     *
     * 正则验证密码账号
     * @param mobile
     * @param pwd
     * @return
     */
    private boolean verification(String mobile, String pwd) {
        //验证手机号
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        boolean matches = m.matches();
        if (!matches) {
            Toast.makeText(this, "请输入正确的手机号!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        //验证密码
        String pwdx = "^[a-zA-Z0-9]+$";
        Pattern compile = Pattern.compile(pwdx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher(pwd);
        boolean matches1 = matcher.matches();
        boolean ff = pwd.length() <= 8 ? true : false;
        if (!ff) {
            Toast.makeText(this, "密码不可超过八位", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!matches1) {
            Toast.makeText(this, "大小写字母和数字!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ff && matches && matches1) {
            return true;
        }
        return false;
    }
}
