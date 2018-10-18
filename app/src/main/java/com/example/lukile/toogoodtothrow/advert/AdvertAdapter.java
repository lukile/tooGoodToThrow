package com.example.lukile.toogoodtothrow.advert;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lukile.toogoodtothrow.OnBottomReachedListener;
import com.example.lukile.toogoodtothrow.R;
import com.example.lukile.toogoodtothrow.category.CategoryActivity;
import com.example.lukile.toogoodtothrow.category.CategoryAdapter;
import com.example.lukile.toogoodtothrow.model.Advert;
import com.example.lukile.toogoodtothrow.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdvertAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private int totalItemCount, lastVisibleItem;

    private int visibleThreshold = 5;

    private Activity activity;

    private OnBottomReachedListener onBottomReachedListener;
    private boolean isLoading;
    private boolean shouldListenToOnBottomReached = true;

    private List<Advert> data = new ArrayList<>();
    private AdvertAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Advert item);
    }


    public AdvertAdapter(List<Advert> data, AdvertAdapter.OnItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }


    public AdvertAdapter(Activity activity) {
        this.activity = activity;

        RecyclerView mRecyclerView = ((AdvertActivity) activity).findViewById(R.id.rcv_advert);
        final LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if(!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold) && shouldListenToOnBottomReached) {
                    if(onBottomReachedListener != null) {
                        onBottomReachedListener.onBottomReached();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


        if(holder instanceof AdvertAdapter.AdvertViewHolder) {
            final AdvertAdapter.AdvertViewHolder viewHolder = (AdvertAdapter.AdvertViewHolder) holder;
            viewHolder.bind(data.get(position), new AdvertAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Advert item) {
                    Intent intent = new Intent(activity, DetailAdvertActivity.class);
                    intent.putExtra("advert_id", data.get(position).getId());
                    activity.startActivity(intent);
                    //Log.e("merci yoda", item.toString());


                }
            });

            final ImageView imvAdvert = viewHolder.imageView;

            final String advertName = data.get(position).getName();

            final int imageUrl;

            if (advertName.equals("Fruits et légumes")) {
                imageUrl = R.drawable.fruit;
            } else if (advertName.equals("Produits laitiers")) {
                imageUrl = R.drawable.milk;
            } else if (advertName.equals("Plats cuisinés")) {
                imageUrl = R.drawable.cooked_food;
            }else if (advertName.equals("Féculents")) {
                imageUrl = R.drawable.starchy;
            }else if (advertName.equals("Bonbons et sucreries")) {
                imageUrl = R.drawable.candy;
            } else if (advertName.equals("Boissons")) {
                imageUrl = R.drawable.drink;
            } else if (advertName.equals("Viandes, poissons et oeuf")) {
                imageUrl = R.drawable.vpo;
            } else if (advertName.equals("Surgelés")) {
                imageUrl = R.drawable.frozen;
            } else if (advertName.equals("Epices")) {
                imageUrl = R.drawable.epice;
            } else if (advertName.equals("Alimentation bébé")) {
                imageUrl = R.drawable.baby;
            } else if (advertName.equals("Thé, café et chocolat")) {
                imageUrl = R.drawable.coffee;
            } else {
                imageUrl =  R.drawable.fruit;
            }


            viewHolder.textViewName.setText(advertName);
            viewHolder.textViewDistance.setText(data.get(position).getIdUser().toString());
            viewHolder.textViewTime.setText("Disponible entre : " + data.get(position).getStartTimeSlot() + " et " + data.get(position).getEndTimeSlot());


            Picasso.get()
                    .load(imageUrl)
                    .transform(new CircleTransform())
                    .error(R.drawable.ic_launcher_background)
                    .into(imvAdvert);

        }

    }



    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public int getItemViewType(int position) {
        return data != null ? data.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM :0;
    }


    static class AdvertViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDistance;
        TextView textViewTime;
        ImageView imageView;

        AdvertViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.advertName);
            textViewDistance = itemView.findViewById(R.id.advertdistance);
            textViewTime = itemView.findViewById(R.id.adverttime);
            imageView = itemView.findViewById(R.id.img_advert);
        }

        public void bind(final Advert item, final AdvertAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.rcv_progress_bar);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity.getBaseContext()).inflate(R.layout.item_advert, parent, false);
            return new AdvertAdapter.AdvertViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity.getBaseContext()).inflate(R.layout.loading_item, parent, false);
            return new AdvertAdapter.LoadingViewHolder(view);
        }
        return null;
    }

    public void resetData(List<Advert> items) {
        this.data = items;
        notifyDataSetChanged();
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public void setShouldListenToOnBottomReached(boolean b) {
        shouldListenToOnBottomReached = b;
    }

}
