package com.example.android.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private List<String> messageArray;
    private ArrayAdapter<String> msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String[] values = savedInstanceState.getStringArray("myKey");
            if (values != null) {
                messageArray = new ArrayList<String>(Arrays.asList(values));
            }
        } else {
            messageArray = new ArrayList<String>();
        }

        msgAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messageArray);
        listView = (ListView) findViewById(R.id.msgview);
        listView.setAdapter(msgAdapter);
    }

    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        editText = (EditText) findViewById(R.id.msg);
        String message = editText.getText().toString();
        messageArray.add(message);
        msgAdapter.notifyDataSetChanged();
        editText.setText("");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        String[] values = new String[msgAdapter.getCount()];
        for (int i = 0; i < msgAdapter.getCount(); i++) {
            values[i] = msgAdapter.getItem(i);
        }
        savedInstanceState.putStringArray("myKey", values);

    }
}
