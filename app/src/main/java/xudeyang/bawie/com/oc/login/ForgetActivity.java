package xudeyang.bawie.com.oc.login;

import android.content.Intent;
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
import xudeyang.bawie.com.oc.login.bean.RegisterBean;
import xudeyang.bawie.com.oc.utils.RetrofitUtil;
import xudeyang.bawie.com.oc.utils.TitleBar;
import xudeyang.bawie.com.oc.view.activity.MainActivity;
import xudeyang.bawie.com.oc.view.base.BaseActivity;

public class ForgetActivity extends BaseActivity {

    @BindView(R.id.forget_title)
    TitleBar forgetTitle;
    @BindView(R.id.pwd_forget)
    EditText pwdForget;
    @BindView(R.id.confirm_pwd_forget)
    EditText confirmPwdForget;
    @BindView(R.id.login_forget)
    Button loginForget;
    @BindView(R.id.visitors_login)
    TextView visitorsLogin;
    private TextView text;
    private String phone;
    private Flowable<RegisterBean> pass;


    @Override
    public void initView() {
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

    }

    @Override
    public void initHttp() {
        //返回
        forgetTitle.setBack(this);
        text = forgetTitle.getText();
        text.setText("已有账号");
        //调到登录页面
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetActivity.this, ElseLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.init_fo_return, R.anim.out_to_return);
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.login_forget, R.id.visitors_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forget://修改密码
                String pwd = pwdForget.getText().toString();
                String confirmPwd = confirmPwdForget.getText().toString();
                boolean verification = verification(phone, pwd, confirmPwd);
                if (verification) {
                    initNetHttp(phone, pwd);
                }
                break;
            case R.id.visitors_login://
                Intent intent = new Intent(ForgetActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    /**
     * 请求网络
     *
     * @param phone
     * @param pwd
     */
    private void initNetHttp(String phone, String pwd) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "0");
        map.put("mobile", phone);
        map.put("newPass", pwd);
        pass = RetrofitUtil.getInstance().getPass(map);
        pass.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<RegisterBean>() {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        String code = registerBean.getCode();
                        if (code.equals("0")) {
                            Toast.makeText(ForgetActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ForgetActivity.this, ElseLoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.out_to, R.anim.out_to_forget);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(ForgetActivity.this, "设置失败", Toast.LENGTH_LONG).show();
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
    private boolean verification(String mobile, String pwd, String confirmPwd) {
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
        if (!(pwd.equals(confirmPwd))) {
            Toast.makeText(this, "两次密码不一样", Toast.LENGTH_SHORT).show();
            return false;
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
