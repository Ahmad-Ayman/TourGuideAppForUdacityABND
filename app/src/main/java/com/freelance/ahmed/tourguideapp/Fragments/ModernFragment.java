package com.freelance.ahmed.tourguideapp.Fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.freelance.ahmed.tourguideapp.Adapters.TourAdapter;
import com.freelance.ahmed.tourguideapp.ApiClients.ApiClient;
import com.freelance.ahmed.tourguideapp.Interfaces.ApiInterface;
import com.freelance.ahmed.tourguideapp.Models.Places;
import com.freelance.ahmed.tourguideapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModernFragment extends Fragment {

    private static final String TAG = ModernFragment.class.getSimpleName();
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMsgDisplay;
    private static final String MODERN_FRAG = "modern";
    private GridLayoutManager lLayout;
    private TourAdapter tourAdapter;
    private RecyclerView rView;
    private SwipeRefreshLayout swiping;
    int lastFirstVisiblePosition;
    private ApiInterface apiInterface;
    private ArrayList<Places.PlacesData> mlist = new ArrayList<>();

    public ModernFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modern, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.modern));
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            lLayout = new GridLayoutManager(getActivity(), 2);
        } else {
            lLayout = new GridLayoutManager(getActivity(), 4);
        }
        mLoadingIndicator = (ProgressBar) view.findViewById(R.id.pb_loading_indicator);
        mErrorMsgDisplay = (TextView) view.findViewById(R.id.errormsg);
        rView = (RecyclerView) view.findViewById(R.id.rv_places);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        getPlacesDataFromRetrofit();
        swiping = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        swiping.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getPlacesDataFromRetrofit();
                        rView.setVisibility(View.VISIBLE);
                        swiping.setRefreshing(false);
                    }
                }
        );


    }

    private void getPlacesDataFromRetrofit() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Places> call = apiInterface.getPlaces(MODERN_FRAG);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                Log.e(TAG, response.body().getResults().toString());
                List<Places.PlacesData> rlist = response.body().getResults();
                if (rlist != null && !rlist.isEmpty()) {
                    tourAdapter = new TourAdapter(getActivity(), rlist);
                    rView.setAdapter(tourAdapter);
                    showPlacesDataView();
                    Log.e(getResources().getString(R.string.showdatalog), getResources().getString(R.string.successLog));
                } else {
                    showError();
                    Log.e(getResources().getString(R.string.showdatalog), getResources().getString(R.string.errorLog));
                }
            }

            @Override
            public void onFailure(Call<Places> call, Throwable t) {
                Log.d(getResources().getString(R.string.failure), t.toString());
                showError();

            }
        });
    }

    private void showPlacesDataView() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mErrorMsgDisplay.setVisibility(View.INVISIBLE);
        rView.setVisibility(View.VISIBLE);

    }

    private void showError() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        rView.setVisibility(View.INVISIBLE);
        mErrorMsgDisplay.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((GridLayoutManager) rView.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);
        lastFirstVisiblePosition = 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lastFirstVisiblePosition = ((GridLayoutManager) rView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    @Override
    public void onStop() {
        super.onStop();
        lastFirstVisiblePosition = ((GridLayoutManager) rView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }
}
