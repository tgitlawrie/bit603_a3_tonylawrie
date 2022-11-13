package com.example.bit603_a3_tonylawrie;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

  final TextView nameView, typeView ,quantityView;



  public ItemViewHolder(@NonNull View itemView) {
    super(itemView);
    nameView = itemView.findViewById(R.id.itemText);
    typeView = itemView.findViewById(R.id.typeText);
    quantityView = itemView.findViewById(R.id.quantityText);
  }
}
