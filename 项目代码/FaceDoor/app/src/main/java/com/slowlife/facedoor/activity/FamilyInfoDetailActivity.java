package com.slowlife.facedoor.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.slowlife.facedoor.R;

public class FamilyInfoDetailActivity extends AppCompatActivity {
    private ImageView face;
    private TextView etName;
    private TextView spSex;
    private TextView relation;
    private TextView phone;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info_detail);
        btnBack= (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        face= (ImageView) findViewById(R.id.face);
        etName= (TextView) findViewById(R.id.tv_name);
        spSex= (TextView) findViewById(R.id.sp_sex);
        relation= (TextView) findViewById(R.id.sp_relation);
        phone= (TextView) findViewById(R.id.et_phone);

        Intent intent=getIntent();
        etName.setText(intent.getStringExtra("name"));
        spSex.setText(intent.getStringExtra("sex"));
        relation.setText(intent.getStringExtra("relation"));
        phone.setText(intent.getStringExtra("phone"));
        face.setImageResource(intent.getIntExtra("face",0));

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
}
