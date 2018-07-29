package com.slowlife.facedoor.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.slowlife.facedoor.R;
import com.slowlife.facedoor.activity.FamilyInfoDetailActivity;
import com.slowlife.facedoor.activity.FamilyInfoEditActivity;
import com.slowlife.facedoor.activity.GuestInfoDetailActivity;
import com.slowlife.facedoor.activity.GuestInfoEditActivity;
import com.slowlife.facedoor.activity.ShareQRCodeActivity;
import com.slowlife.facedoor.domain.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestAdapter extends BaseAdapter {

	private Context mContext;
	private List<Guest> list;
	private ArrayList<CheckItem> arrayList;
	private Listenner listenner = new Listenner();
	public GuestAdapter(Context mContext, List<Guest> list) {
		this.list = list;
		this.mContext = mContext;

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		final ViewHolder viewHolder;
		Log.e("TAG",list.get(0).toString());
		if (arg1 == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);

			arg1 = inflater.inflate(R.layout.item_guest, arg2, false);
			viewHolder = new ViewHolder();
			viewHolder.name= (TextView) arg1.findViewById(R.id.tv_name);
			TextView tvStatu=(TextView) arg1.findViewById(R.id.tv_statu);
			if(list.get(arg0).getStatu().equals("使用中")){
				tvStatu.setBackgroundResource(R.color.isUsing);
				tvStatu.setTextColor(mContext.getResources().getColor(R.color.white));
			}
			else if(list.get(arg0).getStatu().equals("待使用")){
				tvStatu.setBackgroundResource(R.color.needUse);
				tvStatu.setTextColor(mContext.getResources().getColor(R.color.white));
			}
			viewHolder.statu= tvStatu;
			viewHolder.phone= (TextView) arg1.findViewById(R.id.tv_phone);
			viewHolder.face= (ImageView) arg1.findViewById(R.id.iv_face);
			ImageView edit= (ImageView) arg1.findViewById(R.id.edit);
			ImageView detail= (ImageView) arg1.findViewById(R.id.detail);
			ImageView qrcode=(ImageView) arg1.findViewById(R.id.QRCode);
			edit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(mContext,GuestInfoEditActivity.class);

					intent.putExtra("face",list.get(arg0).getFace());
					intent.putExtra("name",list.get(arg0).getName());
					intent.putExtra("sex",list.get(arg0).getSex());
					intent.putExtra("visitTime",list.get(arg0).getVisitTime());
					intent.putExtra("doorLimit",list.get(arg0).getDueTime());
					intent.putExtra("carNumber",list.get(arg0).getCarNumber());
					intent.putExtra("phone",list.get(arg0).getPhone());
					mContext.startActivity(intent);

				}
			});
			detail.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(mContext,GuestInfoDetailActivity.class);

					intent.putExtra("face",list.get(arg0).getFace());
					intent.putExtra("name",list.get(arg0).getName());
					intent.putExtra("sex",list.get(arg0).getSex());
					intent.putExtra("visitTime",list.get(arg0).getVisitTime());
					intent.putExtra("doorLimit",list.get(arg0).getDueTime());
					intent.putExtra("carNumber",list.get(arg0).getCarNumber());
					intent.putExtra("phone",list.get(arg0).getPhone());
					mContext.startActivity(intent);

				}
			});
			qrcode.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(mContext,ShareQRCodeActivity.class);
					mContext.startActivity(intent);
				}
			});



			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		viewHolder.name.setText(list.get(arg0).getName());
		viewHolder.statu.setText(list.get(arg0).getStatu());
		viewHolder.phone.setText(list.get(arg0).getPhone());
		viewHolder.face.setImageResource(list.get(arg0).getFace());
		return arg1;
	}
	class CheckItem {
		int position;
		boolean ischeck = true;

		public CheckItem(boolean ischeck) {
			this.ischeck = ischeck;
		}

		public void setChick(boolean ischeck) {
			this.ischeck = ischeck;
		}
	}

	class Listenner implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			CheckItem item = (CheckItem) arg0.getTag();
			setCheck(item.position, !item.ischeck);
		}
	}

	private void setCheck(int postion, boolean ischeck) {
		if (!ischeck) {
			Toast.makeText(mContext, "不能取消勾选，请直接选择其余的物业", Toast.LENGTH_SHORT)
					.show();
		} else {
			arrayList.set(postion, new CheckItem(ischeck));
			for (int i = 0; i < postion; i++) {
				arrayList.get(i).ischeck = false;
			}
			for (int i = postion + 1; i < arrayList.size(); i++) {
				arrayList.get(i).ischeck = false;
			}

		}

		this.notifyDataSetChanged();
	}

	class ViewHolder {
		private TextView name,statu,phone,carnumber;
		private ImageView face;
	}
}
