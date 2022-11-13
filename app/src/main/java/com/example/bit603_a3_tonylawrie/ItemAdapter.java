package com.example.bit603_a3_tonylawrie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
  final Context context;
  final List<Inventory> itemList;

  public ItemAdapter(Context context, List<Inventory> itemList) {
    this.context = context;
    this.itemList = itemList;
  }

  @NonNull
  @Override
  public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
    holder.nameView.setText(itemList.get(position).getItem());
    holder.typeView.setText(itemList.get(position).getType());
    holder.quantityView.setText(itemList.get(position).toString());
  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }
}
