package org.proingesistinfor.ventasapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.general.Util;
import org.proingesistinfor.ventasapp.interfaces.RecyclerViewOnItemClickListener;
import org.proingesistinfor.ventasapp.model.Ad;
import java.util.List;

/**
 * Created by peter on 05-11-17.
 */

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdViewHolder>{

    private List<Ad> ads;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private Context context;

    public AdAdapter(List<Ad> ads, RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.ads = ads;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ad, parent, false);
        this.context = parent.getContext();
        return new AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdViewHolder holder, int position) {
        Ad ad = ads.get(position);
        holder.set(ad);
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    class AdViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageViewAd;
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewPrice;

        public AdViewHolder(View view) {
            super(view);
            imageViewAd = (ImageView) view.findViewById(R.id.imageViewAd);
            textViewName = (TextView) view.findViewById(R.id.textViewName);
            textViewName.setTypeface(Util.fontBold(context));
            textViewDescription = (TextView) view.findViewById(R.id.textViewDescription);
            textViewDescription.setTypeface(Util.fontRegular(context));
            textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
            textViewPrice.setTypeface(Util.fontBold(context));
            view.setOnClickListener(this);
        }

        public void set(Ad ad) {
            imageViewAd.setImageBitmap(Util.base64ToBitmap(ad.getImage()));
            textViewName.setText(ad.getName());
            textViewDescription.setText(ad.getDescription());
            textViewPrice.setText("Bs. "+ ad.getPrice());
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
