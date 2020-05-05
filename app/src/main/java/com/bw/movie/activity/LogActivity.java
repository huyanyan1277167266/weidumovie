package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LogBean;
import com.bw.movie.bean.WXCodeBean;
import com.bw.movie.contract.LogContract;
import com.bw.movie.presenter.LogIPresenter;
import com.bw.movie.utiles.EncryptUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class LogActivity extends BaseActivity implements LogContract.LogView {
@BindView(R.id.login_email)
EditText loginemail;
@BindView(R.id.login_pwd)
    EditText loginpwd;
@BindView(R.id.login_forget)
    Button loginforget;
@BindView(R.id.login_login)
    TextView loginlogin;
@BindView(R.id.login_dl)
Button logindl;
@BindView(R.id.login_wechat)
ImageButton loginwechat;
    private SharedPreferences name;
    //邮箱正则表达式
    public static  String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private String email;
    private String pwd;
    private String encrypt;

    @Override
    protected BasePresenter initPresenter() {
        return new LogIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_log;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
    //接收微信传过来的方法
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveWXCode(WXCodeBean wxCodeBean) {
        ;
        Log.i("xxx", "receiveWXCode: "+wxCodeBean.getCode());
        EventBus.getDefault().removeStickyEvent(wxCodeBean);



    }
    @Override
    public void onLogSuccess(LogBean logBean) {
        Log.i("xxx",logBean.getMessage());
        if (logBean.getMessage().equals("登陆成功")){
            int userId = logBean.getResult().getUserId();
            String sessionId = logBean.getResult().getSessionId();
            SharedPreferences sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("userId",userId);
            edit.putString("sessionId",sessionId);
            edit.commit();

            Intent intent = new Intent(LogActivity.this, MoviewActivity.class);
            startActivity(intent);
            finish();

        }

    }

    @Override
    public void onLogError(String str) {

    }



    @OnClick({R.id.login_login,R.id.login_dl,R.id.login_wechat})
    public void onViewClick(View v){
        switch (v.getId()){
            //去注册
            case R.id.login_login:
                Intent intent = new Intent(LogActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
                //登录
            case R.id.login_dl:

                email = loginemail.getText().toString().trim();
                pwd = loginpwd.getText().toString().trim();
                encrypt = EncryptUtil.encrypt(pwd);

                    if (!TextUtils.isEmpty(encrypt)) {
                        BasePresenter basePresenter = getmPresenter();
                        if (basePresenter!=null&&basePresenter instanceof LogIPresenter){
                            ((LogIPresenter) basePresenter).getLog(email, encrypt);
                        }
                    }else{
                        Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
                //微信登录
            case R.id.login_wechat:
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                App.getWxApi().sendReq(req);
                break;
                default:
        }
    }
    //邮箱正则
    private boolean isEmail(String str){
        if (TextUtils.isEmpty(str)){
            return false;
        }
        Pattern compile = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }

}
