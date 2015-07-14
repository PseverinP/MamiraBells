package com.androidcollider.mamirabells.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcollider.mamirabells.R;
import com.androidcollider.mamirabells.models.Bell;

import java.util.ArrayList;

/**
 * Created by s.parkhomenko on 28.05.2015.
 */
public class BellAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    public ArrayList<Bell> bells;

    public BellAdapter(Context context, ArrayList<Bell> bells) {
        this.context = context;
        this.bells = bells;
        lInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    // the number of elements
    @Override
    public int getCount() {
        return bells.size();
    }

    // element at position
    @Override
    public Bell getItem(int position) {
        return bells.get(position);
    }

    // id at position
    @Override
    public long getItemId(int position) {
        return position;
    }

    // create viewholder
    static class ViewHolder {
        public ImageView imageBell;
        public TextView tvName;
        public TextView tvDate;
        public TextView tvDescription;
    }

    // list item_for_routes_listview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = lInflater.inflate(R.layout.item_bell, parent, false);

            holder = new ViewHolder();
            holder.imageBell = (ImageView)view.findViewById(R.id.image_bell);
            holder.tvName = (TextView)view.findViewById(R.id.tv_name);
            holder.tvDescription = (TextView)view.findViewById(R.id.tv_description);
            holder.tvDate = (TextView)view.findViewById(R.id.tv_date);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Bell bell = getItem(position);
       /* holder.tv_rates_name.setText(ratesTypeNames[bankRates.getBankType()]);

        holder.tv_ask.setText(Utils.doubleFormatter(bankRates.getAsk(),FORMAT,PRECISE));
        if (bankRates.getAskDelta()>0){
            holder.tv_ask_delta.setText("+"+Utils.doubleFormatter(bankRates.getAskDelta(),FORMAT,PRECISE));
            holder.tv_ask_delta.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            holder.tv_ask_delta.setText(Utils.doubleFormatter(bankRates.getAskDelta(),FORMAT,PRECISE));
            holder.tv_ask_delta.setTextColor(context.getResources().getColor(R.color.darker_red));
        }
        holder.tv_bid.setText(Utils.doubleFormatter(bankRates.getBid(),FORMAT,PRECISE));
        if (bankRates.getBidDelta()>0){
            holder.tv_bid_delta.setText("+"+Utils.doubleFormatter(bankRates.getBidDelta(),FORMAT,PRECISE));
            holder.tv_bid_delta.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            holder.tv_bid_delta.setText(Utils.doubleFormatter(bankRates.getBidDelta(),FORMAT,PRECISE));
            holder.tv_bid_delta.setTextColor(context.getResources().getColor(R.color.darker_red));
        }*/

        return view;
    }
}
