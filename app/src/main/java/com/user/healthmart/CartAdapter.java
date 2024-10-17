package com.user.healthmart;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    public Spinner quantitySpinner;
    private List<Product> cartItems;
    private OnItemRemovedListener onItemRemovedListener;

    public CartAdapter(List<Product> cartItems, OnItemRemovedListener listener) {
        this.cartItems = cartItems;
        this.onItemRemovedListener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Product product = cartItems.get(holder.getAdapterPosition());

        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));

        // Load the image from URI using Glide
        Glide.with(holder.itemView.getContext())  // Get context from the ViewHolder's itemView
                .load(product.getImageUri())
                .placeholder(R.drawable.img_placeholder)  // Placeholder image
                .error(R.drawable.error_img)  // Image in case of error
                .into(holder.productImage);

        //Populate the spinner with quantities
        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(holder.itemView.getContext(), android.R.layout.simple_spinner_item, Arrays.asList("1", "2", "3", "More..."));
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.quantitySpinner.setAdapter(quantityAdapter);

//        //Handle Quantity Selection
        // Handle quantity selection
        holder.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedQuantity = parent.getItemAtPosition(position).toString();
                if (selectedQuantity.equals("More...")) {
                    // Handle custom quantity logic (e.g., open a dialog to enter quantity)
                } else {
                    product.setQuantity(Integer.parseInt(selectedQuantity));  // Update quantity in product object
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // Handle remove item logic
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition(); // Get the updated position here
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // Remove the item from the list
                    cartItems.remove(currentPosition);
                    // Notify adapter about item removal
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, cartItems.size());
                    if (onItemRemovedListener != null) {
                        onItemRemovedListener.onItemRemoved(currentPosition); // Use updated position
                        onItemRemovedListener.onCartUpdated(cartItems.size());  // Update badge count
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public Spinner quantitySpinner;
        TextView productName, productPrice;
        ImageButton removeButton;
        ImageView productImage;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.cartProductImage);
            productName = itemView.findViewById(R.id.cartProductName);
            productPrice = itemView.findViewById(R.id.cartProductPrice);
            removeButton = itemView.findViewById(R.id.removeButton);
            quantitySpinner = itemView.findViewById(R.id.quantitySpinner);// Add this button in the layout
        }
    }

    // Interface to notify the activity when an item is removed
    public interface OnItemRemovedListener {
        void onItemRemoved(int position);

        void onCartUpdated(int size);
    }
}
