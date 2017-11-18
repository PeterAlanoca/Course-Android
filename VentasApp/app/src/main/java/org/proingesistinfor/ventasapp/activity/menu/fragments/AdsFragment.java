package org.proingesistinfor.ventasapp.activity.menu.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.adapters.AdAdapter;
import org.proingesistinfor.ventasapp.base.BaseFragment;
import org.proingesistinfor.ventasapp.interfaces.RecyclerViewOnItemClickListener;

/**
 * Created by peter on 05-11-17.
 */

public class AdsFragment extends BaseFragment implements View.OnClickListener, RecyclerViewOnItemClickListener {

    private FloatingActionButton fab;
    private RecyclerView recyclerViewAds;

    public static AdsFragment newInstance() {
        AdsFragment fragment = new AdsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ads, container, false);
        setTitle("Mis anuncios");
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        recyclerViewAds = (RecyclerView) view.findViewById(R.id.recyclerView_ads);
        recyclerViewAds.setAdapter(new AdAdapter(getBusinessLogic().getAdAll(getUser().getId()), this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAds.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                replaceFragment(new NewAdFragment());
                break;
        }
    }

    @Override
    public void onClick(View view, int position) {

    }
}
