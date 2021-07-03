package com.carbit3333333.loftcoin2.ui.currency;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.carbit3333333.loftcoin2.BaseComponent;
import com.carbit3333333.loftcoin2.R;
import com.carbit3333333.loftcoin2.databinding.DialoCurrencyBinding;
import com.carbit3333333.loftcoin2.data.CurrencyRepo;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

public class CurrencyDialog extends AppCompatDialogFragment {
//    private final CurrencyComponent component;
    private DialoCurrencyBinding binding;
    private CurrencyRepo currencyRepo;
    private CurrencyAdapter adapter;

//    @Inject
//    public CurrencyDialog(BaseComponent baseComponent) {
//        component = DaggerCurrencyComponent.builder()
//                .baseComponent(baseComponent)
//                .build();
//    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        currencyRepo = new CurrencyRepoImpl(requireContext());/
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
       binding = DialoCurrencyBinding.inflate(requireActivity().getLayoutInflater());
        return new MaterialAlertDialogBuilder(requireActivity()).
        setTitle(R.string.choose_currency).setView(binding.getRoot()).create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recycler.setAdapter(adapter);
        currencyRepo.availableCurrencys().observe(getViewLifecycleOwner(), currencies -> {adapter.submitList(currencies);

        });
    }
}
