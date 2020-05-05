package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.RegisterContract;
import com.bw.movie.presenter.RegisterIPresenter;
import com.bw.movie.utiles.EncryptUtil;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterContract.RegisterView {
    @BindView(R.id.register_name)
    EditText registername;
    @BindView(R.id.register_email)
    EditText registeremail;
    @BindView(R.id.register_pass)
    EditText registerpass;
    @BindView(R.id.register_code)
    EditText registercode;
    @BindView(R.id.register_hq)
    Button registerhq;
    @BindView(R.id.register_login)
    TextView registerlogin;
    @BindView(R.id.register_zc)
    Button registerzc;
    @BindView(R.id.xx)
    ImageView xx;
    @BindView(R.id.register_eye)
    ImageView register_eye;
    private String name;
    private String encrypt;
    private String email;
    private String code;
    //public static String REGEX_EMAIL="^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$";

    @Override
    protected BasePresenter initPresenter() {
        return new RegisterIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {

    }

    @Override
    public void onEmailSuccess(EmailBean emailBean) {
        if (emailBean!=null){
            Toast.makeText(this, ""+emailBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEmailError(String str) {
        Log.i("xxx",str);
    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {
        if (registerBean!=null){
            Intent intent = new Intent(RegisterActivity.this, LogActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onRegisterError(String str) {
        Log.i("",str);
    }
    @OnClick({R.id.register_eye,R.id.register_hq,R.id.register_login,R.id.register_zc})
    public void onViewClicked(View v){
        switch (v.getId()){
            //隐藏密码的小眼睛
            case R.id.register_eye:
                //隐藏密码
                registerpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
          //获取验证码
           case R.id.register_hq:
              if (!TextUtils.isEmpty(registeremail.getText())){
                  BasePresenter basePresenter = getmPresenter();
                  if (basePresenter!=null&&basePresenter instanceof RegisterIPresenter){
                      ((RegisterIPresenter) basePresenter).getEmail(registeremail.getText().toString());
                  }
              }else{
                  Toast.makeText(this, "请输入正确的邮箱", Toast.LENGTH_SHORT).show();
              }
                break;
                //已有账号立即登录
            case R.id.register_login:
                Intent intent = new Intent(RegisterActivity.this, LogActivity.class);
                startActivity(intent);
                finish();
                break;
                //注册按钮
            case R.id.register_zc:
                if (TextUtils.isEmpty(registername.getText())){
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(registeremail.getText())){
                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(registerpass.getText())){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(registercode.getText())){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    name = registername.getText().toString();
                    String pass = registerpass.getText().toString();
                    encrypt = EncryptUtil.encrypt(pass);
                    email = registeremail.getText().toString();
                    code = registercode.getText().toString();
                    BasePresenter basePresenter = getmPresenter();
                    if (basePresenter!=null&&basePresenter instanceof RegisterIPresenter){
                        ((RegisterIPresenter) basePresenter).getRegister(name, encrypt, email, code);

                    }

                }
                break;
                default:
        }
    }

}
