package com.slowlife.facedoor.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.slowlife.facedoor.activity.FamilyGuestActivity;
import com.slowlife.facedoor.activity.FamilyInfoDetailActivity;
import com.slowlife.facedoor.activity.FamilyInfoEditActivity;
import com.slowlife.facedoor.domain.FamilyGuest;

import java.util.ArrayList;
import java.util.List;

public class FamilyGuestAdapter extends BaseAdapter {

	private Context mContext;
	private List<FamilyGuest> list;
	private ArrayList<CheckItem> arrayList;
	private Listenner listenner = new Listenner();
	public FamilyGuestAdapter(Context mContext, List<FamilyGuest> list) {
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
		if (arg1 == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			arg1 = inflater.inflate(R.layout.item_family_guest, arg2, false);
			viewHolder = new ViewHolder();
			viewHolder.name= (TextView) arg1.findViewById(R.id.tv_name);
			viewHolder.relation= (TextView) arg1.findViewById(R.id.tv_relation);
			viewHolder.phone= (TextView) arg1.findViewById(R.id.tv_phone);
			viewHolder.face= (ImageView) arg1.findViewById(R.id.iv_face);

			ImageView edit= (ImageView) arg1.findViewById(R.id.edit);
			ImageView detail= (ImageView) arg1.findViewById(R.id.detail);
			edit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(mContext,FamilyInfoEditActivity.class);

					intent.putExtra("face",list.get(arg0).getFace());
					intent.putExtra("name",list.get(arg0).getName());
					intent.putExtra("sex",list.get(arg0).getSex());
					intent.putExtra("relation",list.get(arg0).getRelation());
					intent.putExtra("phone",list.get(arg0).getPhone());
					mContext.startActivity(intent);

				}
			});
			detail.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(mContext,FamilyInfoDetailActivity.class);

					intent.putExtra("face",list.get(arg0).getFace());
					intent.putExtra("name",list.get(arg0).getName());
					intent.putExtra("sex",list.get(arg0).getSex());
					intent.putExtra("relation",list.get(arg0).getRelation());
					intent.putExtra("phone",list.get(arg0).getPhone());
					mContext.startActivity(intent);

				}
			});





			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		viewHolder.name.setText(list.get(arg0).getName());
		viewHolder.relation.setText(list.get(arg0).getRelation());
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
		private TextView name,sex,relation,phone,IDCard;
		private ImageView face;
	}
}
