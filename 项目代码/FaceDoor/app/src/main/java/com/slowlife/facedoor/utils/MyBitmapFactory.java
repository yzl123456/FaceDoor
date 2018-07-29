package com.slowlife.facedoor.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mengcz on 2016/3/7.
 */
public class MyBitmapFactory extends BitmapFactory {
    public static Bitmap decodeRawBitMap(InputStream inputStream){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        Bitmap b = BitmapFactory.decodeStream(inputStream, null, opt);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}
