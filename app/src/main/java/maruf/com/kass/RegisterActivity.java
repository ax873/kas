package maruf.com.kass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username,email,password;
    Button btregister;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);





        username = findViewById(R.id.idusername);
        email = findViewById(R.id.email);
        password = findViewById(R.id.idpass);
        btregister = findViewById(R.id.btnregister);
        auth=FirebaseAuth.getInstance();
        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtusername=username.getText().toString();
                String txtpass=password.getText().toString();
                String txtemail=email.getText().toString();
                register(txtusername,txtemail,txtpass);
            }
        });

    }

    private void register(final String username, String email, final String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser=auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid=firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            HashMap<String, String> hashMap =new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("password",password);

                            hashMap.put("imageURL","default");
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent a=new Intent(RegisterActivity.this,MainActivity.class);

                                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|a.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(a);
                                        finish();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"gagal",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }}






//
//    private Button btSignUp;
//    private EditText etEmail;
//    private EditText etPassword;
//
//    private FirebaseAuth fAuth;
//    private FirebaseAuth.AuthStateListener fStateListener;
//
//    private static final String TAG = RegisterActivity.class.getSimpleName();
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        /**
//         * Inisialisasi Firebase Auth
//         */
//        fAuth = FirebaseAuth.getInstance();
//
//        /**
//         * Cek apakah ada user yang sudah login
//         */
//        fStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User sedang login
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User sedang logout
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//            }
//        };
//
//        btSignUp = (Button) findViewById(R.id.bt_signup);
//        etEmail = (EditText) findViewById(R.id.et_email);
//        etPassword = (EditText) findViewById(R.id.et_password);
//
//        btSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /**
//                 * Lempar email dan password ketika tombol signup diklik
//                 */
//                signUp(etEmail.getText().toString(), etPassword.getText().toString());
//            }
//        });
//
//    }
//
//    private void signUp(final String email, String password){
//
//        fAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//                        /**
//                         * Jika sign in gagal, tampilkan pesan ke user. Jika sign in sukses
//                         * maka auth state listener akan dipanggil dan logic untuk menghandle
//                         * signed in user bisa dihandle di listener.
//                         */
//                        if (!task.isSuccessful()) {
//                            task.getException().printStackTrace();
//                            Toast.makeText(RegisterActivity.this, "Proses Pendaftaran Gagal",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "Proses Pendaftaran Berhasil\n" +
//                                            "Email "+email,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // rest of code
//                    }
//                });
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        fAuth.addAuthStateListener(fStateListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (fStateListener != null) {
//            fAuth.removeAuthStateListener(fStateListener);
//        }
//    }
//}
//
