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
import com.ogresolutions.kaogire.smarthouse.objects.Service;

import java.util.ArrayList;

/**
 * Created by Njuguna on 15-Jun-16.
 */
public class ServiceAdapter extends BaseAdapter {
    ArrayList<Service> services= new ArrayList<>();
    TextView tvType, tvAmount, tvPenalty, tvDueDate, tvOverDue, tvPaidDate;
    private static LayoutInflater inflater = null;

    public ServiceAdapter(Context context, ArrayList<Service> services){
        this.services = services;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return services.size();
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
            convertView = inflater.inflate(R.layout.service_item, parent, false);
        }
        Service service = services.get(position);
        tvAmount = (TextView)convertView.findViewById(R.id.tvSerAmount);
        tvDueDate = (TextView)convertView.findViewById(R.id.tvSerDatePaid);
        tvPaidDate = (TextView)convertView.findViewById(R.id.tvSerDateDue);
        tvPenalty = (TextView)convertView.findViewById(R.id.tvSerPenalty);
        tvType = (TextView)convertView.findViewById(R.id.tvSerVoteHead);
        tvOverDue = (TextView)convertView.findViewById(R.id.tvSerOverdue);

        if(service.getOverDue()){
            tvOverDue.setText("Overdue");
            tvOverDue.setTextColor(Color.RED);
            tvOverDue.setTextSize(10);
            if (service.getSettled()){
                tvOverDue.setText("Settled");
                tvOverDue.setTextColor(Color.GREEN);
            }
        }else{
            if (!service.getSettled()){
                tvOverDue.setText("Pending");
                tvOverDue.setTextColor(Color.MAGENTA);
            }else{
                tvOverDue.setText("Settled");
                tvOverDue.setTextColor(Color.GREEN);
            }
        }
        if(service.getDatePaid() == null){
            tvPaidDate.setText("Not Paid");
            tvPaidDate.setTextColor(Color.RED);
        }else{
            tvPaidDate.setText(MyTime.displayDate(service.getDatePaid()));
        }
        if(service.getSettled()){
            tvPaidDate.setText(MyTime.displayDate(service.getDatePaid()));
        }else{
            tvPaidDate.setText("Pending");
            tvPaidDate.setTextColor(Color.MAGENTA);
        }
        tvAmount.setText(service.getAmountDue());
        tvType.setText(service.getType());
        tvDueDate.setText(MyTime.displayDate(service.getDateDue()));
        return convertView;
    }
}
