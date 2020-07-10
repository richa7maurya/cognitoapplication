package com.example.cognitoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NextPage extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;





    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mDatabase= FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_next_page);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy_MM_dd");
       final   String curdate = dateFormat1.format(new Date());


        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        t1 = (TextView) findViewById(R.id.text2);
        t2 = (TextView) findViewById(R.id.text3);
        t3 = (TextView) findViewById(R.id.text4);
        t4 = (TextView) findViewById(R.id.text5);
        t5 = (TextView) findViewById(R.id.text6);
        t6 = (TextView) findViewById(R.id.text7);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //create child in root object and assign value to child

                String c6= getCurrentTimestamp();
                mDatabase.child("cognitoapplication").child("linkedin").child(curdate).child(c6).setValue(1);


                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://in.linkedin.com/"));
                startActivity(intent);

                 }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //create child in root object and assign value to child

                String c6= getCurrentTimestamp();
                mDatabase.child("cognitoapplication").child("canva").child(curdate).child(c6).setValue(1);


                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.canva.com/login"));
                startActivity(intent);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //create child in root object and assign value to child
                String c6= getCurrentTimestamp();
                mDatabase.child("cognitoapplication").child("github").child(curdate).child(c6).setValue(1);

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/"));
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //create child in root object and assign value to child
                String c6= getCurrentTimestamp();
                mDatabase.child("cognitoapplication").child("coursera").child(curdate).child(c6).setValue(1);

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.coursera.com/"));
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //create child in root object and assign value to child
                String c6= getCurrentTimestamp();
                mDatabase.child("cognitoapplication").child("twitter").child(curdate).child(c6).setValue(1);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/home"));
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //create child in root object and assign value to child
                String c6= getCurrentTimestamp();
                mDatabase.child("cognitoapplication").child("yahoo").child(curdate).child(c6).setValue(1);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://in.yahoo.com/"));
                startActivity(intent);
            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //create child in root object and assign value to child
                startActivity(new Intent(NextPage.this,Graphpage.class));

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            ArrayList<BarEntry> yVal=new ArrayList<>();





            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot ds:dataSnapshot.getChildren()) {

                    for (DataSnapshot ds1 : ds.getChildren()) {

                        String val;
                        try {
                            val = ds1.getKey().toString();
                            // System.out.println("lol");
                            switch (val) {
                                case "linkedin":
                                    t1.setText(val);
                                    break;
                                case "canva":
                                    t2.setText(val);
                                    break;
                                case "github":
                                    t3.setText(val);
                                    break;
                                case "coursera":
                                    t4.setText(val);
                                    break;
                                case "twitter":
                                    t5.setText(val);
                                    break;
                                case "yahoo":
                                    t6.setText(val);
                                    break;


                            }

                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private String getCurrentTimestamp() {


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
            String currentDateTime = dateFormat.format(new Date());


            return currentDateTime;

        } catch (Exception e) {
            e.printStackTrace();
            return null;


        }

    }
}