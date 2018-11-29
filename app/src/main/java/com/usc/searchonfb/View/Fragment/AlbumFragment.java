package com.usc.searchonfb.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.usc.searchonfb.R;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.utils.ExpandableListItem;
import com.usc.searchonfb.utils.GetExpandableList;
import com.usc.searchonfb.view.adapter.ExpandableListAdapter;

import static com.usc.searchonfb.utils.Constants.DETAILS_DATA;
import static com.usc.searchonfb.utils.Constants.LAST_EXPANDED_POSITION;

/**
 * Created by adarsh on 4/15/2017.
 */
public class AlbumFragment extends Fragment {

    private volatile boolean dataInserted = false;

    private DetailsData mDetailData = null;

    private volatile boolean isActivityCreated = false;

    private TextView errorTv = null;

    static int lastExpandedPosition = -1;

    boolean dataUpdated = false;

    private ExpandableListView mExpandableListView = null;

    public AlbumFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        findIds(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(DETAILS_DATA,mDetailData);
        outState.putInt(LAST_EXPANDED_POSITION,lastExpandedPosition);
        super.onSaveInstanceState(outState);

    }

    private void findIds(View v) {
        errorTv = (TextView)v.findViewById(R.id.error_message);
        mExpandableListView = (ExpandableListView) v.findViewById(R.id.expandable_list);
    }

    public void insertData(DetailsData pDetailData) {
        this.mDetailData = pDetailData;
        dataInserted = true;
        if(isActivityCreated && !dataUpdated){
            updateUI();
        }
    }

    private void updateUI() {
        if(dataInserted){
            if (mDetailData != null) {
                dataUpdated = true;
                if (mDetailData.getAlbums() != null) {
                    //CONVERT THE DATA INTO THE REQUIRED FORMAT
                    GetExpandableList mExpandableList = GetExpandableList.getExpandableInstance();
                    ExpandableListItem mItem = mExpandableList.getExpandableListData(mDetailData);
                    if(mItem.getmHeaderList()!=null && mItem.getmHeaderList().size()!=0){
                        //Initialise adapter and set it to list
                        ExpandableListAdapter mExpandableAdapter = new ExpandableListAdapter(getActivity(), mItem.getmHeaderList(), mItem.getmExpandableListData());
                        // setting list adapter
                        mExpandableListView.setAdapter(mExpandableAdapter);
                        initializeGroupListener();
                        mExpandableListView.setVisibility(View.VISIBLE);
                        errorTv.setVisibility(View.GONE);
                    }else{
                        mExpandableListView.setVisibility(View.GONE);
                        errorTv.setVisibility(View.VISIBLE);
                    }
                }else{
                    mExpandableListView.setVisibility(View.GONE);
                    errorTv.setVisibility(View.VISIBLE);
                }
            }else{
                mExpandableListView.setVisibility(View.GONE);
                errorTv.setVisibility(View.VISIBLE);
            }
        }
    }


    private void initializeGroupListener() {
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    mExpandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityCreated = true;
        if(savedInstanceState!=null){
            if(savedInstanceState.getParcelable(DETAILS_DATA)!=null){
                mDetailData = savedInstanceState.getParcelable(DETAILS_DATA);
            }
            lastExpandedPosition = savedInstanceState.getInt(LAST_EXPANDED_POSITION);
        }
        if(mDetailData!=null){
            dataInserted = true;
        }
        if(!dataUpdated){
            updateUI();
        }
    }



}