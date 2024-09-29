package es.upm.etsiinf.proyectoPMD;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ReviewsAdapter extends BaseAdapter {

    private List<Review> reviews;
    private LayoutInflater inflater;

    public ReviewsAdapter(Context context, List<Review> reviews) {
        this.reviews = reviews;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.review_item, null);
            holder = new ViewHolder();
            holder.tituloTextView = convertView.findViewById(R.id.tituloTextView);
            holder.descripcionTextView = convertView.findViewById(R.id.descripcionTextView);
            holder.notaTextView = convertView.findViewById(R.id.notaTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Review review = (Review) getItem(position);

        holder.tituloTextView.setText(review.getTitulo());
        holder.descripcionTextView.setText(review.getDescripcion());
        holder.notaTextView.setText(String.valueOf(review.getValoracion()));

        return convertView;
    }

    static class ViewHolder {
        TextView tituloTextView;
        TextView descripcionTextView;
        TextView notaTextView;
    }
}


