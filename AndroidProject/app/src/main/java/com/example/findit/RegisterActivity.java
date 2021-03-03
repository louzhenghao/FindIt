package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findit.entity.User;
import com.example.findit.util.CodeTimeUtil;
import com.google.gson.Gson;
import com.mob.MobSDK;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private ImageView ivEye;
    private Button btnRegister;
    private Button btnCode;
    private EditText etRegisterPhone;
    private EditText etRegisterCode;
    private EditText etRegisterPwd;
    private int eye = 0;
    private int num = 0;
    private int tx = 0;
    private String phone;
    private String pwd;
    private OkHttpClient okHttpClient;
    private CodeTimeUtil codeTimeUtil;
    private OnSendMessageHandler onSendMessageHandler;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.nonetitle);
        setContentView(R.layout.activity_register);

        findView();

        setListeners();



        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==0){
                    Intent intent=new Intent(RegisterActivity.this,LoginInActivity.class);
                    startActivity(intent);
                    RegisterActivity.this.finish();
                }
            }
        };

        onSendMessageHandler = new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
                return false;
            }
        };

    }


    private void findView(){
        okHttpClient = new OkHttpClient();
        ivEye = findViewById(R.id.iv_register_eye);
        btnRegister = findViewById(R.id.btn_register_register);
        btnCode = findViewById(R.id.btn_register_code);
        etRegisterPhone = findViewById(R.id.et_register_phone);
        etRegisterCode = findViewById(R.id.et_register_code);
        etRegisterPwd = findViewById(R.id.et_register_pwd);
    }


    private void setListeners(){
        RegisteredLiseners registeredLiseners = new RegisteredLiseners();
        ivEye.setOnClickListener(registeredLiseners);
        btnRegister.setOnClickListener(registeredLiseners);
        btnCode.setOnClickListener(registeredLiseners);

    }


//    @Override
//    public boolean handleMessage(Message msg) {
//        return false;
//    }

    public class RegisteredLiseners implements View.OnClickListener{

        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_register_code:
                    tx=0;
                    registcode();//调用注册短信发送的回调接口
                    //判断是否为null或“”
                    if (TextUtils.isEmpty(etRegisterPhone.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入合法的手机号", Toast.LENGTH_SHORT).show();
                    } else {
                        if (onSendMessageHandler.onSendMessage("86",etRegisterPhone.getText().toString()))
                            Toast.makeText(RegisterActivity.this, "请您输入合法的手机号", Toast.LENGTH_SHORT).show();
                        else
                            SMSSDK.getVerificationCode("86", etRegisterPhone.getText().toString());
                    }
                    break;
                case R.id.btn_register_register:
                    if (TextUtils.isEmpty(etRegisterPhone.getText().toString()) || etRegisterPhone.getText().toString().length()!=11){
                        Toast.makeText(RegisterActivity.this, "请检验您输入的手机号", Toast.LENGTH_SHORT).show();
                    } else {
                        if (TextUtils.isEmpty(etRegisterCode.getText().toString())) {
                            Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            if (TextUtils.isEmpty(etRegisterPwd.getText().toString())) {
                                Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                            } else {
                                num = 1;
                                registcode();
                                SMSSDK.submitVerificationCode("86", etRegisterPhone.getText().toString(), etRegisterCode.getText().toString());
                                //register(num);
                            }
                        }
                    }

                    break;
                case R.id.iv_register_eye:
                    if(eye==0){
                        eye=1;
                        ivEye.setImageResource(R.drawable.eye);
                        etRegisterPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else{
                        eye=0;
                        ivEye.setImageResource(R.drawable.noteye);
                        etRegisterPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    break;
            }
        }
    }



    public void registcode(){
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                register(num);
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "已发送验证码，请查收", Toast.LENGTH_SHORT).show();
                                codeTimeUtil = new CodeTimeUtil(btnCode,30000,1000);
                                codeTimeUtil.start();
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (tx==0){
                                Toast.makeText(RegisterActivity.this, "操作失败，请重新获取验证码", Toast.LENGTH_SHORT).show();
                                tx=1;
                            }
                        }
                    });
                    ((Throwable) data).printStackTrace();
                }

            }
        };
        SMSSDK.registerEventHandler(eh);
    }


    public void register(int x){
        if (x==1){
            phone = etRegisterPhone.getText().toString();
            pwd = etRegisterPwd.getText().toString();

            //手机号密码规则
            if (phone.length() == 11 && pwd.length() != 0) {
                User user = new User(null,null,pwd,null,null,phone,null,null,null);
                String info = new Gson().toJson(user);
                final Request request = new Request.Builder().url(com.example.findit.Info.BASE_URL + "user/register?info=" + info).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(), "服务器出错了，请稍后重试", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Looper.prepare();
                        String x = response.body().string();
                        Log.e("注册",x);
                        if (x.equals("true")){
                            Toast.makeText(RegisterActivity.this, "您已注册成功，即将返回登录界面", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(RegisterActivity.this, "注册失败，您已经注册，请直接登录", Toast.LENGTH_SHORT).show();
                        }
                        new Thread(){
                            @Override
                            public void run() {
                                try{
                                    Thread.sleep(2000);
                                    Message message=new Message();
                                    message.what=0;
                                    handler.sendMessage(message);
                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                        Looper.loop();

                    }
                });
            }
            num=0;
        }

    }
}
