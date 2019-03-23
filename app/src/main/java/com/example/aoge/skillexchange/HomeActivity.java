package com.example.aoge.skillexchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeActivity extends Fragment {
    private String TAG = this.getClass().getSimpleName();
    //装在所有动态添加的Item的LinearLayout容器
    private ListView HomeShowView;
    private JSONArray jsonArray;
    private List<Map<String, Object>> list;

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
        HomeShowView = (ListView)getView().findViewById(R.id.home_ll_show);
        list = new ArrayList<Map<String, Object>>();

        HomeUserRequest();

    }

    //添加ViewItem
    private void addViewItem() {

        System.out.println(list);

        SimpleAdapter adapter = new SimpleAdapter(getContext(), list,
                R.layout.item_home_show, new String[] { "username", "can",
                "want" }, new int[] {
                R.id.txt_h_username,
                R.id.txt_h_mainskill,
                R.id.txt_h_mainwant });
        // 给ListView设置适配器
        HomeShowView.setAdapter(adapter);

        HomeShowView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                                                    Bundle bundle = new Bundle();
                                                    System.out.println(arg2);
//                                          bundle.putInt("photo", username[arg2]);
//                                          bundle.putString("message", message[arg2]);
//                                          Intent intent = new Intent();
//                                          intent.putExtras(bundle);
//                                          intent.setClass(MainActivity.this, MoveList.class);
//                                          Log.i("message", message[arg2]);
//                                          startActivity(intent);
                                                }
                                            });





//        HomeShowView.addView(View.inflate(getContext(), R.layout.item_home_show, null));
//
//        View hotelEvaluateView = View.inflate(getContext(), R.layout.item_home_show, null);
//        addHotelNameView.addView(hotelEvaluateView);
//
//        View hotelEvaluateView1 = View.inflate(getContext(), R.layout.item_home_show, null);
//
//        addHotelNameView.addView(hotelEvaluateView1);


    }

    /**
     * link to server to depend whether the username and password are right.

     */
    public void HomeUserRequest() {
        //request url
        String url = "http://106.14.117.91:8080/SkillsExchangeServer/ShowUserServlet";    //注①
        String tag = "HomeUser";    //注②




        //get the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getView().getContext());

        //cancel the request queue that marked by "Register" in order to not request again.
        requestQueue.cancelAll(tag);

        //build StringRequest and set the request method "POST"(default "GET")
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String username,email,gender,location,can,want;
                            jsonArray = (JSONArray) new JSONObject(response).get("homeshow");  //注③

                            for(int i=0;i<jsonArray.length();i++){
                                Map<String, Object> map = new HashMap<String, Object>();

                                JSONObject subObiect = new JSONObject();
                                subObiect = (JSONObject)jsonArray.get(i);
                                map.put("username",subObiect.getString("username"));
                                map.put("can",subObiect.getString("can"));
                                map.put("want",subObiect.getString("want"));
                                list.add(map);
//                                System.out.println(map);
//                                System.out.println(list);

                                username = subObiect.getString("username");
                                email = subObiect.getString("email");
                                gender = subObiect.getString("gender");
                                location = subObiect.getString("location");
                                can = subObiect.getString("can");
                                want = subObiect.getString("want");
//                                System.out.println(username+email+gender+location+can+want);
                            }
                            addViewItem();
                        } catch (JSONException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getView().getContext(),
                        "No internet!", Toast.LENGTH_LONG)
                        .show();
                Log.e("TAG", error.getMessage(), error);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Mark", "home");  //set the parameter.
                return params;
            }
        };

        //set the tag.
        request.setTag(tag);

        //add the request to queue.
        requestQueue.add(request);
    }
}
