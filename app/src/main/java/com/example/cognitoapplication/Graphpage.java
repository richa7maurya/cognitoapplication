package com.example.cognitoapplication;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Graphpage  extends AppCompatActivity
{

    FirebaseDatabase ptbase ;
    DatabaseReference ptref;


    private BarChart mChart;
    private Button out;
    protected void onCreate(Bundle savedInstanceState)
    {

        ptbase =FirebaseDatabase.getInstance();
        ptref=ptbase.getReference("cognitoapplication");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphpage);



        out=(Button)findViewById(R.id.out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Graphpage.this,Register.class));
            }
        });

        mChart=(BarChart)findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);


        mChart.setFitBars(true);
        System.out.println("VALUELL:");

        ptref.addValueEventListener(new ValueEventListener() {
            ArrayList<BarEntry> yVal=new ArrayList<>();

            SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
            String currentDateTime = dateFormat.format(new Date());

            @Override

            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                final ArrayList<String> xLabels = new ArrayList<>();

                  System.out.println("Suuuuu" + Snapshot.getKey());
                int i = 0;
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy_MM_dd");
                String a = dateFormat1.format(new Date());
                for (DataSnapshot dataSnapshot : Snapshot.getChildren()) {
                    int val = 0;

                    System.out.println(dataSnapshot.getKey());
                    xLabels.add(dataSnapshot.getKey());
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    //System.out.println("hell"+ds.getKey());


                    if (ds.getKey().equals(a)) {
                        for (DataSnapshot data : ds.getChildren()) {

                            int j = 0, k = 0;
                            System.out.println("got");
                            j = Integer.parseInt(currentDateTime);
                            System.out.println("Value of j " + j);
                            try {
                                k = Integer.parseInt(data.getKey().toString());
                                System.out.println("Hello " + k);
                            } catch (NumberFormatException nfe) {
                                System.out.println("Could not parse " + nfe);
                            }
                            if (j - k <= 10000) {
                                val++;
                            }

                        }
                    }

                }
                    yVal.add(new BarEntry(i++, val));
                            }

                XAxis xAxis = mChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {

                        return xLabels.get((int) value);

                    }

                });
                BarDataSet set = new BarDataSet(yVal, "Website usage graph");
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


                set.setColors(ColorTemplate.MATERIAL_COLORS);
                set.setDrawValues(true);

                BarData data = new BarData(set);
                mChart.setData(data);
                mChart.invalidate();
                mChart.animateY(200);



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }





}