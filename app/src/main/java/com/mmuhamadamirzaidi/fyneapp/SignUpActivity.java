package com.mmuhamadamirzaidi.fyneapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mmuhamadamirzaidi.fyneapp.Model.User;

import dmax.dialog.SpotsDialog;

public class SignUpActivity extends AppCompatActivity {

    private EditText sign_up_fullname, sign_up_phone, sign_up_password;
    private Button button_sign_up, button_account_sign_in;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_up_fullname = findViewById(R.id.sign_up_fullname);
        sign_up_phone = findViewById(R.id.sign_up_phone);
        sign_up_password = findViewById(R.id.sign_up_password);

        button_sign_up = findViewById(R.id.button_sign_up);
        button_account_sign_in = findViewById(R.id.button_account_sign_in);

        // Custom dialog
        dialog = new SpotsDialog.Builder().setContext(SignUpActivity.this).setTheme(R.style.SignUp).build();

        // Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Check if user exist
                        if (dataSnapshot.child(sign_up_phone.getText().toString().trim()).exists()) {

                            dialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Phone number already exist!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            dialog.dismiss();

                            // Get user information
                            User user = new User (sign_up_fullname.getText().toString().trim(), sign_up_password.getText().toString().trim());
                            table_user.child(sign_up_phone.getText().toString().trim()).setValue(user);

                            Toast.makeText(SignUpActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                            SendUserToSignInActivity();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        button_account_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInActivity = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(signInActivity);
            }
        });
    }

    private void SendUserToSignInActivity() {
        Intent mainIntent = new Intent(SignUpActivity.this, SignInActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
