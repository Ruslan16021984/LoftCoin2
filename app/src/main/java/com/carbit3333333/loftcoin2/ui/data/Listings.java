package com.carbit3333333.loftcoin2.ui.data;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Listings {
    abstract List<AutoValue_Coin> data();
}
