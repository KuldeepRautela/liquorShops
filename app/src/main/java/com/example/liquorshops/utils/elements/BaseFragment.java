package com.example.liquorshops.utils.elements;

import androidx.fragment.app.Fragment;

import com.example.liquorshops.utils.abstracts.BluePrints;


public class BaseFragment extends Fragment implements BluePrints.ofView, BluePrints.ofFrag, BluePrints.ofRecycler {
    public boolean mIsVisibleToUser; // you can see this variable may absolutely <=> getUserVisibleHint() but it not. Currently, after many test I find that

    /**
     * This method will call when viewpager create fragment and when we go to this fragment from
     * background or another activity, fragment
     * NOT call when we switch between each page in ViewPager
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * This method will call at first time viewpager created and when we switch between each page
     * NOT called when we go to background or another activity (fragment) when we go back
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
    }

    @Override
    public void initializeView() {

    }

    @Override
    public void initializePicker() {

    }

    @Override
    public void initializeListeners() {

    }

    @Override
    public void initializeData() {

    }

    @Override
    public void initializeViewModel() {

    }

    @Override
    public void initializeTabView() {

    }

    @Override
    public void closeEverything() {

    }

    @Override
    public void initializeRecyclerView() {

    }

    @Override
    public void initializeEmptyView(boolean isEmpty) {

    }


    @Override
    public void initializeFragsView() {

    }


}
