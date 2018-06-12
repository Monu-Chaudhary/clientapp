package com.example.nugas.clientapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nugas.clientapp.model.ChatMessage;
import com.example.nugas.clientapp.network.ApiClient;
import com.example.nugas.clientapp.network.ApiInterface;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText textMessage;
    Button send;
    List<ChatMessage> messages;
    RecyclerView.LayoutManager layoutManager;
    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        messages = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        adapter = new ChatAdapter(this, messages);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textMessage.getText().toString().trim().equals("")) {
                    String msg = textMessage.getText().toString().trim();
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setText(msg);
                    addMessage(chatMessage);
                    getMessage(chatMessage);
                }
            }
        });
    }

    private void getMessage(ChatMessage chatMessage) {
        Retrofit retrofit = ApiClient.getClient();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<JsonObject> res = api.getMessage(chatMessage.getText());

        res.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject = response.body();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addMessage(ChatMessage chatMessage) {
        messages.add(chatMessage);
        adapter.notifyDataSetChanged();
        textMessage.getText().clear();
    }

    private void init() {
        recyclerView = findViewById(R.id.recylerView);
        textMessage = findViewById(R.id.message);
        send = findViewById(R.id.send);
    }
}
