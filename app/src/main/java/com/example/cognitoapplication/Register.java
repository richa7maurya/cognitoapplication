package com.example.cognitoapplication;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {

    private TextView log_link;
    private EditText reg_email, reg_pass;
    private Button bt_reg;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase ptbase;
    DatabaseReference ptref;
    private CheckBox showpassword1;





    protected void onCreate(Bundle savedInstanceState) {

        ptbase =FirebaseDatabase.getInstance();
        ptref=ptbase.getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        log_link = (TextView) findViewById(R.id.log);

        bt_reg = (Button) findViewById(R.id.bt_reg);
        //newuser = (Button) findViewById(R.id.bt_newuser);
        reg_email = (EditText) findViewById(R.id.mail);
        reg_pass = (EditText) findViewById(R.id.ed_regpass);

        showpassword1=findViewById(R.id.Chkbox1);


        showpassword1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    reg_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else
                {
                    reg_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        bt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = reg_email.getText().toString().trim();
                String user_pass = reg_pass.getText().toString().trim();

                if (user_email.isEmpty()) {
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (user_pass.isEmpty()) {
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (user_email.isEmpty() && user_pass.isEmpty()) {
                    Toast.makeText(Register.this, "Enter details", Toast.LENGTH_SHORT).show();
                } else if (!(user_email.isEmpty() && user_pass.isEmpty())) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(user_email, user_pass).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, MainActivity.class));
                            } else {
                                Toast.makeText(Register.this, "Registration Not  Successful!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {
                    Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    log_link.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Register.this,MainActivity.class);
            startActivity(intent);
        }
    });
    }
}