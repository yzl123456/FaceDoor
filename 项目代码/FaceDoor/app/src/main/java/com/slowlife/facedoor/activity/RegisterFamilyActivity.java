package com.slowlife.facedoor.activity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.slowlife.facedoor.R;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

public class RegisterFamilyActivity extends AppCompatActivity {
    private ImageButton ibAddFace;
    private RelativeLayout relativeLayout1, relativeLayout2;
    private String picturePath;
    private File file;
    private static AlertDialog dialog;
    private LayoutInflater layoutInflater;
    private LinearLayout pictureLayout;
    private List<File> files;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_family);
        layoutInflater = (LayoutInflater) getSystemService(Service.LAYOUT_INFLATER_SERVICE);
//        pictureLayout = (LinearLayout) findViewById(R.id.ll_pictures_repair_submit);
        ibAddFace= (ImageButton) findViewById(R.id.ib_add_face);
        ibAddFace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog();
            }
        });


    }


    public void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(RegisterFamilyActivity.this);
        View view = inflater.inflate(R.layout.dialog_upload_picture, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(
                RegisterFamilyActivity.this);
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
                    Toast.makeText(RegisterFamilyActivity.this, "内存卡不存在",
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
                Toast.makeText(RegisterFamilyActivity.this, "选择图片失败,请重新选择",
                        Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(RegisterFamilyActivity.this, "选择图片失败,请重新选择",
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

    /**
     * ɾ��ͼƬ
     */

    private void deletePic() {

        for (File f : files)
            f.delete();
    }

//    private String RepairPost(MyComplain repair) {
//        String result = "";
//        HttpClient client = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(Host.addRepair);
//        httpPost.setHeader("cookie", Session.getSeesion());
//
//        try {
//
//            MultipartEntity entity = new MultipartEntity();
//
//            for (int i = 0; i < files.size(); i++) {
//                FileBody fileBody2 = new FileBody(files.get(i));
//                entity.addPart("File"+i, fileBody2);
//            }
//
//            StringBody times = new StringBody(repair.getTimes(), Charset.forName("UTF-8"));
//            entity.addPart("times", times);
//            StringBody what = new StringBody(repair.getTitle(),Charset.forName("UTF-8"));
//            entity.addPart("title", what);
//            StringBody content = new StringBody(repair.getContent(),Charset.forName("UTF-8"));
//            entity.addPart("content", content);
//            httpPost.setEntity(entity);
//            HttpResponse response = client.execute(httpPost);
//            result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        return result;
//
//    }
}
