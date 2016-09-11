package com.ogresolutions.kaogire.smarthouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogresolutions.kaogire.smarthouse.R;
import com.ogresolutions.kaogire.smarthouse.objects.Guest;

import java.util.ArrayList;

/**
 * Created by Njuguna on 4/26/2016.
 */
public class GuestAdapter extends BaseAdapter {
    ArrayList<Guest> myGuest = new ArrayList<Guest>();
    TextView tvName, tvTimeIn, tvTimeOut;
    private static LayoutInflater inflater = null;

    public GuestAdapter(Context context, ArrayList<Guest> myGuests) {
        this.myGuest = myGuests;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.myGuest.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.guest_item, parent, false);
        }
        Guest guest = myGuest.get(position);
        tvName = (TextView) convertView.findViewById(R.id.tvGuestName);
        tvTimeIn = (TextView) convertView.findViewById(R.id.tvGuestTimeIn);
        tvTimeOut = (TextView) convertView.findViewById(R.id.tvGuestTImeOut);
        tvName.setText(guest.getName());
        if (guest.getTimeIn() != null || guest.getTimeOut() != null) {
            tvTimeIn.setText(guest.getTimeIn().toString());
            tvTimeOut.setText(guest.getTimeOut().toString());
        }
        else{
            tvTimeIn.setVisibility(View.INVISIBLE);
            tvTimeOut.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
}
