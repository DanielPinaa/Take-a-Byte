package es.upm.etsiinf.proyectoPMD;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import es.upm.etsiinf.proyectoPMD.model.Comida;

public class ComidaAdapter extends BaseAdapter {
    private Activity ctx;
    private List<Comida> data;
    //constructor
    public ComidaAdapter(Activity ctx, List<Comida> data){
        this.ctx=ctx;
        this.data=data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = ctx.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.mirar_carta_item_list_layout,null);//el null es la raíz
        }
        ((ImageView)view.findViewById(R.id.burger_item_image)).setImageBitmap(data.get(i).getImageBmp());
        ((TextView)view.findViewById(R.id.burguer_item_name)).setText(data.get(i).getLocation());

        return view;
    }
}

