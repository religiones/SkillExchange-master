package com.example.aoge.skillexchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class NearbyActivity extends Fragment {
    private TextView textView;
    private Button button;
    private LinearLayout addnearbyView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_nearby,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addnearbyView = (LinearLayout)getView().findViewById(R.id.nearby_ll_show);
        addViewItem();
    }

    //添加ViewItem
    private void addViewItem() {

        View hotelEvaluateView = View.inflate(getContext(), R.layout.item_nearby_show, null);
        addnearbyView.addView(hotelEvaluateView);
        View hotelEvaluateView1 = View.inflate(getContext(), R.layout.item_nearby_show, null);

        addnearbyView.addView(hotelEvaluateView1);
    }
}
