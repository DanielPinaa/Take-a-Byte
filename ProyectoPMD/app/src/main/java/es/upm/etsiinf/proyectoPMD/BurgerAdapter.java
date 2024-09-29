package es.upm.etsiinf.proyectoPMD;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import es.upm.etsiinf.proyectoPMD.model.Burger;

public class BurgerAdapter extends BaseAdapter{
        private Activity ctx;
        private List<Burger> data;
        //constructor
        public BurgerAdapter (Activity ctx, List<Burger> data){
            this.ctx=ctx;
            this.data=data;
        }
        public int getCount() {
            return data.size();
        }

        public Object getItem(int i) {
            return data.get(i);
        }

        public long getItemId(int i) {
            return i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) { //pintar la fila i
            if (view == null) {
                LayoutInflater layoutInflater = ctx.getLayoutInflater(); //si la lista está vacía view, me pone el primer elemento
                view = layoutInflater.inflate(R.layout.burguer_item_list_layout, null);//el null es la raíz
            }
            ((ImageView)view.findViewById(R.id.burger_item_image)).setImageBitmap(data.get(i).getImageBmp());
            ((TextView)view.findViewById(R.id.burguer_item_name)).setText(data.get(i).getTitle()+"");

            return view;
        }

}
