package com.user.healthmart;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartItems;
   TextView  cartItemCountView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
//        setContentView(R.layout.activity_search);

        // Initialize RecyclerView
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
//        cartItemCountView = findViewById(R.id.cartItemCount);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the cart items from SharedPreferences
        cartItems =  new ArrayList<>();  // Initialize cartItems list

        // Initialize the adapter with the retrieved cart items
        cartAdapter = new CartAdapter(cartItems, new CartAdapter.OnItemRemovedListener() {
            @Override
            public void onItemRemoved(int position) {
                removeItemFromCart(position);
            }

            @Override
            public void onCartUpdated(int size) {

            }
        });
        cartRecyclerView.setAdapter(cartAdapter);

        // Load the cart data for the first time
        loadCartData();
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Reload the cart items when the activity resumes
        loadCartData();
    }

    // Method to load cart data from SharedPreferences
    private void loadCartData() {
        // Clear the current cart items
        cartItems.clear();

        // Retrieve the cart items from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
        int cartItemCount = sharedPreferences.getInt("cartItemCount", 0);

        for (int i = 1; i <= cartItemCount; i++) {
            String productName = sharedPreferences.getString("product_" + i + "_name", "Unknown");
            String productPrice = sharedPreferences.getString("product_" + i + "_price", "₹0");

            // Get the image URI as a string
            String imageUriString = sharedPreferences.getString("product_" + i + "_imageUri", null);
            Uri productImageUri = null;

            // Convert the string back to a Uri object if it's valid
            if (imageUriString != null && !imageUriString.equals("default_image_uri")) {
                productImageUri = Uri.parse(imageUriString);
            } else {
                // Optionally set a default image URI if it's null
                productImageUri = Uri.parse("android.resource://com.user.healthmart/drawable/img_placeholder");
            }

            // Add the product to the list
            cartItems.add(new Product(productName, productPrice, productImageUri));
        }

        // Notify the adapter that the data has changed
        cartAdapter.notifyDataSetChanged();

        // Update the cart badge
        updateCartBadge();
    }


//    // Method to retrieve the cart items from SharedPreferences
//    private List<Product> retrieveCartItems() {
//        List<Product> cartItems = new ArrayList<>();
//        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
//        int cartItemCount = sharedPreferences.getInt("cartItemCount", 0);
//
////        for (int i = 1; i <= cartItemCount; i++) {
////            String productName = sharedPreferences.getString("product_" + i + "_name", "Unknown");
////            String productPrice = sharedPreferences.getString("product_" + i + "_price", "₹0");
////            String imageUriString = sharedPreferences.getString("product_" + i + "_imageUri", null);
////            // Check if the image URI is valid
////            Uri productImageUri;
////            if (imageUriString != null && !imageUriString.isEmpty() && !imageUriString.equals("default_image_uri")) {
////                productImageUri = Uri.parse(imageUriString);  // Convert the string back to Uri
////            } else {
////                productImageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.img_placeholder); // Default placeholder image
////            }
////
////            cartItems.add(new Product(productName, productPrice, productImageUri));
////        }
////
////        return cartItems;
//        for (int i = 1; i <= cartItemCount; i++) {
//            String productName = sharedPreferences.getString("product_" + i + "_name", "Unknown");
//            String productPrice = sharedPreferences.getString("product_" + i + "_price", "₹0");
//
//            // Get the image URI as a string
//            String imageUriString = sharedPreferences.getString("product_" + i + "_imageUri", null);
//
//            Uri productImageUri = null;  // Default value for productImageUri
//
//            // Check if the imageUriString is null before parsing
//            if (imageUriString != null  && !imageUriString.equals("default_image_uri")) {
//               // Convert the string back to a Uri object
//                productImageUri = Uri.parse(imageUriString);
//            } else {
//                // Optionally set a default image URI if it's null
//                 productImageUri = Uri.parse("android.resource://com.user.healthmart/drawable/img_placeholder");
//            }
//
//            // Add the product to the list, with the productImageUri possibly being null
//            cartItems.add(new Product(productName, productPrice, productImageUri));
//        }
//
//        return cartItems;
//    }

    private void removeItemFromCart(int position){
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);

            // Save updated cart back to SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();



            // Clear all previous cart data before saving the updated one
            editor.clear();

//            editor.putInt("cartItemCount", cartItems.size());

            // Update the remaining cart data without clearing everything
            for (int i = 0; i < cartItems.size(); i++) {
                editor.putString("product_" + (i + 1) + "_name", cartItems.get(i).getName());
                editor.putString("product_" + (i + 1) + "_price", String.valueOf(cartItems.get(i).getPrice()));
                Uri imageUri = cartItems.get(i).getImageUri();
                if (imageUri != null) {
                    editor.putString("product_" + (i + 1) + "_imageUri", imageUri.toString());
                } else {
                    editor.putString("product_" + (i + 1) + "_imageUri", "default_image_uri");
                }
            }
            editor.apply();

            cartAdapter.notifyItemRemoved(position);
            cartAdapter.notifyItemRangeChanged(position, cartItems.size());

            updateCartBadge();
        }
    }


    private void updateCartBadge() {
        if (cartItems.size() == 0) {
            // Hide badge if cart is empty
            cartItemCountView.setVisibility(View.GONE);
        } else {
            // Show updated item count in the badge
            cartItemCountView.setText(String.valueOf(cartItems.size()));
            cartItemCountView.setVisibility(View.VISIBLE);
        }
    }
}
