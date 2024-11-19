package com.example.seminar_4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private List<ImaginiSarpe> imagini = null;
    private Context ctx;
    private int resurseLayout;

    public ImagineAdapter(Context ctx, int resurseLayout, List<ImaginiSarpe> imagini ) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resurseLayout = resurseLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int i) {
        return imagini.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
