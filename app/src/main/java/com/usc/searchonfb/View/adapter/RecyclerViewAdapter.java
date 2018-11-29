package com.usc.searchonfb.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.usc.searchonfb.R;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.utils.Constants;
import com.usc.searchonfb.utils.FavoriteSharedPreference;
import com.usc.searchonfb.view.activity.DetailsActivity;

import java.util.List;

import static com.usc.searchonfb.utils.Constants.CONST_USER;

/**
 * Created by adarsh on 4/4/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private List<SearchData> searchDataList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private String type;
    private boolean isFavorite = false;


    public RecyclerViewAdapter(Context context, List<SearchData> searchDataList, String type, boolean isFavorite) {
        this.searchDataList = searchDataList;
        this.mContext = context;
        this.type = type;
        this.isFavorite = isFavorite;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<SearchData> data) {
        if(data.size()==0 && isFavorite && onItemClickListener!=null){
            onItemClickListener.onItemClick(0);
        }
        this.searchDataList = data;
    }

    public void loadMoreData(List<SearchData> data) {
        this.searchDataList.addAll(data);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        final SearchData mSearchData = searchDataList.get(position);
        if (mSearchData.getName() != null) {
            holder.mProfileText.setText(mSearchData.getName());
        }

        if (FavoriteSharedPreference.isFavorite(mContext.getApplicationContext(), mSearchData, type)) {
            holder.mFavorites.setBackgroundResource(R.drawable.favorites_on);
        } else {
            holder.mFavorites.setBackgroundResource(R.drawable.favorites_off);
        }

        if (mSearchData.getPicture() != null) {
            if (mSearchData.getPicture().getData() != null) {
                if (mSearchData.getPicture().getData().getUrl() != null) {
                    String mUrl = mSearchData.getPicture().getData().getUrl();
                    Picasso.with(mContext).load(mUrl)
                            .resize(60, 60).into(holder.mProfileImage);
                }
            }
        }

        View.OnClickListener favoriteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FavoriteSharedPreference.isFavorite(mContext.getApplicationContext(), mSearchData, type)) {
                    FavoriteSharedPreference.deleteFavoriteItem(mContext.getApplicationContext(), mSearchData, type);
                    holder.mFavorites.setBackgroundResource(R.drawable.favorites_off);
                } else {
                    FavoriteSharedPreference.addFavoriteItem(mContext.getApplicationContext(), mSearchData, type);
                    holder.mFavorites.setBackgroundResource(R.drawable.favorites_on);
                }

                if(isFavorite){
                    updateData();
                }
            }
        };

        holder.mFavorites.setOnClickListener(favoriteClickListener);

        holder.mNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, DetailsActivity.class);
                mIntent.putExtra(Constants.SEARCH_STRING,mSearchData);
                mIntent.putExtra(Constants.CONST_TYPE,type);
                mContext.startActivity(mIntent);
            }
        });

    }

    private void updateData() {
        setData(FavoriteSharedPreference.getFavouriteList(mContext,type));
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null != searchDataList ? searchDataList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView mProfileImage;

        protected TextView mProfileText;

        protected ImageButton mFavorites;

        protected ImageButton mNextPage;

        protected View itemView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.mProfileImage = (ImageView) itemView.findViewById(R.id.image_search);
            this.mProfileText = (TextView) itemView.findViewById(R.id.name_search);
            this.mFavorites = (ImageButton) itemView.findViewById(R.id.favorite_button);
            this.mNextPage = (ImageButton) itemView.findViewById(R.id.details_button);
            this.itemView = itemView;
        }

    }
}
