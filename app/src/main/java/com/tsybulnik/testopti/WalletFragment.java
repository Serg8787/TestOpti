package com.tsybulnik.testopti;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsybulnik.testopti.adapter.DealAdapter;
import com.tsybulnik.testopti.database.DealsDatabase;
import com.tsybulnik.testopti.model.Deal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment implements LifecycleOwner {
    private RecyclerView recyclerViewDeals;
    private DealAdapter dealAdapter;
    private ViewModelDeal viewModelDeal;
    List<Deal> dealsList = new ArrayList();
    public static DealsDatabase database;



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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        NavController navController = Navigation.findNavController((Activity) getContext(),R.id.navHostFragment);
        viewModelDeal = new ViewModelProvider(this).get(ViewModelDeal.class);
        database = DealsDatabase.newInstance((Activity) getContext());
        dealsList = database.dealDao().getAll();

        recyclerViewDeals = view.findViewById(R.id.rvDeals);
        // создаем адаптер
        dealAdapter = new DealAdapter((Activity) getContext(),dealsList);
        // устанавливаем для списка адаптер
        recyclerViewDeals.setAdapter(dealAdapter);



       getView().findViewById(R.id.ivAddDeal).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               navController.navigate(R.id.walletAddDealFragment);           }
       });


    }

}