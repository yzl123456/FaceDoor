package com.slowlife.facedoor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.slowlife.facedoor.R;

public class FamilyInfoEditActivity extends AppCompatActivity {
    private ImageView face;
    private EditText etName;
    private Spinner spSex;
    private Spinner relation;
    private EditText phone;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info_edit);
        btnBack= (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        face= (ImageView) findViewById(R.id.face);
        etName= (EditText) findViewById(R.id.tv_name);
        spSex= (Spinner) findViewById(R.id.sp_sex);
        relation= (Spinner) findViewById(R.id.sp_relation);
        phone= (EditText) findViewById(R.id.et_phone);

        Intent intent=getIntent();
        face.setImageResource(intent.getIntExtra("face",0));
        etName.setText(intent.getStringExtra("name"));
        setSpinnerItemSelectedByValue(spSex,intent.getStringExtra("sex"));
        setSpinnerItemSelectedByValue(relation,intent.getStringExtra("relation"));
        phone.setText(intent.getStringExtra("phone"));

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
