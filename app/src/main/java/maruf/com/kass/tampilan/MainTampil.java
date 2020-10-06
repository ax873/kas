package maruf.com.kass.tampilan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import maruf.com.kass.R;
import maruf.com.kass.adapter.setter.kaslist;
import maruf.com.kass.coba.setsaldo;
import maruf.com.kass.setterr.setkas;

public class MainTampil extends AppCompatActivity {
    private ListView listviewkas;
    public  static  final String KS_NAMA="nama";
    public  static  final String KS_ID="id";
    public  static  final String KS_RANTING="ranting";
    public  static  final String KS_JUMLAH="jumlah";
    public  static  final String KS_DESK="desk";
    TextView txtapayah;
    DatabaseReference databasw1;
    List<setkas> kaslist;
    DatabaseReference databasekas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tampil);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
        kaslist = new ArrayList<>();
        listviewkas = (ListView) findViewById(R.id.lisviewkas);
        txtapayah=(TextView) findViewById(R.id.idapayah);

        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                setsaldo value = dataSnapshot.getValue(setsaldo.class);
                txtapayah.setText(value.getSaldo());

                int hasil;
             TextView   tuunjuk = (TextView) findViewById(R.id.idapayah1);
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil= Integer.parseInt(txtapayah.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                tuunjuk.setText(formatRupiah.format((double)hasil));
                txtapayah.setVisibility(View.INVISIBLE);


            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        listviewkas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setkas kas=kaslist.get(position);
                Intent a =new Intent(getApplicationContext(),MainUbah.class);
                a.putExtra(KS_ID,kas.getIdkas());
                a.putExtra(KS_NAMA,kas.getNama());
                a.putExtra(KS_RANTING,kas.getRanting());
                a.putExtra(KS_JUMLAH,kas.getJumlah());
                a.putExtra(KS_DESK,kas.getDesk());
                startActivity(a);


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        databasekas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslist.clear();

                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    setkas kas=setnapasopt.getValue(setkas.class);
                    kaslist.add(kas);
                }
                maruf.com.kass.adapter.setter.kaslist adapter=new kaslist(MainTampil.this,kaslist);
                listviewkas.setAdapter(adapter);
//                Collections.reverse(kaslist);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
