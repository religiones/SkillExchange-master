package com.example.aoge.skillexchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Set;


public class MsgActivity extends BaseActivity {
    private UserInformation user;
    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private TextView Uname;
    private String email;
    private String talkto;

//    public  String HOST = "169.254.26.233";//服务器地址
//    public  String HOST = "172.19.101.46";//服务器地址106.14.117.91
    public  String HOST = "106.14.117.91";
    public  int PORT = 8800;//连接端口号
    public  Socket socket = null;
    public  BufferedReader in = null;
    public  PrintWriter out = null;


    private List<Msg> msgList = new ArrayList<Msg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_msg);

//        Intent intent = getIntent();
//        user = (UserInformation) intent.getSerializableExtra("key");
//        Uname = (TextView)findViewById(R.id.txt_msg_username);
//        Uname.setText(user.getUserName());
//        email = user.getEmail();

        initMsgs(); // 初始化消息数据
        adapter = new MsgAdapter(MsgActivity.this, R.layout.item_msg_show, msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//

                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    if (socket.isConnected()) {//如果服务器连接
                        if (!socket.isOutputShutdown()) {//如果输出流没有断开
                            Msg msg = new Msg(content, Msg.TYPE_SENT,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+
                                    String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)),"Yes");
                            msgList.add(msg);
                            adapter.notifyDataSetChanged(); // 当有新消息时，刷新ListView中的显示
                            msgListView.setSelection(msgList.size()); // 将ListView定位到最后一行

                            new Thread(runnableout).start();
                        }
                    }
                }
            }
        });


//        btn_send.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String msg = ed_msg.getText().toString();
//                if (socket.isConnected()) {//如果服务器连接
//                    if (!socket.isOutputShutdown()) {//如果输出流没有断开
//                        new Thread(runnableout).start();
//
//                    }
//                }
//            }
//        });

        new Thread(runnable).start();

    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED, String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+
                String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)),"Yes");
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+
                String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)),"Yes");
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ",
                Msg.TYPE_RECEIVED,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+
                String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)),"Yes");
        msgList.add(msg3);
    }




    Runnable runnableout = new Runnable(){
        @Override
        public void run() {
//            Map<String, String> params = new HashMap<>();
//            params.put("Sender", "asdas");  //set the parameter.
//            params.put("Receiver", "kkkkk");
//            params.put("Text", inputText.getText().toString());
//            String outstring = getMapToString(params);
            out.println("daniel"+",,,,,"+"daniel"+",,,,,"+inputText.getText().toString());
//            System.out.println(params);
//            System.out.println(outstring);
            inputText.setText(""); // 清空输入框中的内容
        }
    };



    /**
     * 读取服务器发来的信息，并通过Handler发给UI线程
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            connection();// 连接到服务器
            try {
                while (true) {//死循环守护，监控服务器发来的消息
                    if (!socket.isClosed()) {//如果服务器没有关闭
                        if (socket.isConnected()) {//连接正常
                            if (!socket.isInputShutdown()) {//如果输入流没有断开
                                String getLine;
                                if ((getLine = in.readLine()) != null) {//读取接收的信息
                                    getLine += "\n";
                                    Message message = new Message();
                                    message.obj = getLine;
                                    mHandler.sendMessage(message);//通知UI更新
                                } else {

                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };



    //    接收线程发送过来信息，并用TextView追加显示
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            tv_msg.append((CharSequence) msg.obj);
//            System.out.println(msg.obj);
            String m = (String)msg.obj;
            String[]str = m.split(",,,,,");
            Msg msgs = new Msg(str[2], Msg.TYPE_RECEIVED,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+
                    String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)),"Yes");
            msgList.add(msgs);
            adapter.notifyDataSetChanged(); // 当有新消息时，刷新ListView中的显示
            msgListView.setSelection(msgList.size()); // 将ListView定位到最后一行
        }
    };

    /**
     //     * 连接服务器
     //     */
    private void connection() {
        try {
            socket = new Socket(HOST, PORT);//连接服务器
            in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));//接收消息的流对象
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);//发送消息的流对象
            String information = "daniel"+",,,,,"+"daniel"+",,,,,"+"this is my text";
            out.println(information);
            System.out.println("Success");
        } catch (IOException ex) {
            ex.printStackTrace();
            ShowDialog("连接服务器失败：" + ex.getMessage());
        }
    }

    /**
     * 如果连接出现异常，弹出AlertDialog！
     */
    public void ShowDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("通知").setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void btnBack(View view){
//        out.println("exit");
//        File

    }
}


//
///**
// * Android Tcp即时通讯客户端
// */
//public class MsgActivity extends Activity{
//    private TextView tv_msg = null;
//    private EditText ed_msg = null;
//    private Button btn_send = null;
//    private static final String HOST = "169.254.26.233";//服务器地址
//    private static final int PORT = 8800;//连接端口号
//    private Socket socket = null;
//    private BufferedReader in = null;
//    private PrintWriter out = null;
//
//    //接收线程发送过来信息，并用TextView追加显示
//    public Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            tv_msg.append((CharSequence) msg.obj);
//            System.out.println(msg.obj);
//        }
//    };
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_msg);
//
//        tv_msg = (TextView) findViewById(R.id.txt_msg_username);
//        ed_msg = (EditText) findViewById(R.id.input_text);
//        btn_send = (Button) findViewById(R.id.send);
//
//        btn_send.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String msg = ed_msg.getText().toString();
//                if (socket.isConnected()) {//如果服务器连接
//                    if (!socket.isOutputShutdown()) {//如果输出流没有断开
//                        new Thread(runnableout).start();
//
//                    }
//                }
//            }
//        });
//        //启动线程，连接服务器，并用死循环守候，接收服务器发送过来的数据
//        new Thread(runnable).start();
//    }
//
//    /**
//     * 连接服务器
//     */
//    private void connection() {
//        try {
//            socket = new Socket(HOST, PORT);//连接服务器
//            in = new BufferedReader(new InputStreamReader(socket
//                    .getInputStream()));//接收消息的流对象
//            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
//                    socket.getOutputStream())), true);//发送消息的流对象
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            ShowDialog("连接服务器失败：" + ex.getMessage());
//        }
//    }
//
//    /**
//     * 如果连接出现异常，弹出AlertDialog！
//     */
//    public void ShowDialog(String msg) {
//        new AlertDialog.Builder(this).setTitle("通知").setMessage(msg)
//                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).show();
//    }
//
//
//    Runnable runnableout = new Runnable(){
//        @Override
//        public void run() {
//            out.println(ed_msg.getText().toString());//点击按钮发送消息
//            ed_msg.setText("");//清空编辑框
//        }
//    };
//
//
//    /**
//     * 读取服务器发来的信息，并通过Handler发给UI线程
//     */
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            connection();// 连接到服务器
//            try {
//                while (true) {//死循环守护，监控服务器发来的消息
//                    if (!socket.isClosed()) {//如果服务器没有关闭
//                        if (socket.isConnected()) {//连接正常
//                            if (!socket.isInputShutdown()) {//如果输入流没有断开
//                                String getLine;
//                                if ((getLine = in.readLine()) != null) {//读取接收的信息
//                                    getLine += "\n";
//                                    Message message = new Message();
//                                    message.obj = getLine;
//                                    mHandler.sendMessage(message);//通知UI更新
//                                } else {
//
//                                }
//                            }
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };
//}