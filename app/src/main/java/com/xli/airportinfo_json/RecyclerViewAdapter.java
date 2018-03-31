package com.xli.airportinfo_json;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ShangyuSun on 2018/3/20.
 */

public class RecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        public interface OnAdapterItemInteraction {
            void onItemSelected(ViewHolder holder, Integer position);
        }
        private ArrayList<String> mDataSet= new ArrayList<>();


        public RecyclerViewAdapter(ArrayList<String> mDataSet ) {
            this.mDataSet = mDataSet;
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_view, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder,
                                     final int position) {
            holder.eqTextView.setText(mDataSet.get(position));

        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView eqTextView;
            public ViewHolder(View view) {
                super(view);
                mView = view;
                eqTextView = view.findViewById(R.id.eq_row);
            }
        }
    }
