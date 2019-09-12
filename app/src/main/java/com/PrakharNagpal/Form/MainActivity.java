package com.PrakharNagpal.Form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView nameTextView;
    TextView classTextView;
    TextView dobTextView;
    TextView genderTextView;
    TextView emailTextView;
    TextView passwordTextView;
    TextView cpasswordTextView;
    FloatingActionButton floatingActionButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTextView = findViewById(R.id.NAME);
        classTextView = findViewById(R.id.CLASS);
        dobTextView = findViewById(R.id.DOB);
        genderTextView = findViewById(R.id.GENDER);
        emailTextView = findViewById(R.id.EMAILID);
        passwordTextView = findViewById(R.id.PASSWORD);
        cpasswordTextView = findViewById(R.id.CONFIRM);
        floatingActionButton = findViewById(R.id.fabMainActivity);
//        fetchData();
        fetchFirebaseData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public void fetchFirebaseData() {
        String uni_id = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(uni_id);// find uid of the device
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    nameTextView.setText(dataSnapshot.child("Name").getValue().toString());
                    classTextView.setText((dataSnapshot.child("Class").getValue().toString()));
                    dobTextView.setText((dataSnapshot.child("DOB").getValue().toString()));
                    genderTextView.setText((dataSnapshot.child("Gender").getValue().toString()));
                    emailTextView.setText(dataSnapshot.child("Email").getValue().toString());
                    passwordTextView.setText(dataSnapshot.child("Password").getValue().toString());
                    cpasswordTextView.setText(dataSnapshot.child("Confirm Password").getValue().toString());
                } else {
                    Toast.makeText(MainActivity.this, "NO DATA EXISTS", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void fetchData() {
        sharedPreferences = getSharedPreferences("DATABASE", MODE_PRIVATE);
        nameTextView.setText(sharedPreferences.getString("NAME", ""));
        classTextView.setText(sharedPreferences.getString("CLASS", ""));
        dobTextView.setText(sharedPreferences.getString("DOB", ""));
        genderTextView.setText(sharedPreferences.getString("GENDER", ""));
        emailTextView.setText(sharedPreferences.getString("Email", ""));
        passwordTextView.setText(sharedPreferences.getString("Password", ""));
        cpasswordTextView.setText(sharedPreferences.getString("ConfirmPassword", ""));
}



    @Override
    protected void onResume() {
        super.onResume();
//        fetchData();
        fetchFirebaseData();
    }



}
