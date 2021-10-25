package com.tsybulnik.testopti;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.tsybulnik.testopti.adapter.DealAdapter;
import com.tsybulnik.testopti.database.DealsDatabase;
import com.tsybulnik.testopti.model.Deal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment implements LifecycleOwner {
    private RecyclerView recyclerViewDeals;
    private DealAdapter dealAdapter;
    List<Deal> dealsList = new ArrayList();
    public static DealsDatabase database;
    private WalletViewModel viewModel;


    public WalletFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalLetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletFragment newInstance(String param1, String param2) {
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewDeals = view.findViewById(R.id.rvDeals);

        NavController navController = Navigation.findNavController((Activity) getContext(), R.id.navHostFragment);
        database = DealsDatabase.newInstance((Activity) getContext());

        viewModel = new ViewModelProvider(requireActivity()).get(WalletViewModel.class);
        viewModel.getList().subscribeOn(Schedulers.computation()).
                observeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(dealList -> {
                    dealAdapter = new DealAdapter((Activity) getContext(), dealList);
                    recyclerViewDeals.setAdapter(dealAdapter);

                }, e ->
                        Toast.makeText(getContext(), "fsdf", Toast.LENGTH_LONG).show());

        getView().findViewById(R.id.ivAddDeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.walletAddDealFragment);
            }
        });


    }

}