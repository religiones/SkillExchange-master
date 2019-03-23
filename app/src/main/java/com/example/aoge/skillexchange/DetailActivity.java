package com.example.aoge.skillexchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        // 通过key得到得到对象
        // getSerializableExtra得到序列化数据
        UserInformation user = (UserInformation) intent.getSerializableExtra("key");
        System.out.println(user.getUserName());
    }
}
