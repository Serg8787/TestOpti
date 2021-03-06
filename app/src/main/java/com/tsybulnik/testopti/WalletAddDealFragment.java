package com.tsybulnik.testopti;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.tsybulnik.testopti.database.DealsDatabase;
import com.tsybulnik.testopti.model.Deal;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WalletAddDealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletAddDealFragment extends Fragment {
    AutoCompleteTextView autoCompleteTextView;
    TextView tvData;
    EditText etNameTransaction;
    EditText etSum;
    ArrayAdapter arrayAdapter;
    DatePickerDialog.OnDateSetListener listener;
    Button btAddNewDeal;
    private WalletViewModel viewModel;


    // TODO: Rename and change types of parameters

    public WalletAddDealFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalletAddDealFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletAddDealFragment newInstance(String param1, String param2) {
        WalletAddDealFragment fragment = new WalletAddDealFragment();
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
        return inflater.inflate(R.layout.fragment_wallet_add_deal, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        tvData = view.findViewById(R.id.tvData);
        etNameTransaction = view.findViewById(R.id.etNameTransaction);
        etSum = view.findViewById(R.id.etSum);
        btAddNewDeal = view.findViewById(R.id.btAddNewDeal);
        DealsDatabase database = DealsDatabase.newInstance((Activity) getContext());

        NavController navController = Navigation.findNavController((Activity) getContext(), R.id.navHostFragment);
        viewModel = new ViewModelProvider(requireActivity()).get(WalletViewModel.class);


        String[] itemsAutoComplete = {getString(R.string.income), getString(R.string.rashod)};
        arrayAdapter = new ArrayAdapter( getContext(), R.layout.support_simple_spinner_dropdown_item, itemsAutoComplete);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int months = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog((Activity) getContext(),
                        R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Picker_Date_Calendar,
                        listener, year, months, day);
                datePickerDialog.show();
            }
        });

        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String data = day + "." + month + "." + year;
                tvData.setText(data);
            }
        };

        btAddNewDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etNameTransaction.getText().toString().isEmpty() &&
                        !tvData.getText().toString().isEmpty() &&
                        !autoCompleteTextView.getText().toString().isEmpty() &&
                        !etSum.getText().toString().isEmpty() &&
                        etNameTransaction.getText() != null &&
                        autoCompleteTextView.getText() != null &&
                        tvData.getText() != null &&
                        etSum.getText() != null) {
                    String sum = "";
                    String nameTransaction = etNameTransaction.getText().toString();
                    if (autoCompleteTextView.getText().toString().equals("????????????")) {
                        sum = "+ " + etSum.getText().toString() + " ???";
                    } else  {
                        sum = "- " + etSum.getText().toString() + " ???";
                    }
                    Deal deal = new Deal(nameTransaction, autoCompleteTextView.getText().toString(), sum, tvData.getText().toString());
                    // ?????? viewModel
//                    database.dealDao().insert(deal).subscribeOn(Schedulers.io()).subscribe();

                    viewModel.insert(deal);
                    navController.navigate(R.id.action_walletAddDealFragment_to_walLetFragment);
                } else {
                    Toast.makeText(getContext(), "?????????????????? ?????? ????????", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}