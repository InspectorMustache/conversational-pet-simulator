package com.inspmustache.android.conversationalpetsimulator;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspmustache.android.conversationalpetsimulator.animals.AnimalAbstract;
import com.inspmustache.android.conversationalpetsimulator.animals.Cat;
import com.inspmustache.android.conversationalpetsimulator.chatting.ChatFragment;

/**
 * The MainMenu fragment from which to select a category.
 */
public class CategoriesFragment extends Fragment {


    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate fragment from xml layout
        View fragmentView = inflater.inflate(R.layout.fragment_categories, container,false);

        // connect all category buttons to generic onclicklistener
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id of clicked view and background color of entry to fragment
                ColorDrawable backgroundColor = (ColorDrawable) v.getBackground();
                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putInt("backgroundColor", backgroundColor.getColor());
                fragmentArgs.putInt("clickedViewId", v.getId());

                Fragment newFragment = new ChatFragment();
                newFragment.setArguments(fragmentArgs);

                switchToCategory(newFragment);
            }
        };

        fragmentView.findViewById(R.id.cat_entry).setOnClickListener(onClickListener);
        fragmentView.findViewById(R.id.dog_entry).setOnClickListener(onClickListener);
        fragmentView.findViewById(R.id.chicken_entry).setOnClickListener(onClickListener);

        // return the fragment
        return fragmentView;
    }

    /**
     *
     * @param switchFragment the Fragment to switch to
     * Switch to one of the category fragments
     */
    private void switchToCategory(Fragment switchFragment) {
        // switch to new Fragment
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment_container, switchFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // push onto back stack, so we can navigate back
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
