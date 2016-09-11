package com.ogresolutions.kaogire.smarthouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogresolutions.kaogire.smarthouse.R;
import com.ogresolutions.kaogire.smarthouse.model.MyTime;
import com.ogresolutions.kaogire.smarthouse.objects.Notice;

import java.util.ArrayList;

/**
 * Created by Njuguna on 5/1/2016.
 */
public class NoticeAdapter extends BaseAdapter {
    private ArrayList<Notice> myNotices= new ArrayList<Notice>();
    private TextView tvHouse, tvTitle, tvTime, tvBody;
    private static LayoutInflater inflater = null;

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public NoticeAdapter(Context context, ArrayList<Notice> theNotices){
        this.myNotices = theNotices;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myNotices.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.notice_item, parent, false);
        }

        Notice myNotice = myNotices.get(position);
        tvHouse = (TextView)convertView.findViewById(R.id.tvNoticeHouse);
        tvTime = (TextView)convertView.findViewById(R.id.tvNoticeTime);
        tvTitle = (TextView)convertView.findViewById(R.id.tvNoticeTitle);
        tvBody = (TextView)convertView.findViewById(R.id.tvNoticeBodyF);

        tvHouse.setText(myNotice.getHouseNo());
        tvTitle.setText(myNotice.getTitle());
//        tvBody.setText(myNotice.getBody());

        tvTime.setText(MyTime.displayDate(myNotice.getTimeUp()));

        return convertView;
    }
}
