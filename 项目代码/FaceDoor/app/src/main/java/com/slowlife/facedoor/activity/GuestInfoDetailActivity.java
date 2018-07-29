package com.slowlife.facedoor.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.slowlife.facedoor.R;

public class GuestInfoDetailActivity extends AppCompatActivity {
    private ImageView face;
    private TextView etName;
    private TextView spSex;
    private TextView visitTime;
    private TextView phone;
    private TextView doorLimit;
    private TextView carNumber;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_info_detail);
        btnBack= (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        face= (ImageView) findViewById(R.id.face);
        etName= (TextView) findViewById(R.id.tv_name);
        spSex= (TextView) findViewById(R.id.tv_sex);
        phone= (TextView) findViewById(R.id.tv_phone);
        visitTime= (TextView) findViewById(R.id.tv_visit_time);
        doorLimit= (TextView) findViewById(R.id.tv_door_limit);
        carNumber= (TextView) findViewById(R.id.tv_car_number);

        Intent intent=getIntent();
        face.setImageResource(intent.getIntExtra("face",0));
        etName.setText(intent.getStringExtra("name"));
        spSex.setText(intent.getStringExtra("sex"));
        phone.setText(intent.getStringExtra("phone"));
        visitTime.setText(intent.getStringExtra("visitTime"));
        doorLimit.setText(intent.getStringExtra("doorLimit"));
        carNumber.setText(intent.getStringExtra("carNumber"));


    }
    /**

     * 根据值, 设置spinner默认选中:

     * @param spinner

     * @param value

     */

    public static void setSpinnerItemSelectedByValue(Spinner spinner, String value){

        SpinnerAdapter apsAdapter= spinner.getAdapter(); //得到SpinnerAdapter对象

        int k= apsAdapter.getCount();

        for(int i=0;i<k;i++){

            if(value.equals(apsAdapter.getItem(i).toString())){

                spinner.setSelection(i,true);// 默认选中项

                break;

            }

        }

    }
}
