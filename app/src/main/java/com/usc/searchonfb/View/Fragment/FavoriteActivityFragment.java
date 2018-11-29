package com.usc.searchonfb.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.usc.searchonfb.R;
import com.usc.searchonfb.databinding.ContentFavoriteBinding;
import com.usc.searchonfb.view.activity.MainActivity;
import com.usc.searchonfb.view.activity.ResultsActivity;

import java.util.ArrayList;
import java.util.List;

import static com.usc.searchonfb.utils.Constants.CALL_FROM_FAVORITES;
import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

/**
 * Created by adarsh on 4/14/2017.
 */

public class FavoriteActivityFragment extends Fragment {

    ContentFavoriteBinding binding = null;

    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentFavoriteBinding.inflate(inflater, container, false);
        binding.toolbar.setTitle("Favorite");
        setUpTabsViewPager();
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setToolbar(binding.toolbar);
        super.onActivityCreated(savedInstanceState);
    }

    private void setUpTabsViewPager() {
        mViewPager = binding.viewPager;
        setupViewPager(mViewPager);
        binding.tabs.setupWithViewPager(binding.viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        View tabOne =  LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        ImageView mImageView = (ImageView)tabOne.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.users);
        TextView mTextView = (TextView)tabOne.findViewById(R.id.text_view_tab);
        mTextView.setText("Users");
        binding.tabs.getTabAt(0).setCustomView(tabOne);

        View tabTwo =  LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabTwo.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.pages);
        mTextView = (TextView)tabTwo.findViewById(R.id.text_view_tab);
        mTextView.setText("Pages");
        binding.tabs.getTabAt(1).setCustomView(tabTwo);

        View tabThree =  LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabThree.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.events);
        mTextView = (TextView)tabThree.findViewById(R.id.text_view_tab);
        mTextView.setText("Places");
        binding.tabs.getTabAt(2).setCustomView(tabThree);

        View tabFour =  LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabFour.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.places);
        mTextView = (TextView)tabFour.findViewById(R.id.text_view_tab);
        mTextView.setText("Events");
        binding.tabs.getTabAt(3).setCustomView(tabFour);

        View tabFive =  LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabFive.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.groups);
        mTextView = (TextView)tabFive.findViewById(R.id.text_view_tab);
        mTextView.setText("Groups");
        binding.tabs.getTabAt(4).setCustomView(tabFive);

    }

    public void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter((getActivity().getSupportFragmentManager()));

        Bundle args = new Bundle();
        args.putBoolean(CALL_FROM_FAVORITES, true);

        UserFragment mUserFragment = new UserFragment();
        mUserFragment.setArguments(args);
        adapter.addFrag(mUserFragment, "Users");

        PageFragment mPageFragment = new PageFragment();
        mPageFragment.setArguments(args);
        adapter.addFrag(mPageFragment, "Pages");

        PlaceFragment mPlaceFragment = new PlaceFragment();
        mPlaceFragment.setArguments(args);
        adapter.addFrag(mPlaceFragment, "Places");

        EventFragment mEventPageFragment = new EventFragment();
        mEventPageFragment.setArguments(args);
        adapter.addFrag(mEventPageFragment, "Events");

        GroupFragment mGroupFragment = new GroupFragment();
        mGroupFragment.setArguments(args);
        adapter.addFrag(mGroupFragment, "Groups");

        mViewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
