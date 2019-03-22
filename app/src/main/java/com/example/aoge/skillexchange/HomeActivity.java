package com.example.aoge.skillexchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends Fragment {
    private String TAG = this.getClass().getSimpleName();
    //装在所有动态添加的Item的LinearLayout容器
    private LinearLayout addHotelNameView;

    private TextView textView;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_home,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addHotelNameView = (LinearLayout)getView().findViewById(R.id.home_ll_show);
        addViewItem();

    }

    //添加ViewItem
    private void addViewItem() {

        View hotelEvaluateView = View.inflate(getContext(), R.layout.item_home_show, null);
        addHotelNameView.addView(hotelEvaluateView);

        View hotelEvaluateView1 = View.inflate(getContext(), R.layout.item_home_show, null);

        addHotelNameView.addView(hotelEvaluateView1);


    }
}
