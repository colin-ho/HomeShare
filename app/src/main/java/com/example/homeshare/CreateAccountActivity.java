package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    private Button backToLoginButton;
    private Button createAccountButton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    String userID;
    EditText mFullName, mEmail, mPassword, mAbout,mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        backToLoginButton = findViewById(R.id.backToLoginButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        mFullName  = findViewById(R.id.createAccountFullName);
        mPhone = findViewById(R.id.createAccountPhone);
        mEmail = findViewById(R.id.createAccountEmailAddress);
        mPassword = findViewById(R.id.createAccountPassword);
        mAbout = findViewById(R.id.createAccountAbout);

        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname =  String.valueOf(((EditText) findViewById(R.id.createAccountFullName)).getText());
                String about =  String.valueOf(((EditText) findViewById(R.id.createAccountAbout)).getText());
                String email = String.valueOf(((EditText) findViewById(R.id.createAccountEmailAddress)).getText());
                String password = String.valueOf(((EditText) findViewById(R.id.createAccountPassword)).getText());
                String confirmPassword = String.valueOf(((EditText) findViewById(R.id.createAccountConfirmPassword)).getText());
                String phone = String.valueOf(((EditText) findViewById(R.id.createAccountPhone)).getText());
                createAccount(email, password, confirmPassword,about,fullname,phone);
            }
        });
    }

    public void createAccount(String email, String password, String confirmPassword,String about,String fullname,String phone) {
        if (email == null || email.equals("") || password == null || password.equals("")) {
            Toast.makeText(CreateAccountActivity.this, "Email / Password Fields Cannot Be Empty",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(CreateAccountActivity.this, "Passwords must match",
                    Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG, "createUserWithEmail:success");
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = mStore.collection("UsersTest").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("email",email);
                            user.put("fName",fullname);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("Tag","onSuccess: User profile is created ");
                                }
                            });
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}