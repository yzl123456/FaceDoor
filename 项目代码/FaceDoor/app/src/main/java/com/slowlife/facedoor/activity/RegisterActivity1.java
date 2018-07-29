package com.slowlife.facedoor.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;
import com.slowlife.facedoor.R;
import com.slowlife.facedoor.utils.Constants;
import com.slowlife.facedoor.utils.MyBitmapFactory;

import java.io.InputStream;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 这是输入电话号码，和发送验证码的注册界面
 */
public class RegisterActivity1 extends Activity {

	private Button ensureBtn;
	private ImageButton backBtn;
	private EditText etPhone;
	private EditText etPwd;
	private TextView sendCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_regist);
		MobSDK.init(this);


		//160307新增
		InputStream is=this.getResources().openRawResource(R.raw.logo_app);
		Bitmap yunpassBitmap= MyBitmapFactory.decodeRawBitMap(is);
		ImageView yunpassimageView=(ImageView)findViewById(R.id.zhuce_themepic);
		yunpassimageView.setImageBitmap(yunpassBitmap);

		backBtn= (ImageButton) findViewById(R.id.zuce_back);
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//发送短信验证码到手机
		etPhone = (EditText) findViewById(R.id.phone);
		etPwd= (EditText) findViewById(R.id.pwd_et);
		sendCode= (TextView) findViewById(R.id.send_mess);
		sendCode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(etPhone.getText().toString().equals("")){
					Toast.makeText(RegisterActivity1.this, "请输入电话号码！", Toast.LENGTH_SHORT).show();
				}
				else if(etPhone.getText().toString().length()!=11){
					Toast.makeText(RegisterActivity1.this, "电话号码输入错误", Toast.LENGTH_SHORT).show();
				}
				else{
					String phoneNumber=etPhone.getText().toString();
					sendCode("86",phoneNumber);
					Toast.makeText(RegisterActivity1.this, "验证码已发送", Toast.LENGTH_SHORT).show();
				}
			}
		});

		ensureBtn= (Button) findViewById(R.id.zuce_ensure);
		ensureBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Constants.userName= etPhone.getText().toString();
				Constants.pwd=etPwd.getText().toString();
				Toast.makeText(RegisterActivity1.this, "注册成功", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(RegisterActivity1.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}

	// 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
	public void sendCode(String country, String phone) {
		// 注册一个事件回调，用于处理发送验证码操作的结果
		SMSSDK.registerEventHandler(new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				if (result == SMSSDK.RESULT_COMPLETE) {
					// TODO 处理成功得到验证码的结果
					// 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
				} else{
					// TODO 处理错误的结果
				}

			}
		});
		// 触发操作
		SMSSDK.getVerificationCode(country, phone);
	}

	// 提交验证码，其中的code表示验证码，如“1357”
	public void submitCode(String country, String phone, String code) {
		// 注册一个事件回调，用于处理提交验证码操作的结果
		SMSSDK.registerEventHandler(new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				if (result == SMSSDK.RESULT_COMPLETE) {
					// TODO 处理验证成功的结果
				} else{
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
	};





}
