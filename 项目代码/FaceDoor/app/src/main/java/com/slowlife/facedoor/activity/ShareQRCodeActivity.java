package com.slowlife.facedoor.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.slowlife.facedoor.R;
import com.slowlife.facedoor.utils.AndroidShare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class ShareQRCodeActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnShare;
    private ImageView QRCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_qrcode);
        btnBack= (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        QRCode= (ImageView) findViewById(R.id.qrcode);
        btnShare= (Button) findViewById(R.id.share);
        //分享访客二维码
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm =((BitmapDrawable) ((ImageView) QRCode).getBackground()).getBitmap();
                saveMyBitmap("qrcode",bm);
                Log.e("TAG","finish--save");
//                showShare();
//                Toast.makeText(ShareQRCodeActivity.this, "保存图片", Toast.LENGTH_SHORT).show();

                AndroidShare share=new AndroidShare(getApplicationContext());
                share.shareQQFriend("扫码开门","facedoor开门二维码",AndroidShare.DRAWABLE,bm);
            }
        });

    }




    public static String saveMyBitmap(String bitName,Bitmap mBitmap) {
        File f = new File("/sdcard/" + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        } catch (Exception e) {
            return "create_bitmap_error";
        }
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/sdcard/" + bitName + ".png";
    }
//    private void showShare() {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle("开门二维码");
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////        oks.setima
////        oks.setImagePath("/sdcard/qrcode.png");//确保SDcard下面存在此张图片
//        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
//        // 启动分享GUI
//        oks.show(this);
//
//
//
//    }
}
