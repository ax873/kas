package maruf.com.kass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {
    MaterialEditText username,email,password;
Button btloginl;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        email = findViewById(R.id.idemail);

        password = findViewById(R.id.idpass);
        btloginl=findViewById(R.id.btlogin);

        btloginl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtpass=password.getText().toString();
                String txtemail=email.getText().toString();
                auth.signInWithEmailAndPassword(txtemail,txtpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){  Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);;
                        startActivity(intent);}
                    }
                });
            }
        });
    }

    public void kgddhs(View view) {

        Intent g =new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(g);
    }
}
