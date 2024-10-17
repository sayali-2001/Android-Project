package com.user.healthmart;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;

public class RecommendationsAdapter extends RecyclerView.Adapter<RecommendationsAdapter.ViewHolder> {

    private List<ReccomendedProduct> recList;
    ReccomendedProduct recProduct;

    public RecommendationsAdapter(List<ReccomendedProduct> recListList) {
        this.recList = recList != null ? recList : new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName, companyName, location, productPrice;
        public Button addToCartButton;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            companyName = itemView.findViewById(R.id.companyName);
            location = itemView.findViewById(R.id.location);
            productPrice = itemView.findViewById(R.id.productPrice);
            addToCartButton = itemView.findViewById(R.id.getAddToCart);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Log when a new ViewHolder is created
        Log.d("RecyclerView", "onCreateViewHolder called");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (recList != null) {
            Log.d("RecyclerView", "getItemCount: " + recList.size());
            return recList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get element from the dataset at this position
       ReccomendedProduct product = recList.get(position);

       if(product != null){
           // Log the product details being bound to the view
           Log.d("RecyclerView", "Binding product: " + product.getName());

           // Set product details to the views
           holder.productName.setText(product.getName());
           holder.companyName.setText(product.getCompany());
           holder.location.setText(product.getLocation());
           holder.productPrice.setText(product.getPrice());
           holder.productImage.setImageResource(product.getImageResId());

           // Handle button click for adding to cart (optional)
           holder.addToCartButton.setOnClickListener(v -> {
               // Your add-to-cart logic
           });
       }
       else {
           Log.e("RecyclerView", "Product is null at position: " + position);
       }
    }



    }

