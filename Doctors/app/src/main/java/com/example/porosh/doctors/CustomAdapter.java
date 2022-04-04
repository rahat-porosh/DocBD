package com.example.porosh.doctors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

class CustomAdapter extends BaseAdapter {
    Context context;
    String[] strings;
    private LayoutInflater inflater;
    CustomAdapter(Context context,String[] strings){
        this.context=context;
        this.strings=strings;
    }
    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.custom_view,parent,false);
        }
        /*ImageView imageView=convertView.findViewById(R.id.img);
        imageView.setImageResource(img);*/
        TextView textView=convertView.findViewById(R.id.txt1);
        textView.setText(strings[position]);
        return convertView;
    }
}