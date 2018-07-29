package com.slowlife.facedoor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.slowlife.facedoor.R;
import com.slowlife.facedoor.utils.MyBitmapFactory;

import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by 泽林 on 2018/4/6.
 */

public class DoorFragment extends Fragment {
    private ImageView btnOpenDoor;
    private ImageView btnRegister;
    private ImageView btnGuestList;
    private ImageView btnFaceUpdate;
    private ImageView btnGuestApply;
    private ImageView btnVisitRecord;
    private ImageView btnCallSpeak;
    private ImageView inOutRecords;

    private InputStream is;
    private Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_door,null);
        btnOpenDoor= (ImageView) root.findViewById(R.id.btn_open_door);
        btnRegister= (ImageView) root.findViewById(R.id.btn_register);
        btnGuestList= (ImageView) root.findViewById(R.id.btn_guest_list);
        btnFaceUpdate= (ImageView) root.findViewById(R.id.btn_face_update);
        btnGuestApply= (ImageView) root.findViewById(R.id.btn_guest_apply);
        btnVisitRecord= (ImageView) root.findViewById(R.id.btn_visit_record);
        btnCallSpeak= (ImageView) root.findViewById(R.id.btn_call_speak);
        inOutRecords= (ImageView) root.findViewById(R.id.in_out_records);

        is =this.getResources().openRawResource(R.raw.open_door);
        bitmap = MyBitmapFactory.decodeRawBitMap(is);
        btnOpenDoor.setImageBitmap(bitmap);

        is =this.getResources().openRawResource(R.raw.register);
        bitmap = MyBitmapFactory.decodeRawBitMap(is);
        btnRegister.setImageBitmap(bitmap);

        is =this.getResources().openRawResource(R.raw.guest_family);
        bitmap = MyBitmapFactory.decodeRawBitMap(is);
        btnGuestList.setImageBitmap(bitmap);

        is =this.getResources().openRawResource(R.raw.face_update);
        bitmap = MyBitmapFactory.decodeRawBitMap(is);
        btnFaceUpdate.setImageBitmap(bitmap);

        is =this.getResources().openRawResource(R.raw.guest_apply);
        bitmap = MyBitmapFactory.decodeRawBitMap(is);
        btnGuestApply.setImageBitmap(bitmap);

        is =this.getResources().openRawResource(R.raw.visit_record);
        bitmap = MyBitmapFactory.decodeRawBitMap(is);
        btnVisitRecord.setImageBitmap(bitmap);

        is =this.getResources().openRawResource(R.raw.call_speak);
        bitmap = MyBitmapFactory.decodeRawBitMap(is);
        btnCallSpeak.setImageBitmap(bitmap);

        is =this.getResources().openRawResource(R.raw.in_out_record);
        bitmap = MyBitmapFactory.decodeRawBitMap(is);
        inOutRecords =(ImageView)root.findViewById(R.id.in_out_records);
        inOutRecords.setImageBitmap(bitmap);



        btnOpenDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MyQRCodeActivity.class);
                startActivity(intent);
            }
        });

        btnGuestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),FamilyGuestActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });


        btnFaceUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),FaceUpdateActivity.class);
                startActivity(intent);
            }
        });
        btnGuestApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),GuestApplyActivity.class);
                startActivity(intent);
            }
        });
        btnVisitRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),VisitRecordActivity.class);
                startActivity(intent);
            }
        });
//        btnCallSpeak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getContext(),CallSpeakActivity.class);
//                startActivity(intent);
//            }
//        });
        inOutRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),RecordsListActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}
