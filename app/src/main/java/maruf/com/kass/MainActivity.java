package maruf.com.kass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import maruf.com.kass.coba.coba;
import maruf.com.kass.coba.setsaldo;
import maruf.com.kass.setterr.setkas;
import maruf.com.kass.tampilan.MainTampil;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databasekas;
    EditText edid;
    EditText  ednama;
    EditText   edranting;
    EditText   edjumlah;
    EditText   eddesk;
    TextView isisaldo;
    TextView tampillsaldo;
    private String Sid;

    Double hasil,v1,v2;
    int ang1,ang2,hasl;
    Button EDsIMPAN1;
    TextView idcurenc;
    DatabaseReference databasw1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isisaldo = (TextView) findViewById(R.id.Idid);
        tampillsaldo = (TextView) findViewById(R.id.idtampilsaldo);
        edid = (EditText) findViewById(R.id.txtidkas);
        ednama = (EditText) findViewById(R.id.txtnama);
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
        edranting= (EditText) findViewById(R.id.txtranting);
        edjumlah = (EditText) findViewById(R.id.txtjumlah);
        eddesk = (EditText) findViewById(R.id.txtdesk);
        Button edccsimpan = (Button) findViewById(R.id.btsimpan);
        Button bthapus = (Button) findViewById(R.id.bthapus);
        Sid=getIntent().getStringExtra("id");
        idcurenc=(TextView)findViewById(R.id.idcurency);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");
databasw1.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
        isisaldo.setText(mahasiswa.getSaldo());
        isisaldo.setVisibility(View.INVISIBLE);
 tampillsaldo.setVisibility(View.INVISIBLE);
      int hs;
      NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
      hs = Integer.parseInt(isisaldo.getText().toString());
      Locale loca = new Locale("in", "ID");
   NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
  idcurenc.setText(formatRupiah.format((double) hs));

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

        edccsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
                String idd=edid.getText().toString().trim();
                String nama=ednama.getText().toString().trim();
                String ranting=edranting.getText().toString().trim();
                String jumlah=edjumlah.getText().toString().trim();
                String desk=eddesk.getText().toString().trim();
                String idduuu = tampillsaldo.getText().toString().trim();
                simpansaldo(new setsaldo(idduuu.toLowerCase()));
                submitutuser(new setkas(
                        idd.toLowerCase(),
                        nama.toLowerCase(),
                        ranting.toLowerCase(),
                        jumlah.toLowerCase(),
                        desk.toLowerCase()));
            }

        });


    }

    private void convert() {

         ang1 = Integer.parseInt(isisaldo.getText().toString());
        ang2 = Integer.parseInt(edjumlah.getText().toString());
        hasl = ang1 + ang2;
        tampillsaldo.setText(""+hasl);

//
//        v1 = Double.parseDouble(isisaldo.getText().toString());
//        v2 = Double.parseDouble(edjumlah.getText().toString());
//        hasil= v1+v2;
//        tampillsaldo.setText(Double.toString(hasil));
 }

    private void submitutuser(setkas setkas) {
        String chid=edid.getText().toString().trim();
        databasekas
                .child(chid)
                .setValue(setkas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        edid.setText("");
                        ednama.setText("");
                        edranting.setText("");
                        edjumlah.setText("");
                        eddesk.setText("");


                    }
                });

    }







    private void simpansaldo(setsaldo setsaldo) {
        databasw1.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                Toast.makeText(getApplicationContext(),"saldo tersimpan",Toast.LENGTH_LONG).show();
//tampil();


            }
        });}

    public void aaaa(View view) {

        Intent a =new Intent(MainActivity.this, MainTampil.class);
        startActivity(a);
    }

    public void hhhh(View view) {
        Intent g =new Intent(MainActivity.this, MainSaldo.class);
        startActivity(g);
    }
//
//
//    private void hapusii(String Sid) {
//        databasekas
//                .child(Sid)
//                .removeValue()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        edid.setText("");
//                        ednama.setText("");
//                        edranting.setText("");
//                        edjumlah.setText("");
//                        eddesk.setText("");
//
//
//                    }
//                });
//    }
//

}
