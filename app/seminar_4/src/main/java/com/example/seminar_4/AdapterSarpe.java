package com.example.seminar_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class AdapterSarpe  extends BaseAdapter {
    private List<Sarpe> serpi = null;
    private Context ctx;
    private int resurseLayout;

    public AdapterSarpe(List<Sarpe> serpi, Context ctx, int resurseLayout) {
        this.serpi = serpi;
        this.ctx = ctx;
        this.resurseLayout = resurseLayout;
    }

    @Override
    public int getCount() {
        return serpi.size();
    }

    @Override
    public Object getItem(int i) {
        return serpi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resurseLayout,viewGroup, false );

        TextView specTV = v.findViewById(R.id.specieTV);
        TextView lungTV = v.findViewById(R.id.lungime_medieTV);
        TextView culTV = v.findViewById(R.id.culoareTV);
        CheckBox venCB = v.findViewById(R.id.veninosCB);

        Sarpe s=(Sarpe) getItem(i);

        specTV.setText(s.getSpecie());
        lungTV.setText(s.getLungime_medie());
        culTV.setText(s.getCuloare());
        venCB.setChecked(s.isVeninos());



        return v;
    }


}
