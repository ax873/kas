package maruf.com.kass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

import maruf.com.kass.coba.setsaldo;

public class MainSaldo extends AppCompatActivity {

    EditText edisisaldo1;
    //    DatabaseReference myRef;
    TextView tunjuk;
    private TextView detailHarga;
//int hasil;
    DatabaseReference databasw1;
    Button edsimpansaldo1,edubahsaldo1,edhapussaldo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_saldo);
//        detailHarga = (TextView) findViewById(R.id.textw);
//
//        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
//
//
//
//        Locale loca = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);


        tunjuk = (TextView) findViewById(R.id.saldokamu);
        edisisaldo1 = (EditText) findViewById(R.id.idsaldo1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");
        edsimpansaldo1 = (Button) findViewById(R.id.btsimpansaldo1);
        edubahsaldo1 = (Button) findViewById(R.id.btubahsaldo1);
        edhapussaldo1 = (Button) findViewById(R.id.bthapussaldo1);

        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
                tunjuk.setText(mahasiswa.getSaldo());

                int hasil;
                detailHarga = (TextView) findViewById(R.id.textw);
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil = Integer.parseInt(tunjuk.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                detailHarga.setText(formatRupiah.format((double) hasil));
                tunjuk.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        edsimpansaldo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idduuu = edisisaldo1.getText().toString().trim();
                simpansaldo(new setsaldo(idduuu.toLowerCase()));
            }
        });
    }





    private void simpansaldo(setsaldo setsaldo) {
        databasw1.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                edisisaldo1.setText("");

                Toast.makeText(getApplicationContext(),"saldo tersimpan",Toast.LENGTH_LONG).show();
//tampil();


            }
        });

    }


}
