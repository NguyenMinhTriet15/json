package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView txtview;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtview =  (TextView) findViewById(R.id.textView);
        Button btnparse = findViewById(R.id.button);
        mQueue = Volley.newRequestQueue(this);
        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonparse();
            }
        });
    }

    private void jsonparse() {
        String url = "https://api.jsonserve.com/NSZtiC";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("sinhvien");
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject sinhvien = jsonArray.getJSONObject(i);
                                String name = sinhvien.getString("name");
                                int studentid = sinhvien.getInt("studentid");
                                String mail = sinhvien.getString("mail");
                                String lop = sinhvien.getString("class");
                                int khoi = sinhvien.getInt("grade");
                                txtview.append(name + ", "+ String.valueOf(studentid) + ", " + mail + ", " + lop + ", " + String.valueOf(khoi) + "\n\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}