package com.slowlife.facedoor.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.slowlife.facedoor.R;
import com.slowlife.facedoor.utils.MyBitmapFactory;

import java.io.InputStream;

/**
 * Created by 泽林 on 2018/4/6.
 */

public class CommunityFragment extends Fragment {
    View root;
    ImageView page;
    InputStream is;
    Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.fragment_community, null);

//        is =this.getResources().openRawResource(R.raw.community);
//        bitmap = MyBitmapFactory.decodeRawBitMap(is);
//        page =(ImageView)root.findViewById(R.id.imageView);
//        page.setImageBitmap(bitmap);
        return root;
    }
}
