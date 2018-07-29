package com.slowlife.facedoor.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.slowlife.facedoor.R;
import com.slowlife.facedoor.domain.Tab;

public class HomeActivity extends FragmentActivity {
    private RadioButton meRadioButton,homeRadioButton,shopRadioButton,communityRadioButton;
    private RadioGroup raGroup;
    private ImageButton btnOpenDoor;
    private ImageButton btnRegister;
    private ImageButton btnGuestList;

    private Button btnFaceUpdate;
    private Button btnGuestApply;
    private Button btnVisitRecord;
    private Button btnCallSpeak;


    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {DoorFragment.class,CommunityFragment.class,ShopFragment.class,MeFragment.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.door,R.drawable.community,R.drawable.shop,
            R.drawable.me};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"门禁", "社区", "商城", "个人"};

    private TextView tvDoor;
    private TextView tvCommunity;
    private TextView tvShop;
    private TextView tvMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
//        Tab tab_home = new Tab(HomeFragment.class,R.string.home,R.drawable.selector_icon_home);
//        Tab tab_hot = new Tab(HotFragment.class,R.string.hot,R.drawable.selector_icon_hot);
//        Tab tab_category = new         Tab(CategoryFragment.class,R.string.catagory,R.drawable.selector_icon_category);
//        Tab tab_cart = new Tab(CartFragment.class,R.string.cart,R.drawable.selector_icon_cart);
//        Tab tab_mine = new Tab(MineFragment.class,R.string.mine,R.drawable.selector_icon_mine);
//
//        mTabs.add(tab_home);
//        mTabs.add(tab_hot);
//        mTabs.add(tab_category);
//        mTabs.add(tab_cart);
//        mTabs.add(tab_mine);

//        homeRadioButton= (RadioButton) findViewById(R.id.rb_home_homeactivity);
//        communityRadioButton= (RadioButton) findViewById(R.id.rb_community_homeactivity);
//        shopRadioButton= (RadioButton) findViewById(R.id.rb_shop_homeactivity);
//        meRadioButton= (RadioButton) findViewById(R.id.rb_me_homeactivity);
//        raGroup = (RadioGroup) findViewById(R.id.radioGroup_homeactivity);
//        btnOpenDoor= (ImageButton) findViewById(R.id.btn_open_door);
//        btnRegister= (ImageButton) findViewById(R.id.btn_register);
//        btnGuestList= (ImageButton) findViewById(R.id.btn_guest_list);
//        btnFaceUpdate= (Button) findViewById(R.id.btn_face_update);
//        btnGuestApply= (Button) findViewById(R.id.btn_guest_apply);
//        btnVisitRecord= (Button) findViewById(R.id.btn_visit_record);
//        btnCallSpeak= (Button) findViewById(R.id.btn_call_speak);
//
//        btnOpenDoor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(HomeActivity.this,MyQRCodeActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnGuestList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(HomeActivity.this,FamilyGuestActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(HomeActivity.this,RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        raGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup arg0, int arg1) {
//                switch (arg1) {
//                    case R.id.rb_me_homeactivity://个人
//                        meRadioButton.setTextColor(getResources().getColor(R.color.style));
//                        shopRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                        homeRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                        communityRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                        break;
//                    case R.id.rb_shop_homeactivity://商城
//
//                        break;
//                    case R.id.rb_community_homeactivity://社区
//
//                        homeRadioButton.setTextColor(getResources().getColor(R.color.style));
//                        communityRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                        shopRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                        meRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                        break;
//                    case R.id.rb_home_homeactivity://主页
//                        homeRadioButton.setTextColor(getResources().getColor(R.color.style));
//                        shopRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                        communityRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                        meRadioButton.setTextColor(getResources().getColor(R.color.rb_unpressed));
//                    break;
//
//                    default:
//                        break;
//                }
//            }
//        });
//        btnFaceUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(HomeActivity.this,FaceUpdateActivity.class);
//                startActivity(intent);
//            }
//        });
//        btnGuestApply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(HomeActivity.this,GuestApplyActivity.class);
//                startActivity(intent);
//            }
//        });
//        btnVisitRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(HomeActivity.this,VisitRecordActivity.class);
//                startActivity(intent);
//            }
//        });
//        btnCallSpeak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(HomeActivity.this,CallSpeakActivity.class);
//                startActivity(intent);
//            }
//        });


    }
    /**
     * 初始化组件
     */
    private void initView(){
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        int count = fragmentArray.length;

        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
//                mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
        tvDoor= (TextView) mTabHost.getTabWidget().getChildTabViewAt(0).findViewById(R.id.textview);
        tvCommunity= (TextView) mTabHost.getTabWidget().getChildTabViewAt(1).findViewById(R.id.textview);
        tvShop= (TextView) mTabHost.getTabWidget().getChildTabViewAt(2).findViewById(R.id.textview);
        tvMe= (TextView) mTabHost.getTabWidget().getChildTabViewAt(3).findViewById(R.id.textview);
        tvDoor.setTextColor(getResources().getColor(R.color.style));
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("门禁")){
                    tvDoor.setTextColor(getResources().getColor(R.color.style));
                    tvCommunity.setTextColor(getResources().getColor(R.color.rb_unpressed));
                    tvShop.setTextColor(getResources().getColor(R.color.rb_unpressed));
                    tvMe.setTextColor(getResources().getColor(R.color.rb_unpressed));
                }
                else if(tabId.equals("社区")){
                    tvCommunity.setTextColor(getResources().getColor(R.color.style));
                    tvDoor.setTextColor(getResources().getColor(R.color.rb_unpressed));
                    tvShop.setTextColor(getResources().getColor(R.color.rb_unpressed));
                    tvMe.setTextColor(getResources().getColor(R.color.rb_unpressed));
                }
                else if(tabId.equals("商城")){
                    tvShop.setTextColor(getResources().getColor(R.color.style));
                    tvCommunity.setTextColor(getResources().getColor(R.color.rb_unpressed));
                    tvDoor.setTextColor(getResources().getColor(R.color.rb_unpressed));
                    tvMe.setTextColor(getResources().getColor(R.color.rb_unpressed));
                }
                else if(tabId.equals("个人")){
                    tvMe.setTextColor(getResources().getColor(R.color.style));
                    tvCommunity.setTextColor(getResources().getColor(R.color.rb_unpressed));
                    tvShop.setTextColor(getResources().getColor(R.color.rb_unpressed));
                    tvDoor.setTextColor(getResources().getColor(R.color.rb_unpressed));
                }
            }
        });
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){

        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);


        return view;
    }
}
