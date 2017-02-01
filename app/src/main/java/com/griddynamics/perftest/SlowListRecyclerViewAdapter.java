package com.griddynamics.perftest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.griddynamics.perftest.DummyContent.DummyItem;

import java.util.List;


class SlowListRecyclerViewAdapter extends RecyclerView.Adapter<SlowListRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final String elementType;

    SlowListRecyclerViewAdapter(List<DummyItem> items, String elementType) {
        mValues = items;
        this.elementType = elementType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (ListTypes.SLOW.equals(elementType)) {
            view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.slow_custom_view, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        DummyItem mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
