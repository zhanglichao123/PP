package xudeyang.bawie.com.oc.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.utils.TitleBar;
import xudeyang.bawie.com.oc.view.activity.MainActivity;
import xudeyang.bawie.com.oc.view.base.BaseActivity;

public class PassActivity extends BaseActivity {

    @BindView(R.id.pass_title)
    TitleBar passTitle;
    @BindView(R.id.mobile_pass)
    EditText mobilePass;
    @BindView(R.id.pwd_pass)
    EditText pwdPass;
    @BindView(R.id.login_pass)
    Button loginPass;
    @BindView(R.id.visitors_pass)
    TextView visitorsPass;
    @BindView(R.id.verificatio_pass)
    TextView verificatioPass;
    private TextView text;
    private boolean verification;
    private String mobile;
    boolean gg = true;
    Handler hh = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
          int i = msg.what;
          if (i==1){
              Toast.makeText(PassActivity.this, "对lllll对对", Toast.LENGTH_SHORT).show();
              gg = true;
              verificatioPass.setTextColor(Color.BLUE);
          }

        }
    };
    private Thread thres;

    @Override
    public void initView() {
        setContentView(R.layout.activity_pass);
        ButterKnife.bind(this);

    }

    @Override
    public void initHttp() {
        //返回
        passTitle.setBack(this);
        text = passTitle.getText();
        text.setText("已有账号");
        //调到登录页面
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassActivity.this, ElseLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.init_fo_return, R.anim.out_to_return);
            }
        });
    }

    //
    @Override
    public void initData() {


    }
//13269671778

    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    public void sendCode(final String country, final String phone) {
        Toast.makeText(PassActivity.this, "对对对", Toast.LENGTH_SHORT).show();
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, final int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    //下一步
                    loginPass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(PassActivity.this, "对4444444对对", Toast.LENGTH_SHORT).show();
                            submitCode("86", phone, pwdPass.getText().toString());
                        }
                    });

                } else {
                    // TODO 处理错误的结果
                    Log.d("验证", "失败");
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    /**
     * 防止验证频繁点击
     */
    public void textThread() {
        thres = new Thread(new Runnable() {
            @Override
            public void run() {
                Message dd=new Message();
                dd.what=1;
                dd.obj="222";
                hh.sendMessageDelayed(dd,50000);
            }
        });
        thres.start();

    }

    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, final String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    Intent intent = new Intent(PassActivity.this, ForgetActivity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    if (hh!=null){
                        hh=null;
                        hh.removeCallbacks(thres);
                    }
                    overridePendingTransition(R.anim.init_fo_register, R.anim.out_to);
                } else {
                    // TODO 处理错误的结果
                }
            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
        if (hh!=null){
            hh=null;
        }
    }

    ;

    /**
     * 单击事件
     *
     * @param view
     */
    @OnClick({R.id.login_pass, R.id.visitors_pass, R.id.verificatio_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_pass://下一步

                break;
            case R.id.visitors_pass://游客登入
                Intent intent = new Intent(PassActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.verificatio_pass://获取验证码
                if (gg) {
                    gg = false;
                    verificatioPass.setTextColor(Color.RED);
                    textThread();
                //获得手机号
                mobile = mobilePass.getText().toString();
                //验证手机号
                verification = verification(mobile);
                if (verification) {
                    //获得短信验证
                    sendCode("86", mobile);
                }
                }
                break;
        }
    }

    /***
     *
     * 正则验证密码账号
     * @param mobile
     *
     * @return
     */
    private boolean verification(String mobile) {
        //验证手机号
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        boolean matches = m.matches();
        if (!matches) {
            Toast.makeText(this, "请输入正确的手机号!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return matches;
    }



}
