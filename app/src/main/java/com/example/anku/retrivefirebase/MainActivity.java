package com.example.anku.retrivefirebase;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mvalueview;
    private Firebase mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvalueview=(ListView) findViewById(R.id.listview);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://retrievefirebase.firebaseio.com/Contact");

        //mref=new Firebase("https://retrievefirebase.firebaseio.com/Contact");
        FirebaseListAdapter<String> firebaseListAdapter=new FirebaseListAdapter<String>(
                this,String.class,android.R.layout.simple_list_item_1,databaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
            TextView textView=(TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
           //     Log.e("Info",textView+" " );
            }
        };

        mvalueview.setAdapter(firebaseListAdapter);

        mvalueview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {
                    Uri uri = Uri.parse("https://www.facebook.com"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                else if(i==1)
                     {
                        Uri uri = Uri.parse("https://plus.google.com"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
            }
        });

    }
}
