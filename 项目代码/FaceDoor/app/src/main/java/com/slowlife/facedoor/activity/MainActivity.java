package com.slowlife.facedoor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mob.MobSDK;
import com.slowlife.facedoor.R;
import com.slowlife.facedoor.utils.Constants;
import com.slowlife.facedoor.utils.MyBitmapFactory;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private ImageButton registerBtn;
    private EditText etName;
    private EditText etPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobSDK.init(this);

        etName= (EditText) findViewById(R.id.et_edit_useName_login);
        etPwd= (EditText) findViewById(R.id.et_edit_password_login);
        if(Constants.userName!=null)
            etName.setText(Constants.userName);
        if(Constants.pwd!=null)
            etPwd.setText(Constants.pwd);


        //登录页logo
        InputStream is=this.getResources().openRawResource(R.raw.logo_app);
        Bitmap yunpassBitmap= MyBitmapFactory.decodeRawBitMap(is);
        ImageView yunpassimageView=(ImageView)findViewById(R.id.iv_verpass_login);
        yunpassimageView.setImageBitmap(yunpassBitmap);




        loginBtn= (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        registerBtn= (ImageButton) findViewById(R.id.ib_zuce_buttton_login);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity1.class);
                startActivity(intent);
            }
        });


    }
}
