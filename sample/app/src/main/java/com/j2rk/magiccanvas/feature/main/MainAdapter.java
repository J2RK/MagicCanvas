package com.j2rk.magiccanvas.feature.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.j2rk.magiccanvas.databinding.ViewMainMenuBinding;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<MainMenu> items;

    public MainAdapter(List<MainMenu> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(ViewMainMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.binding.setItem(items.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        ViewMainMenuBinding binding;

        public MainViewHolder(ViewMainMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}