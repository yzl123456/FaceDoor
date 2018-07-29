package com.slowlife.facedoor.adapter;

import android.content.Context;
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
import com.slowlife.facedoor.domain.Guest;
import com.slowlife.facedoor.domain.GuestApply;

import java.util.ArrayList;
import java.util.List;

public class GuestApplyAdapter extends BaseAdapter {

	private Context mContext;
	private List<GuestApply> list;
	private ArrayList<CheckItem> arrayList;
	private Listenner listenner = new Listenner();
	public GuestApplyAdapter(Context mContext, List<GuestApply> list) {
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

			arg1 = inflater.inflate(R.layout.item_guest_apply, arg2, false);
			viewHolder = new ViewHolder();
			viewHolder.name= (TextView) arg1.findViewById(R.id.tv_name);
//			viewHolder.statu= (TextView) arg1.findViewById(R.id.tv_statu);
			viewHolder.accept=(ImageView) arg1.findViewById(R.id.btn_accept);
			viewHolder.ignore=(ImageView) arg1.findViewById(R.id.btn_ignore);
			viewHolder.phone= (TextView) arg1.findViewById(R.id.tv_phone);
			viewHolder.face= (ImageView) arg1.findViewById(R.id.iv_face);

			if(list.get(arg0).getStatu().equals("已同意")){
				viewHolder.accept.setImageResource(R.drawable.accepted);
				viewHolder.ignore.setVisibility(View.INVISIBLE);
			}
			else if(list.get(arg0).getStatu().equals("已忽视")){
				viewHolder.accept.setImageResource(R.drawable.ignored);
				viewHolder.ignore.setVisibility(View.INVISIBLE);
			}
			else{
				viewHolder.accept.setImageResource(R.drawable.accept);
				viewHolder.ignore.setImageResource(R.drawable.ignore);
				viewHolder.accept.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						list.get(arg0).setStatu("已同意");
						viewHolder.accept.setImageResource(R.drawable.accepted);
						viewHolder.ignore.setVisibility(View.INVISIBLE);
						viewHolder.accept.setOnClickListener(null);
						viewHolder.ignore.setOnClickListener(null);
					}
				});
				viewHolder.ignore.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						list.get(arg0).setStatu("已忽视");
						viewHolder.accept.setImageResource(R.drawable.ignored);
						viewHolder.ignore.setVisibility(View.INVISIBLE);
						viewHolder.accept.setOnClickListener(null);
						viewHolder.ignore.setOnClickListener(null);
					}
				});
			}

			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		viewHolder.name.setText(list.get(arg0).getName());
//		viewHolder.statu.setText(list.get(arg0).getStatu());

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
		private ImageView face,accept,ignore;
	}
}
