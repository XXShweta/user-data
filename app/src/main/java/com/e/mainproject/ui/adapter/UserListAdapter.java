package com.e.mainproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e.mainproject.R;
import com.e.mainproject.databinding.ItemMainFragmentBinding;
import com.e.mainproject.storage.roomDatabase.userDB.UserEntity;
import com.e.mainproject.ui.viewmodel.ProjectMainViewModel;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder>{

    private Context mContext;
    private List<UserEntity> userEntities;
    private ProjectMainViewModel projectMainViewModel;

    public UserListAdapter(Context mContext, List<UserEntity> userEntities, ProjectMainViewModel projectMainViewModel) {
        this.mContext = mContext;
        this.userEntities = userEntities;
        this.projectMainViewModel = projectMainViewModel;
    }

    public void setListData(List<UserEntity> list){
        userEntities.clear();
        userEntities.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_fragment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        holder.binding.setProjectMainViewModel(projectMainViewModel);
        holder.binding.setEntity(userEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return userEntities.size();
    }

    class UserListViewHolder extends RecyclerView.ViewHolder{
        ItemMainFragmentBinding binding;
        UserListViewHolder(View itemView){
            super(itemView);
            binding = ItemMainFragmentBinding.bind(itemView);
        }
    }
}
