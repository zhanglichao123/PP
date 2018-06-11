package xudeyang.bawie.com.oc.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.utils.TitleBar;
import xudeyang.bawie.com.oc.view.base.BaseActivity;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.register_title)
    TitleBar registerTitle;
    @BindView(R.id.mobile_register)
    EditText mobileRegister;
    @BindView(R.id.pwd_register)
    EditText pwdRegister;
    @BindView(R.id.login_register)
    Button loginRegister;
    @BindView(R.id.visitors_register)
    TextView visitorsRegister;
    private TextView text;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public void initHttp() {

    }

    @Override
    public void initData() {
        //返回
        registerTitle.setBack(this);
        text = registerTitle.getText();
        text.setText("已有账号");
        //跳转到登录页面
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, ElseLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.init_fo_return, R.anim.out_to_return);
            }
        });
    }


    @OnClick({R.id.login_register, R.id.visitors_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_register:
                String mobile = mobileRegister.getText().toString();
                String pwd = pwdRegister.getText().toString();
                boolean verification = verification(mobile, pwd);
                if (verification) {
                    initNetHttp(mobile, pwd);
                }
                break;
            case R.id.visitors_register:
                break;
        }
    }

    /**
     * 请求数据
     * @param mobile
     * @param pwd
     */
    private void initNetHttp(String mobile, String pwd) {

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
