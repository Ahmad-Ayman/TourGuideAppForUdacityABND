package com.freelance.ahmed.tourguideapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freelance.ahmed.tourguideapp.Activities.DetailsActivity;
import com.freelance.ahmed.tourguideapp.Interfaces.ItemClickListener;
import com.freelance.ahmed.tourguideapp.Models.Places;
import com.freelance.ahmed.tourguideapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 2/18/2018.
 */

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourAdapterViewHolder> {
    private Context mContext;
    private String name;
    private String pic;
    private String bio, longi, lati;
    private final static String BASE_POSTER_URL = "https://ahmedayman1708.000webhostapp.com/TourGuideApp/pics/phar/";
    private final static String NAME_KEY = "NAME_KEY";
    private final static String PIC_KEY = "PIC_KEY";
    private final static String BIO_KEY = "BIO_KEY";
    private final static String LONGI_KEY = "LONGI_KEY";
    private final static String LATI_KEY = "LATI_KEY";

    private List<Places.PlacesData> places;

    public TourAdapter(Context context, List<Places.PlacesData> rList) {
        mContext = context;
        places = rList;
    }

    @Override
    public TourAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, null);
        TourAdapter.TourAdapterViewHolder rcv = new TourAdapter.TourAdapterViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final TourAdapterViewHolder holder, int position) {
        name = places.get(position).getmName();
        pic = places.get(position).getmPic();

        holder.mPlaceName.setText(name);
        String urlofPoster = BASE_POSTER_URL + pic;
        Picasso.with(mContext).load(urlofPoster).fit()
                .placeholder(R.drawable.noimage).centerCrop()
                .error(R.drawable.error).centerCrop()
                .into(holder.mPlacePic);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                bio = places.get(pos).getmBio();
                longi = places.get(pos).getmLongi();
                lati = places.get(pos).getmLati();
                name = places.get(pos).getmName();
                pic = places.get(pos).getmPic();
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.putExtra(NAME_KEY, name);
                i.putExtra(PIC_KEY, pic);
                i.putExtra(BIO_KEY, bio);
                i.putExtra(LONGI_KEY, longi);
                i.putExtra(LATI_KEY, lati);
                //open activity
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null == places) return 0;
        return places.size();
    }

    public void setPlacesData(ArrayList<Places.PlacesData> placesData) {

        places = placesData;
        notifyDataSetChanged();
    }

    //////////////////////////////////////// View Holder Class //////////////////////////
    public class TourAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mPlacePic;
        public TextView mPlaceName;
        private ItemClickListener itemClickListener;

        public TourAdapterViewHolder(View view) {
            super(view);
            mPlacePic = (ImageView) view.findViewById(R.id.iv_pic);
            mPlaceName = (TextView) view.findViewById(R.id.tv_name);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }
    //////////////////////////////////////////////////

}
