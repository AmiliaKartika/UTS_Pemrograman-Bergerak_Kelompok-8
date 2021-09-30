package com.ahmadabuhasan.appgithubuser;

import android.widget.Filter;

import java.util.ArrayList;

public class SearchFilter extends Filter {

    final ArrayList<User> filterList;
    UserAdapter userAdapter;

    public SearchFilter(ArrayList<User> filterList, UserAdapter userAdapter) {
        this.filterList = filterList;
        this.userAdapter = userAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<User> filteredData = new ArrayList<>();
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getName().toUpperCase().contains(constraint)) {
                    filteredData.add(filterList.get(i));
                }
            }
            results.count = filteredData.size();
            results.values = filteredData;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        userAdapter.listUser = (ArrayList<User>) results.values;
        userAdapter.notifyDataSetChanged();
    }
}