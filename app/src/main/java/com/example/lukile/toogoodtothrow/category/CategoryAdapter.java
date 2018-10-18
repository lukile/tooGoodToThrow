package com.example.lukile.toogoodtothrow.category;

import android.app.Activity;
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
import com.example.lukile.toogoodtothrow.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private int totalItemCount, lastVisibleItem;

    private int visibleThreshold = 5;

    private Activity activity;

    private OnBottomReachedListener onBottomReachedListener;
    private boolean isLoading;
    private boolean shouldListenToOnBottomReached = true;

    private List<String> data = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }


    public CategoryAdapter(List<String> data, OnItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }


    public CategoryAdapter(Activity activity) {
        this.activity = activity;

        RecyclerView mRecyclerView = ((CategoryActivity) activity).findViewById(R.id.rcv_category);
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if(holder instanceof CategoryViewHolder) {
            final CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
            viewHolder.bind(data.get(position), new OnItemClickListener() {
                @Override
                public void onItemClick(String item) {
                    Log.e("merci yoda", item);
                }
            });

            final ImageView imvCharacter = viewHolder.imageView;

            final String categoryName = data.get(position);

            final int imageUrl;

            if (categoryName.equals("Fruits et légumes")) {
                imageUrl = R.drawable.fruit;
            } else if (categoryName.equals("Produits laitiers")) {
                imageUrl = R.drawable.milk;
            } else if (categoryName.equals("Plats cuisinés")) {
                imageUrl = R.drawable.cooked_food;
            }else if (categoryName.equals("Féculents")) {
                imageUrl = R.drawable.starchy;
            }else if (categoryName.equals("Bonbons et sucreries")) {
                imageUrl = R.drawable.candy;
            } else if (categoryName.equals("Boissons")) {
                imageUrl = R.drawable.drink;
            } else if (categoryName.equals("Viandes, poissons et oeuf")) {
                imageUrl = R.drawable.vpo;
            } else if (categoryName.equals("Surgelés")) {
                imageUrl = R.drawable.frozen;
            } else if (categoryName.equals("Epices")) {
                imageUrl = R.drawable.epice;
            } else if (categoryName.equals("Alimentation bébé")) {
                imageUrl = R.drawable.baby;
            } else if (categoryName.equals("Thé, café et chocolat")) {
                imageUrl = R.drawable.coffee;
            } else {
                imageUrl =  R.drawable.fruit;
            }


            viewHolder.textView.setText(categoryName);

            Picasso.get()
                    .load(imageUrl)
                    .transform(new CircleTransform())
                    .error(R.drawable.ic_launcher_background)
                    .into(imvCharacter);

        }

        Log.e("codopfshjsodjfmojqsmù", data.get(position));



    }



    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public int getItemViewType(int position) {
        return data != null ? data.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM :0;
    }


    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        CategoryViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.category);
            imageView = itemView.findViewById(R.id.img_category);
        }

        public void bind(final String item, final OnItemClickListener listener) {

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
            View view = LayoutInflater.from(activity.getBaseContext()).inflate(R.layout.item_category, parent, false);
            return new CategoryViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity.getBaseContext()).inflate(R.layout.loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    public void resetData(List<String> items) {
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
