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
//    /**
//     * Item排序
//     */
//    private void sortHotelViewItem() {
//        //获取LinearLayout里面所有的view
//        for (int i = 0; i < addHotelNameView.getChildCount(); i++) {
//            final View childAt = addHotelNameView.getChildAt(i);
//            final Button btn_remove = (Button) childAt.findViewById(R.id.btn_addHotel);
//            btn_remove.setText("删除");
//            btn_remove.setTag("remove");//设置删除标记
//            btn_remove.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //从LinearLayout容器中删除当前点击到的ViewItem
//                    addHotelNameView.removeView(childAt);
//                }
//            });
//            //如果是最后一个ViewItem，就设置为添加
//            if (i == (addHotelNameView.getChildCount() - 1)) {
//                Button btn_add = (Button) childAt.findViewById(R.id.btn_addHotel);
//                btn_add.setText("+新增");
//                btn_add.setTag("add");
//                btn_add.setOnClickListener(this);
//            }
//        }
//    }

    //添加ViewItem
    private void addViewItem() {

        View hotelEvaluateView = View.inflate(getContext(), R.layout.item_home_show, null);
        addHotelNameView.addView(hotelEvaluateView);

        View hotelEvaluateView1 = View.inflate(getContext(), R.layout.item_home_show, null);

        addHotelNameView.addView(hotelEvaluateView1);


//        if (addHotelNameView.getChildCount() == 0) {//如果一个都没有，就添加一个
//            View hotelEvaluateView = View.inflate(this, R.layout.item_hotel_evaluate, null);
//            Button btn_add = (Button) hotelEvaluateView.findViewById(R.id.btn_addHotel);
//            btn_add.setText("+新增");
//            btn_add.setTag("add");
//            btn_add.setOnClickListener(this);
//            addHotelNameView.addView(hotelEvaluateView);
//            //sortHotelViewItem();
//        } else if (((String) view.getTag()).equals("add")) {//如果有一个以上的Item,点击为添加的Item则添加
//            View hotelEvaluateView = View.inflate(this, R.layout.item_hotel_evaluate, null);
//            addHotelNameView.addView(hotelEvaluateView);
//            sortHotelViewItem();
//        }
        //else {
        //  sortHotelViewItem();
        //}
    }
//    //获取所有动态添加的Item，找到控件的id，获取数据
//    private void printData() {
//        for (int i = 0; i < addHotelNameView.getChildCount(); i++) {
//            View childAt = addHotelNameView.getChildAt(i);
//            EditText hotelName = (EditText) childAt.findViewById(R.id.ed_hotelName);
//            RatingBar hotelEvaluateStart = (RatingBar) childAt.findViewById(R.id.rb_hotel_evaluate);
//            EditText hotelEvaluate = (EditText) childAt.findViewById(R.id.ed_hotelEvaluate);
//            Log.e(TAG, "酒店名称：" + hotelName.getText().toString() + "-----评价星数："
//                    + (int) hotelEvaluateStart.getRating() + "-----服务评价：" + hotelEvaluate.getText().toString());
//        }
//    }


}
