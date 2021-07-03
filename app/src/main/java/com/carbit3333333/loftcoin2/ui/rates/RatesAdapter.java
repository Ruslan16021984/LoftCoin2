package com.carbit3333333.loftcoin2.ui.rates;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.carbit3333333.loftcoin2.BuildConfig;
import com.carbit3333333.loftcoin2.R;
import com.carbit3333333.loftcoin2.databinding.LiRateBinding;
import com.carbit3333333.loftcoin2.data.Coin;
import com.carbit3333333.loftcoin2.util.Formatter;
import com.carbit3333333.loftcoin2.util.OutlineCyrcle;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class RatesAdapter extends ListAdapter<Coin, RatesAdapter.ViewHolder> {
    private int colorPositive = Color.GREEN;
    private int colorNegative = Color.RED;
    private LayoutInflater inflater;
    private Formatter<Double> formatter;

    protected RatesAdapter(Formatter<Double> formatter) {
        super(new DiffUtil.ItemCallback<Coin>() {
            @Override
            public boolean areItemsTheSame(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return oldItem.id() == newItem.id();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return Objects.equals(oldItem, newItem);
            }
        });
        this.formatter = formatter;
        setHasStableIds(true);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        inflater = LayoutInflater.from(context);
        TypedValue v = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.textPositive, v, true);
        colorPositive = v.data;
        context.getTheme().resolveAttribute(R.attr.textNegative, v, true);
        colorNegative = v.data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiRateBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Coin coin = getItem(position);
        holder.binding.symbol.setText(getItem(position).symbol());
        final NumberFormat format = NumberFormat.getCurrencyInstance();
        holder.binding.price.setText(formatter.format(coin.price()));
        holder.binding.change.setText(String.format(Locale.US, "%.2f%%", coin.change24h()));
        if (coin.change24h()>0){
            holder.binding.change.setTextColor(colorPositive);
        }else {
            holder.binding.change.setTextColor(colorNegative);
        }
        Picasso.get().load(BuildConfig.IMG_ENDPOINT + coin.id() +".png").into(holder.binding.logo);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LiRateBinding binding;

        public ViewHolder(@NonNull LiRateBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
            OutlineCyrcle.apply(binding.logo);
        }

    }
}
