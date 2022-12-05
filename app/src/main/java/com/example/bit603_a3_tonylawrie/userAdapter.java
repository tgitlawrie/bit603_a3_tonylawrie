package com.example.bit603_a3_tonylawrie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder> {
  private static final String TAG = "adapter";
  private LayoutInflater layoutInflater;
  private List<User> userData;

  userAdapter(Context context, List<User> userData) {
    this.layoutInflater = LayoutInflater.from(context);
    this.userData = userData;
    Log.d(TAG, "userAdapter: " +userData.toString());
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.user_view, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //bind userdata with recyclerview text views
    String empNum = "Emp #: " + userData.get(position).getEmpNumber();
    String username = userData.get(position).getUsername();
    String DoB = "D.O.B: " + userData.get(position).getDOB();
    String phone = "Phone: " + userData.get(position).getPhone();
    String address = "Address: " + userData.get(position).getAddress();

    holder.empNum.setText(empNum);
    holder.username.setText(username.toUpperCase());
    holder.Dob.setText(DoB);
    holder.phone.setText(phone);
    holder.address.setText(address);
  }

  @Override
  public int getItemCount() {
    return userData.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView empNum, username, Dob, phone, address;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      empNum = itemView.findViewById(R.id.rm_empNum_Text);
      username = itemView.findViewById(R.id.rm_username_text);
      Dob = itemView.findViewById(R.id.rm_DoB_text);
      phone = itemView.findViewById(R.id.rm_phone_text);
      address = itemView.findViewById(R.id.rm_address_text);
    }
  }
}
