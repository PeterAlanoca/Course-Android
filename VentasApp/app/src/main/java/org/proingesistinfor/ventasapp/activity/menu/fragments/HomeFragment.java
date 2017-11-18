package org.proingesistinfor.ventasapp.activity.menu.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.activity.ad.AdActivity;
import org.proingesistinfor.ventasapp.adapters.AdAdapter;
import org.proingesistinfor.ventasapp.base.BaseFragment;
import org.proingesistinfor.ventasapp.interfaces.RecyclerViewOnItemClickListener;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.Ad;

/**
 * Created by peter on 05-11-17.
 */

public class HomeFragment extends BaseFragment implements RecyclerViewOnItemClickListener, View.OnClickListener{

    private FloatingActionButton fab;
    private RecyclerView recyclerViewAds;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setTitle("Inicio");
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setBusinessLogic(new BusinessLogic(getActivity()));
        setAds(getBusinessLogic().getAdAll());
        recyclerViewAds = (RecyclerView) view.findViewById(R.id.recyclerView_ads);
        recyclerViewAds.setAdapter(new AdAdapter(getAds(), this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAds.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        Ad ad = getAds().get(position);
        replaceActivity(AdActivity.class, ad, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                replaceFragment(new NewAdFragment());
                break;
        }
    }
}






