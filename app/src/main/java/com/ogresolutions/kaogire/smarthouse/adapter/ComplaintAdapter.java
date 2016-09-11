package com.ogresolutions.kaogire.smarthouse.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogresolutions.kaogire.smarthouse.R;
import com.ogresolutions.kaogire.smarthouse.model.MyTime;
import com.ogresolutions.kaogire.smarthouse.objects.Complaint;

import java.util.ArrayList;

/**
 * Created by Njuguna on 15-Jun-16.
 */
public class ComplaintAdapter extends BaseAdapter {
    ArrayList<Complaint> complaints = new ArrayList<>();
    private static LayoutInflater inflater = null;
    TextView tvVHead, tvCategory, tvDetail, tvTime, tvAddressed, tvDAddressed;

    public ComplaintAdapter(Context context, ArrayList<Complaint> complaints){
        this.complaints = complaints;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return complaints.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.complaint_item, parent, false);
        }
        Complaint complaint = complaints.get(position);
        tvAddressed = (TextView)convertView.findViewById(R.id.tvCmpAddressed);
        tvCategory = (TextView)convertView.findViewById(R.id.tvCmpCategory);
        tvTime = (TextView)convertView.findViewById(R.id.tvCmpDate);
        tvDetail = (TextView)convertView.findViewById(R.id.tvCmpDetail);
        tvVHead = (TextView)convertView.findViewById(R.id.tvCmpVoteHead);
        tvDAddressed = (TextView)convertView.findViewById(R.id.tvCmpDAddressed);
        if(complaint.isAddressed()){
            tvAddressed.setText("Addressed");
            tvAddressed.setTextColor(Color.GREEN);
            tvDAddressed.setText(MyTime.displayDate(complaint.getdAddressed()));
        }else{
            tvAddressed.setText("Pending");
            tvAddressed.setTextColor(Color.RED);
            tvDAddressed.setVisibility(View.GONE);
        }
        tvVHead.setText(complaint.getVoteHead());
        tvCategory.setText(complaint.getCategory());
        tvDetail.setText(complaint.getDetail());
        tvTime.setText(MyTime.displayDate(complaint.getTime()));
        return convertView;
    }
}
