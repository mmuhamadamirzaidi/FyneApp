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
import com.mmuhamadamirzaidi.fyneapp.Common.Common;
import com.mmuhamadamirzaidi.fyneapp.Model.User;

import dmax.dialog.SpotsDialog;

public class SignInActivity extends AppCompatActivity {

    private EditText sign_in_phone, sign_in_password;
    private Button button_sign_in, button_account_sign_up;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sign_in_phone = findViewById(R.id.sign_in_phone);
        sign_in_password = findViewById(R.id.sign_in_password);

        button_sign_in = findViewById(R.id.button_sign_in);
        button_account_sign_up = findViewById(R.id.button_account_sign_up);

        // Custom dialog
        dialog = new SpotsDialog.Builder().setContext(SignInActivity.this).setTheme(R.style.SignIn).build();

        // Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Check if user exist
                        if (dataSnapshot.child(sign_in_phone.getText().toString().trim()).exists()) {

                            dialog.dismiss();
                            // Get user information
                            User user = dataSnapshot.child(sign_in_phone.getText().toString().trim()).getValue(User.class);

                            if (user.getPassword().equals(sign_in_password.getText().toString().trim())) {
                                Toast.makeText(SignInActivity.this, "Sign in successful!", Toast.LENGTH_SHORT).show();
                                Common.currentUser = user;
                                SendUserToMainActivity();
                            } else {
                                Toast.makeText(SignInActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            dialog.dismiss();
                            Toast.makeText(SignInActivity.this, "User don't exist in system!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        button_account_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpActivity = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signUpActivity);
            }
        });
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(SignInActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
