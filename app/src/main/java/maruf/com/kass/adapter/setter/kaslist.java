package maruf.com.kass.adapter.setter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import maruf.com.kass.R;
import maruf.com.kass.setterr.setkas;

public class kaslist extends ArrayAdapter<setkas> {

    private Activity context;
    private List<setkas> setlist;


    public kaslist(@NonNull Activity context, List<setkas> setlist) {
        super(context, R.layout.list_layout,setlist);
        this.context=context;
        this.setlist=setlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View Listviewitem=inflater.inflate(R.layout.list_layout,null,true);
        TextView textnama=(TextView)Listviewitem.findViewById(R.id.tv_nama);
        TextView texyjumlah=(TextView)Listviewitem.findViewById(R.id.tv_jumlah);
        setkas kas=setlist.get(position);
        textnama.setText(kas.getNama());
        texyjumlah.setText(kas.getJumlah());

        return Listviewitem;
    }
}
