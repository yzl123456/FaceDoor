package com.slowlife.facedoor.activity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.slowlife.facedoor.R;

import java.io.File;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegisterFamily;
    private Button btnRegisterGuest;

    private LinearLayout familyRegisterLayout;
    private LinearLayout guestRegisterLayout;

    private ImageView ibAddFace;
    private RelativeLayout relativeLayout1, relativeLayout2;
    private String picturePath;
    private File file;
    private static AlertDialog dialog;
    private LayoutInflater layoutInflater;
    private LinearLayout pictureLayout;
    private List<File> files;
    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnBack= (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.tab_family_register, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab_guest_register, tabHost.getTabContentView());
        TabHost.TabSpec tab = tabHost.newTabSpec("TAB").setIndicator("家人");
        familyRegisterLayout= (LinearLayout) findViewById(R.id.tb_family_register);
        tab.setContent(R.id.tb_family_register);
        tabHost.addTab(tab);

        TabHost.TabSpec  web = tabHost.newTabSpec("TAB").setIndicator("访客");
        guestRegisterLayout= (LinearLayout) findViewById(R.id.tb_guest_register);
        web.setContent(R.id.tb_guest_register);
        tabHost.addTab(web);

        layoutInflater = (LayoutInflater) getSystemService(Service.LAYOUT_INFLATER_SERVICE);

//        pictureLayout = (LinearLayout) findViewById(R.id.ll_pictures_repair_submit);
        familyRegisterLayout.findViewById(R.id.ib_add_face).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibAddFace= (ImageView) familyRegisterLayout.findViewById(R.id.ib_add_face);
                showDialog();
            }
        });
        familyRegisterLayout.findViewById(R.id.send_mess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etPhone= (EditText) familyRegisterLayout.findViewById(R.id.phone);
                if(etPhone.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "请输入电话号码！", Toast.LENGTH_SHORT).show();
                }
                else if(etPhone.getText().toString().length()!=11){
                    Toast.makeText(RegisterActivity.this, "电话号码输入错误", Toast.LENGTH_SHORT).show();
                }
                else{
                    String phoneNumber=etPhone.getText().toString();
                    sendCode("86",phoneNumber);
                    Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                }

            }
        });

        guestRegisterLayout.findViewById(R.id.ib_add_face).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibAddFace= (ImageView) guestRegisterLayout.findViewById(R.id.ib_add_face);
                showDialog();
            }
        });
        guestRegisterLayout.findViewById(R.id.send_mess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etPhone= (EditText) familyRegisterLayout.findViewById(R.id.phone);
                if(etPhone.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "请输入电话号码！", Toast.LENGTH_SHORT).show();
                }
                else if(etPhone.getText().toString().length()!=11){
                    Toast.makeText(RegisterActivity.this, "电话号码输入错误", Toast.LENGTH_SHORT).show();
                }
                else{
                    String phoneNumber=etPhone.getText().toString();
                    sendCode("86",phoneNumber);
                    Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(RegisterActivity.this);
        View view = inflater.inflate(R.layout.dialog_upload_picture, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(
                RegisterActivity.this);
        relativeLayout1 = (RelativeLayout) view
                .findViewById(R.id.rl_shoot_upload_picture);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String SDState = Environment.getExternalStorageState();
                if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    picturePath = getTempPicPath();
                    file = new File(picturePath);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, 1);
                    dialog.cancel();
                } else {
                    dialog.cancel();
                    Toast.makeText(RegisterActivity.this, "内存卡不存在",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        relativeLayout2 = (RelativeLayout) view
                .findViewById(R.id.ll_album_upload_picture);
        relativeLayout2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                picturePath = getTempPicPath();
                file = new File(picturePath);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                Log.e("TAG","picName:"+picturePath);
                Log.e("TAG","pic:"+Uri.fromFile(file));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(Intent.createChooser(intent, "选择图片"), 2);
                dialog.cancel();
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }
    private String getTempPicPath() {
        String picPath = System.currentTimeMillis() + ".jpg";
        String filepathString = Environment.getExternalStorageDirectory()
                .getPath() + File.separator + picPath;
        return filepathString;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                startPhotoZoom(Uri.fromFile(file));
            } else {
                Toast.makeText(getApplicationContext(), "未找到存储卡，无法存储照片！",
                        Toast.LENGTH_SHORT).show();
            }

        } else if (resultCode == RESULT_OK && requestCode == 2) {
            Log.e("TAG","RESULT:"+data.getData());
            startPhotoZoom(data.getData());

        } else if (resultCode == RESULT_OK && requestCode == 3) {
            if (data != null) {
                getImageToView(data);
            } else {
                Toast.makeText(RegisterActivity.this, "选择图片失败,请重新选择",
                        Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(RegisterActivity.this, "选择图片失败,请重新选择",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // ���òü�
        intent.putExtra("crop", "true");
        // aspectX aspectY �ǿ�ߵı���
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY �ǲü�ͼƬ���
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);
        //intent.putExtra("return-times", true);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        Log.e("TAG","caijian-----"+intent.getExtras());
        Log.e("TAG","caijian-----"+intent.getExtras().getParcelable("data"));
        Log.e("TAG","caijian-----"+intent.getExtras().get("data"));
//        Uri cropUri = Uri.fromFile(new File(
//                Environment.getExternalStorageDirectory().getPath() + "/yzl/image/crop.png"));
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,cropUri);
        startActivityForResult(intent, 3);
    }

    /**
     * ����ü�֮���ͼƬ����
     *
     * @param data
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        Log.e("TAG","URI---"+extras);
        if (extras != null) {
            Log.e("TAG","THIS IS GET IMAGE TO VIEW");
            Bitmap photo = extras.getParcelable("data");
            Log.e("TAG",photo.getByteCount()+"");
//            Drawable drawable = new BitmapDrawable(null, photo);

//            Bitmap photo = extras.getParcelable("data");
//            Bitmap testP = (Bitmap) extras.get("data");
//            Log.e("TAG","PHOTO:"+photo);
//            Log.e("TAG","test:"+testP);
//            Drawable drawable = new BitmapDrawable(this.getResources(), photo);
////            View rootView = layoutInflater.inflate(R.layout.face_background, null);
//            Button imageView = (Button) rootView.findViewById(R.id.btn_uestc);
            //imageView.setBackgroundDrawable(drawable);
//            ibAddFace.setBackground(drawable);
            ibAddFace.setImageBitmap(photo);
//            pictureLayout.addView(rootView);
            // pictureLayout.addView(iv,param);
//            File mFile = new File(picturePath);
//            files.add(mFile);
        }
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
