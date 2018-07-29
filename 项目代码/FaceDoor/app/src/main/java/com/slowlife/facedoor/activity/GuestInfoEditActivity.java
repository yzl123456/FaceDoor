package com.slowlife.facedoor.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.slowlife.facedoor.R;
import com.slowlife.facedoor.utils.MyBitmapFactory;

import java.io.InputStream;
import java.util.Calendar;

public class GuestInfoEditActivity extends AppCompatActivity {
    private ImageView face;
    private EditText etName;
    private Spinner spSex;
    private Spinner doorLimit;
    private EditText phone;
    private EditText carNumber;
    private Button btnBack;
    private ImageView reset_born;
    private InputStream is;
    private Bitmap b;
    private TextView born;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_info_edit);
        btnBack= (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        born = (TextView) findViewById(R.id.tv_date);

        is = this.getResources().openRawResource(R.raw.date);
        b = MyBitmapFactory.decodeRawBitMap(is);

        reset_born = (ImageView) findViewById(R.id.ib_reset_born_mydata);
        reset_born.setImageBitmap(b);

        reset_born.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showDialog();
            }
        });


        face= (ImageView) findViewById(R.id.face);
        etName= (EditText) findViewById(R.id.tv_name);
        spSex= (Spinner) findViewById(R.id.sp_sex);
        doorLimit= (Spinner) findViewById(R.id.sp_door_limit);
        phone= (EditText) findViewById(R.id.et_phone);
        carNumber= (EditText) findViewById(R.id.car_number);


        Intent intent=getIntent();
        face.setImageResource(intent.getIntExtra("face",0));
        etName.setText(intent.getStringExtra("name"));
        setSpinnerItemSelectedByValue(spSex,intent.getStringExtra("sex"));
        setSpinnerItemSelectedByValue(doorLimit,intent.getStringExtra("doorLimit"));
        phone.setText(intent.getStringExtra("phone"));
        carNumber.setText(intent.getStringExtra("carNumber"));

    }
    /**

     * 根据值, 设置spinner默认选中:

     * @param spinner

     * @param value

     */

    public static void setSpinnerItemSelectedByValue(Spinner spinner,String value){

        SpinnerAdapter apsAdapter= spinner.getAdapter(); //得到SpinnerAdapter对象

        int k= apsAdapter.getCount();

        for(int i=0;i<k;i++){

            if(value.equals(apsAdapter.getItem(i).toString())){

                spinner.setSelection(i,true);// 默认选中项

                break;

            }

        }

    }
    private void showDialog() {
        Dialog dialog = null;
        Calendar c = Calendar.getInstance();
        dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int arg1, int arg2,
                                          int arg3) {
                        born.setText(+arg1 + "-" + (arg2 + 1) + "-" + arg3);
                    }
                }, c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );

        if (dialog != null) {
            dialog.show();
        }
    }
}
