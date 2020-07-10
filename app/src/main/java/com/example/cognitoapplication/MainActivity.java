package com.example.cognitoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText pass1;
    private TextView att1;
    private Button log1;
    private int count;

    private CheckBox showpassword;
    // private ProgressDialog progress;
    private FirebaseAuth firebaseAuth;
    //DatabaseReference rediff;
    //byte[] array=new byte[7];
    Random rand=new Random();
    private void validate(String uname,String upass)
    {

        firebaseAuth.signInWithEmailAndPassword(uname,upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {   double r=rand.nextDouble();

                    Toast.makeText(MainActivity.this,"login successful!!!!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,NextPage.class));
                    String passs=pass1.getText().toString().trim();
                    passs=Double.toString(r);

                }
                else
                {
                    Toast.makeText(MainActivity.this,"login failed",Toast.LENGTH_SHORT).show();
                    count--;
                    att1.setText("no of attempts remaining: "+count);
                    if(count==0)
                    {

                        log1.setEnabled(false);
                    }
                }
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et);
        pass1 = (EditText) findViewById(R.id.pass);
        att1 = (TextView) findViewById(R.id.att);
        log1 = (Button) findViewById(R.id.log);

        showpassword=findViewById(R.id.Chkbox);


        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    pass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else
                {
                    pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        count = 5;


        log1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String et11 = et1.getText().toString().trim();
                String  pass11= pass1.getText().toString().trim();
                if(!(et11.isEmpty() && pass11.isEmpty()))
                validate(et11,pass11);
                else
                    Toast.makeText(MainActivity.this, "Please enter the details!", Toast.LENGTH_SHORT).show();


            }
        });

    }


}
