package com.PrakharNagpal.Form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.PrakharNagpal.Form.R.id.nameEditText;

public class AddActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText classEditText;
    EditText dobEditText;
    EditText genderEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText cEditText;
    Button savebutton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FloatingActionButton floatingactionbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameEditText = findViewById(R.id.nameEditText);
        classEditText = findViewById(R.id.classEditText);
        dobEditText = findViewById(R.id.dobEditText);
        genderEditText = findViewById(R.id.genderEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        cEditText = findViewById(R.id.cEditText);
        savebutton = findViewById(R.id.buttonEditActivity);
        String uni_id = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(uni_id);
        floatingactionbutton=findViewById(R.id.fab2MainActivity);
        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent In=new Intent(AddActivity.this,ChangePassword.class);
                startActivity(In);
            }
        });




        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String namedata = nameEditText.getText().toString();
                String classdata = classEditText.getText().toString();
                String dobdata = dobEditText.getText().toString();
                String genderdata = genderEditText.getText().toString();
                String emaildata = emailEditText.getText().toString();
                String passworddata = passwordEditText.getText().toString();
                String cdata = cEditText.getText().toString();
                if(passworddata.equals(cdata)) {
                    reference.child("Name").setValue(namedata);
                    reference.child("Class").setValue(classdata);
                    reference.child("DOB").setValue(dobdata);
                    reference.child("Gender").setValue(genderdata);
                    reference.child("Email").setValue(emaildata);
                    reference.child("Password").setValue(passworddata);
                    reference.child("Confirm Password").setValue(cdata);
                }
                else
                {
                    Toast.makeText(AddActivity.this,"Password don not match",Toast.LENGTH_SHORT);
                }



                finish();


            }

        });

    }
}





