package packages.androidclientapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import packages.androidclientapp.activities.LoggedinActivity;
import packages.androidclientapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalResultsFragment extends Fragment {


    public TotalResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((LoggedinActivity) getActivity()).setActionBarTitle(R.string.totalresultsfragment );
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_total_results, container, false);
    }

}
