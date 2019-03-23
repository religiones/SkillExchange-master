package com.example.aoge.skillexchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends Activity {

    private UserInformation user;
    private TextView un;
    private TextView em;

    private TextView lo;
    private TextView ca;
    private TextView wa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        // 通过key得到得到对象
        // getSerializableExtra得到序列化数据
        un=(TextView)findViewById(R.id.txt_d_username);
        em=(TextView)findViewById(R.id.txt_d_email_show);
        lo=(TextView)findViewById(R.id.txt_d_location_show);
        ca=(TextView)findViewById(R.id.txt_d_can_show);
        wa=(TextView)findViewById(R.id.txt_d_want_show);
        user = (UserInformation) intent.getSerializableExtra("key");
        un.setText(user.getUserName());
        em.setText(user.getEmail());
        lo.setText(user.getLocation());
        ca.setText(user.getCan());
        wa.setText(user.getWant());
    }

    public void Chat(View view){
        Intent intent = new Intent(getApplicationContext(), MsgActivity.class);
        // 放入需要传递的对象
        intent.putExtra("key", user);
        // 启动意图
        startActivity(intent);
    }
}
