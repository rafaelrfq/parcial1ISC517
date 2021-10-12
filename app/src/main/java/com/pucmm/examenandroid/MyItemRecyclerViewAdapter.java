package com.pucmm.examenandroid;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pucmm.examenandroid.placeholder.PlaceholderContent.PlaceholderItem;
import com.pucmm.examenandroid.databinding.FragmentItemBinding;
import com.pucmm.examenandroid.placeholder.PlaceholderContentExamen;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderContentExamen.PlaceholderVersion> mValues;
    private final OnTouchListener<PlaceholderContentExamen.PlaceholderVersion> mListener;

    public MyItemRecyclerViewAdapter(List<PlaceholderContentExamen.PlaceholderVersion> items, OnTouchListener<PlaceholderContentExamen.PlaceholderVersion> listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding binding = FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, mListener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mContentView;
        public PlaceholderContentExamen.PlaceholderVersion mItem;
        private OnTouchListener<PlaceholderContentExamen.PlaceholderVersion> mListener;

        public ViewHolder(FragmentItemBinding binding, OnTouchListener<PlaceholderContentExamen.PlaceholderVersion> listener) {
            super(binding.getRoot());
            mContentView = binding.content;
            mListener = listener;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(mItem);
        }
    }
}