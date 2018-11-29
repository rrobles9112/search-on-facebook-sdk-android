package com.usc.searchonfb.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.usc.searchonfb.R;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.rest.model.SearchModel.Picture;
import com.usc.searchonfb.view.adapter.RecyclerViewAdapter;
import com.usc.searchonfb.view.adapter.RecyclerViewPostAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.usc.searchonfb.utils.Constants.CONST_USER;
import static com.usc.searchonfb.utils.Constants.DETAILS_DATA;
import static com.usc.searchonfb.utils.Constants.LAST_EXPANDED_POSITION;
import static com.usc.searchonfb.utils.Constants.TYPE;

/**
 * Created by adarsh on 4/15/2017.
 */
public class PostsFragment extends Fragment{


    private boolean dataInserted = false;

    private DetailsData mDetailData = null;

    private boolean isActivityCreated = false;

    RecyclerView mRecyclerView;

    RecyclerViewPostAdapter adapter;

    String type;

    TextView mErrorText;


    public PostsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_posts, container, false);
        findIds(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void findIds(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mErrorText = (TextView)v.findViewById(R.id.error_message);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(DETAILS_DATA,mDetailData);
        outState.putString(TYPE,type);
        super.onSaveInstanceState(outState);
    }

    public void insertData(DetailsData pDetailData, String type) {
        dataInserted = true;
        mDetailData = pDetailData;
        this.type = type;
        if(isActivityCreated){
            updateUI();
        }
    }

    private void updateUI() {
        //Toast.makeText(getActivity(),"In updateUI of post fragment",Toast.LENGTH_LONG).show();
        boolean postFound = false;

        if(mDetailData!=null){
            if(mDetailData.getPosts()!=null){
                if(mDetailData.getPosts().getData()!=null){
                    if(mDetailData.getPosts().getData().size()!=0){
                        postFound = true;
                        String name = mDetailData.getName();
                        Picture picture = mDetailData.getPicture();
                        adapter = new RecyclerViewPostAdapter(getActivity(),mDetailData.getPosts().getData(),type,name,picture);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mErrorText.setVisibility(View.GONE);

                    }
                }
            }
        }

        if(!postFound){
            mRecyclerView.setVisibility(View.GONE);
            mErrorText.setVisibility(View.VISIBLE);
        }
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityCreated = true;
        if(savedInstanceState!=null){
            if(savedInstanceState.getParcelable(DETAILS_DATA)!=null){
                mDetailData = savedInstanceState.getParcelable(DETAILS_DATA);
                dataInserted = true;
            }

            if(savedInstanceState.getParcelable(TYPE)!=null){
                type = savedInstanceState.getParcelable(TYPE);
            }
        }
        if(dataInserted){
            updateUI();
        }
    }

}