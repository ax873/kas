package maruf.com.kass.tampilan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import maruf.com.kass.R;
import maruf.com.kass.coba.setsaldo;
import maruf.com.kass.setterr.setkas;

public class MainUbah extends AppCompatActivity {
    DatabaseReference databasekas;
    EditText edid1;
    EditText  ednama1;
    EditText   edranting1;
    EditText   edjumlah1;
    EditText   eddesk1;
    private String Sid;
    DatabaseReference databasw1;
    TextView iiidd;
    Button edupdate;
    TextView ppid;
    int ang1,ang2,hasl,ang3,hasbantu,has0l, h1asbantu;
TextView uwu;
    Button EDsIMPAN1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ubah);
        edid1 = (EditText) findViewById(R.id.txtidkas1);
        ednama1 = (EditText) findViewById(R.id.txtnama1);
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
        edranting1 = (EditText) findViewById(R.id.txtranting1);
        edjumlah1 = (EditText) findViewById(R.id.txtjumlah1);
        eddesk1 = (EditText) findViewById(R.id.txtdesk1);
         edupdate = (Button) findViewById(R.id.btsimpan1);
        Button bthapus = (Button) findViewById(R.id.bthapus);
        Sid=getIntent().getStringExtra("id");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");
        Intent a = getIntent();
        iiidd= (TextView)findViewById(R.id.Idid1ubah);
        ppid= (TextView)findViewById(R.id.Iid1ubah);
        uwu= (TextView)findViewById(R.id.uwu);
        String idu = a.getStringExtra(MainTampil.KS_ID);
        String nama = a.getStringExtra(MainTampil.KS_NAMA);

        String rANTING = a.getStringExtra(MainTampil.KS_RANTING);
        String JUMLAH = a.getStringExtra(MainTampil.KS_JUMLAH);
        String DESK = a.getStringExtra(MainTampil.KS_DESK);

        edid1.setText(idu);
        ednama1.setText(nama);
        edranting1.setText(rANTING);
        edjumlah1.setText(JUMLAH);
        ppid.setText(JUMLAH);

        eddesk1.setText(DESK);
databasw1.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
        iiidd.setText(mahasiswa.getSaldo());
       // uwu.setText(mahasiswa.getSaldo());
     //   iiidd.setVisibility(View.INVISIBLE);
        //iiidd.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
        bthapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ang1 = Integer.parseInt(iiidd.getText().toString());
                ang2 = Integer.parseInt(edjumlah1.getText().toString());
                hasl = ang1 - ang2;
                ppid.setText(""+hasl);

            hapusii(Sid);
                String idduuu = ppid.getText().toString().trim();
                simpansaldo(new setsaldo(idduuu.toLowerCase()));
            }
        });


        edupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idd=edid1.getText().toString().trim();
                String nama=ednama1.getText().toString().trim();
                String ranting=edranting1.getText().toString().trim();
                String jumlah=edjumlah1.getText().toString().trim();
                String desk=eddesk1.getText().toString().trim();


                ang1 = Integer.parseInt(iiidd.getText().toString());
                ang2 = Integer.parseInt(ppid.getText().toString());
               ang3 = Integer.parseInt(edjumlah1.getText().toString());


                if (ang2>ang3){

                    ang1 = Integer.parseInt(iiidd.getText().toString());
                    ang2 = Integer.parseInt(ppid.getText().toString());
                    hasl = ang1 - ang2;
                    uwu.setText(""+hasl);

                    hapusii(Sid);
                    has0l=hasl+ang3;

                    ppid.setText(""+has0l);

                   String idduuu = ppid.getText().toString().trim();

                   simpansaldo(new setsaldo(idduuu.toLowerCase()));

                    Toast.makeText(getApplicationContext(),"nduwur",Toast.LENGTH_SHORT).show();
                } else if(ang2<ang3){
                    hasbantu=ang3-ang2;
                        hasl = ang1+hasbantu ;
                    ppid.setText(""+hasl);
                    String idduuu = ppid.getText().toString().trim();
                    simpansaldo(new setsaldo(idduuu.toLowerCase()));
                    Toast.makeText(getApplicationContext(),"ngisor banfgetttttttttttt",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"njirrrr",Toast.LENGTH_LONG).show();
                }




                sutuser(new setkas(
                        idd.toLowerCase(),
                        nama.toLowerCase(),
                        ranting.toLowerCase(),
                        jumlah.toLowerCase(),
                        desk.toLowerCase()),Sid);
            }

        });


    }

    private void convert() {

        ang1 = Integer.parseInt(iiidd.getText().toString());
        ang2 = Integer.parseInt(edjumlah1.getText().toString());
        hasl = ang1 + ang2;
        uwu.setText(""+hasl);

    }

    private void simpansaldo(setsaldo setsaldo) {
        databasw1.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                ppid.setText("");
            }
        });
    }

    private void sutuser(setkas setkas, String id) {
        databasekas
                .child(id)
                .setValue(setkas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        edid1.setText("");
                        ednama1.setText("");
                        edranting1.setText("");
                        edjumlah1.setText("");
                        eddesk1.setText("");


                    }
                });
    }

    private void hapusii(String sid) {

        databasekas
                .child(sid)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                    }
                });

    }

}
