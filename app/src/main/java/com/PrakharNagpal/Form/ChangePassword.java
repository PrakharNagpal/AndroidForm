package com.PrakharNagpal.Form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {
    EditText NewPass;
    EditText CPass;
    Button updateb;
    private FirebaseDatabase firebasedatabase;
    private DatabaseReference REFERENCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        NewPass=findViewById(R.id.UpdatePassword);
        CPass=findViewById(R.id.CONFIRM);
        updateb=findViewById(R.id.updatebutton);
        firebasedatabase=FirebaseDatabase.getInstance();
        String uni_id = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        REFERENCE=firebasedatabase.getReference(uni_id);
        updateb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Password=NewPass.getText().toString();
                String CPassword=CPass.getText().toString();
                if (Password.equals(CPassword)) {
                    REFERENCE.child("UpdatePassword").setValue(NewPass);
                    REFERENCE.child("Confirm Password").setValue(CPass);
                }
                else{
                    Toast.makeText(ChangePassword.this,"Password do not match",Toast.LENGTH_SHORT);
                }
                finish();



            }
        });
    }
}
