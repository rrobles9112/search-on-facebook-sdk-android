package com.usc.searchonfb.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.usc.searchonfb.FacebookApplication;
import com.usc.searchonfb.R;
import com.usc.searchonfb.dagger.component.DaggerResultFragmentsComponent;
import com.usc.searchonfb.dagger.component.ResultFragmentsComponent;
import com.usc.searchonfb.dagger.module.ActivityModule;
import com.usc.searchonfb.dagger.module.ResultFragmentModule;
import com.usc.searchonfb.databinding.ActivityResultsBinding;
import com.usc.searchonfb.view.fragment.EventFragment;
import com.usc.searchonfb.view.fragment.GroupFragment;
import com.usc.searchonfb.view.fragment.PageFragment;
import com.usc.searchonfb.view.fragment.PlaceFragment;
import com.usc.searchonfb.view.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

public class ResultsActivity extends AppCompatActivity {

    ActivityResultsBinding binding;
    private ViewPager mViewPager;
    private ResultFragmentsComponent mResultFragmentComponent;
    private String mSearchString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchString = getIntent().getStringExtra(SEARCH_STRING);
        initializeInjector();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);
        setUpToolBar();
        setUpTabsViewPager();
    }

    public ResultFragmentsComponent getResultFragmentComponent(){
        return mResultFragmentComponent;
    }

    private void initializeInjector() {
        mResultFragmentComponent = DaggerResultFragmentsComponent.builder()
                .activityModule(new ActivityModule(this))
                .resultFragmentModule(new ResultFragmentModule())
                .netComponent(((FacebookApplication)getApplicationContext()).getNetComponent())
                .build();
    }

    private void setUpTabsViewPager() {
        mViewPager = binding.viewPager;
        setupViewPager(mViewPager);
        binding.tabs.setupWithViewPager(binding.viewPager);
        setupTabIcons();
    }

    private void setUpToolBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("  " + getString(R.string.results));
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupTabIcons() {
        View tabOne =  LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView mImageView = (ImageView)tabOne.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.users);
        TextView mTextView = (TextView)tabOne.findViewById(R.id.text_view_tab);
        mTextView.setText("Users");
        binding.tabs.getTabAt(0).setCustomView(tabOne);

        View tabTwo =  LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabTwo.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.pages);
        mTextView = (TextView)tabTwo.findViewById(R.id.text_view_tab);
        mTextView.setText("Pages");
        binding.tabs.getTabAt(1).setCustomView(tabTwo);

        View tabThree =  LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabThree.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.events);
        mTextView = (TextView)tabThree.findViewById(R.id.text_view_tab);
        mTextView.setText("Places");
        binding.tabs.getTabAt(2).setCustomView(tabThree);

        View tabFour =  LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabFour.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.places);
        mTextView = (TextView)tabFour.findViewById(R.id.text_view_tab);
        mTextView.setText("Events");
        binding.tabs.getTabAt(3).setCustomView(tabFour);

        View tabFive =  LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabFive.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.groups);
        mTextView = (TextView)tabFive.findViewById(R.id.text_view_tab);
        mTextView.setText("Groups");
        binding.tabs.getTabAt(4).setCustomView(tabFive);

    }

    //In the result activity we have 5 fragments one for each tab
    public void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle args = new Bundle();
        args.putString(SEARCH_STRING, mSearchString);

        //This is the usertab
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
