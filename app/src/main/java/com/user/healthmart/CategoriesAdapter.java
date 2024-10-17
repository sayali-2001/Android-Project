package com.user.healthmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<CategoriesItem> categoryItemList;

    // Constructor to pass the list of categories
    public CategoriesAdapter(List<CategoriesItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    // ViewHolder class definition inside the adapter
    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        // Declare the views here (e.g., TextView, ImageView)
        TextView categoryName;
        ImageView categoryImage;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views using itemView.findViewById
            categoryName = itemView.findViewById(R.id.itemText);
            categoryImage = itemView.findViewById(R.id.itemImageButton);
        }
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categories, parent, false); // Assuming your layout XML is item_category.xml
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        // Get the category at the current position
        CategoriesItem categoryItem = categoryItemList.get(position);

        // Bind data to the views
        holder.categoryName.setText(categoryItem.getName());
        holder.categoryImage.setImageResource(categoryItem.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the list
        return categoryItemList.size();
    }
}

