package com.usc.searchonfb.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.usc.searchonfb.databinding.ContentMainBinding;
import com.usc.searchonfb.view.activity.MainActivity;
import com.usc.searchonfb.view.activity.ResultsActivity;

import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

//This is the main activity fragment which comes on the click of Home
public class MainActivityFragment extends Fragment {

    ContentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentMainBinding.inflate(inflater, container, false);
        initializeOnClickListeners();
        return binding.getRoot();
    }

    private void initializeOnClickListeners() {
        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.autoCompleteTextView.setText("");
            }
        });

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.autoCompleteTextView.getText().toString().trim().length()!=0){
                    //Start the result activity, which contains user, pages, places, groups....
                    Intent mIntent = new Intent(getActivity(), ResultsActivity.class);
                    mIntent.putExtra(SEARCH_STRING,binding.autoCompleteTextView.getText().toString());
                    startActivity(mIntent);

                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Plese enter a keyword!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).setToolbar(binding.toolbar);
    }
}
