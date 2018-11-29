package com.usc.searchonfb.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.usc.searchonfb.FacebookApplication;
import com.usc.searchonfb.R;
import com.usc.searchonfb.dagger.component.DaggerDetailsActivityComponent;
import com.usc.searchonfb.dagger.component.DetailsActivityComponent;
import com.usc.searchonfb.dagger.module.DetailsFragmentModule;
import com.usc.searchonfb.databinding.ActivityResultsBinding;
import com.usc.searchonfb.presenter.DetailActivityPresenter;
import com.usc.searchonfb.presenter.contract.DetailsPresenterContract;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.rest.model.SearchModel.Picture;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.utils.FavoriteSharedPreference;
import com.usc.searchonfb.view.fragment.AlbumFragment;
import com.usc.searchonfb.view.fragment.PostsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.usc.searchonfb.utils.Constants.CONST_TYPE;
import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

public class DetailsActivity extends AppCompatActivity implements DetailsPresenterContract.View {

    ActivityResultsBinding binding;

    private ViewPager mViewPager;

    private DetailsActivityComponent mDaggerDetailComponent;

    private SearchData mSearchData = null;

    private String mType = null;

    private AlbumFragment mAlbumFragment = null;

    private PostsFragment mPostFragment = null;

    CallbackManager callbackManager;

    ShareDialog shareDialog;

    String mContentTitle = "Homework8";

    String mImageURL;

    String mShareUrl = "http://adarsh.us-west-2.elasticbeanstalk.com/index/index.html";

    String mContentDescription = "FB SEARCH FROM USC CSCI571";

    DetailsData mDetailsData;

    @Inject
    DetailActivityPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        mSearchData = getIntent().getParcelableExtra(SEARCH_STRING);
        mType = getIntent().getStringExtra(CONST_TYPE);

        initializeInjector();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);
        setUpToolBar();
        setUpTabsViewPager();
        mainPresenter.attach(this);
        mainPresenter.load(mSearchData.getId());
        registerShareDialogCallBacks();
    }

    private void loadShareData(DetailsData mDetailsData) {
        if (mDetailsData == null) {
            return;
        }
        if (mDetailsData != null) {
            if(mDetailsData.getName()!=null){
                mContentTitle = mDetailsData.getName();
            }
            Picture picture = mDetailsData.getPicture();
            if (picture != null && picture.getData() != null) {
                if (picture.getData().getUrl() != null) {
                    mImageURL = picture.getData().getUrl();
                }
            }

        }
    }

    private void registerShareDialogCallBacks() {
        shareDialog = new ShareDialog(this);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                //Toast.makeText(getApplicationContext(),"Successfully Posted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Sharing cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), "Failed to post", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private void facebookSDKInitialize() {
        callbackManager = CallbackManager.Factory.create();
    }

    private void onShare() {
        if (mImageURL == null) {
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(mContentTitle)
                        .setContentDescription(
                                mContentDescription)
                        .setContentUrl(Uri.parse(mShareUrl))
                        .build();

                shareDialog.show(linkContent);  // Show facebook ShareDialog
            }
        } else {
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(mContentTitle)
                        .setImageUrl(Uri.parse(mImageURL))
                        .setContentDescription(
                                mContentDescription)
                        .setContentUrl(Uri.parse(mShareUrl))
                        .build();

                shareDialog.show(linkContent);  // Show facebook ShareDialog
            }
        }
    }


    private void initializeInjector() {
        mDaggerDetailComponent = DaggerDetailsActivityComponent.builder()
                .detailsFragmentModule(new DetailsFragmentModule())
                .netComponent(((FacebookApplication) getApplicationContext()).getNetComponent())
                .build();
        mDaggerDetailComponent.inject(this);
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
        getSupportActionBar().setTitle("  " + getString(R.string.title_details));
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupTabIcons() {
        View tabOne = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView mImageView = (ImageView) tabOne.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.albums);
        TextView mTextView = (TextView) tabOne.findViewById(R.id.text_view_tab);
        mTextView.setText("Albums");
        binding.tabs.getTabAt(0).setCustomView(tabOne);

        View tabTwo = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        mImageView = (ImageView) tabTwo.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.posts);
        mTextView = (TextView) tabTwo.findViewById(R.id.text_view_tab);
        mTextView.setText("Posts");
        binding.tabs.getTabAt(1).setCustomView(tabTwo);
    }

    public void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAlbumFragment = new AlbumFragment();
        adapter.addFrag(mAlbumFragment, "Albums");
        mPostFragment = new PostsFragment();
        adapter.addFrag(mPostFragment, "Posts");
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        selectMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        selectMenu(menu);
        return true;
    }

    private void selectMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();

        if (FavoriteSharedPreference.isFavorite(this, mSearchData, mType)) {
            inflater.inflate(R.menu.menu_remove_favorites, menu);
        } else {
            inflater.inflate(R.menu.menu_add_favorites, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_to_favorites:
                FavoriteSharedPreference.addFavoriteItem(this, mSearchData, mType);
                Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_remove_from_favorites:
                FavoriteSharedPreference.deleteFavoriteItem(this, mSearchData, mType);
                Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_share:
                onShare();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void addResults(DetailsData mDetailsData) {

        this.mDetailsData = mDetailsData;

        if (mAlbumFragment != null) {
            mAlbumFragment.insertData(mDetailsData);
        }

        if (mPostFragment != null) {
            mPostFragment.insertData(mDetailsData, mType);
        }

        loadShareData(mDetailsData);
    }

    @Override
    public void clearResults() {

    }

    @Override
    public void showContentLoading() {

    }

    @Override
    public void hideContentLoading() {

    }

    @Override
    public void showListLoading() {

    }

    @Override
    public void hideListLoading() {

    }

    @Override
    public void showContentError() {
        //show posts and albums as error
        if (mAlbumFragment != null) {
            mAlbumFragment.insertData(null);
        }

        if (mPostFragment != null) {
            mPostFragment.insertData(null, mType);
        }
    }

    @Override
    public void hideContentError() {

    }

    @Override
    public void showListError() {

    }

    @Override
    public void showEmptyResultsView() {

    }

    @Override
    public void hideEmptyResultsView() {

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
