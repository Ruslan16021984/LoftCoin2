package com.carbit3333333.loftcoin2.ui.rates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.carbit3333333.loftcoin2.BaseComponent;
import com.carbit3333333.loftcoin2.R;
import com.carbit3333333.loftcoin2.databinding.FragmentRatesBinding;
import com.carbit3333333.loftcoin2.util.PriceFormatter;

import javax.inject.Inject;

import timber.log.Timber;

public class RatesFragment extends Fragment {
    private final RatesComponent component;
    private RatesAdapter adapter;
    private FragmentRatesBinding binding;
    private RatesViewModel viewModel;

    @Inject
    public RatesFragment(BaseComponent baseComponent) {
        component = DaggerRatesComponent.builder().baseComponent(baseComponent).build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, component.viewModelFactory()).get(RatesViewModel.class);
        adapter = new RatesAdapter(new PriceFormatter());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rates, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.rates, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
if (R.id.currency_dialog == item.getItemId()){
    NavHostFragment.findNavController( this ).navigate(R.id.currency_dialog);
}
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        binding = FragmentRatesBinding.bind(view);
        binding.recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recycler.swapAdapter(adapter, false);
        binding.recycler.setHasFixedSize(true);
        binding.refresher.setOnRefreshListener(viewModel::refresh);
        viewModel.coins().observe(getViewLifecycleOwner(), adapter::submitList);
        viewModel.ssRefreshing().observe(getViewLifecycleOwner(), (refreshing) ->{
            binding.refresher.setRefreshing(refreshing);
        });
    }
}
