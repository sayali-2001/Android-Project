package com.user.healthmart;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private ImageButton btnNotification;
    private ImageButton btnAddToCart;
    private ImageButton btnAccount;
//    private Button btnBuyNow;
    private LinearLayout linearLayoutPharmacy;
    private ViewPager2 viewPager;
    private List<Integer> images;
    private Handler handler;
    private Runnable updateRunnable;
    int cartItemCount = 0;
    TextView cartItemCountView;
    RecyclerView recommendedRecyclerView;
    Button addButton;

    ImageView img1,img2,img3,img4;
    TextView prdName1,prdName2,prdName3,prdName4;
    TextView cmpName1, cmpName2, cmpName3,cmpName4;
    TextView location1,location2,location3,location4;
    TextView price1,price2,price3,price4;
    Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        btnNotification = findViewById(R.id.btnNotification);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAccount = findViewById(R.id.btnAccount);

        prdName1 = findViewById(R.id.productName1);

        prdName2 = findViewById(R.id.productName2);
        prdName3 = findViewById(R.id.productName3);
        prdName4 = findViewById(R.id.productName4);

        cmpName1 = findViewById(R.id.companyName1);
        cmpName2 = findViewById(R.id.companyName2);
        cmpName3 = findViewById(R.id.companyName3);
        cmpName4 = findViewById(R.id.companyName4);

        location1 = findViewById(R.id.location1);
        location2 = findViewById(R.id.location2);
        location3 = findViewById(R.id.location3);
        location4 = findViewById(R.id.location4);

        price1 =  findViewById(R.id.productPrice1);
        price2 =  findViewById(R.id.productPrice2);
        price3 =  findViewById(R.id.productPrice3);
        price4 =  findViewById(R.id.productPrice4);

        btn1 = findViewById(R.id.getAddToCart1);
        btn2 = findViewById(R.id.getAddToCart2);
        btn3 = findViewById(R.id.getAddToCart3);
        btn4 = findViewById(R.id.getAddToCart4);









//         addButton = findViewById(R.id.getAddToCart);
         cartItemCountView = findViewById(R.id.cartItemCount);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

// Create the category list
        List<CategoriesItem> categories = new ArrayList<>();
        categories.add(new CategoriesItem("Category 1", R.drawable.stethoscope));
        categories.add(new CategoriesItem("Category 2", R.drawable.medical_mask));
        categories.add(new CategoriesItem("Category 2", R.drawable.medical_bed));
        categories.add(new CategoriesItem("Category 2", R.drawable.oximeter));
        categories.add(new CategoriesItem("Category 2", R.drawable.walker));
        categories.add(new CategoriesItem("Category 2", R.drawable.wheelchair));
        categories.add(new CategoriesItem("Category 2", R.drawable.medical_mask));



// Add more categories...

// Set the adapter
        CategoriesAdapter adapter = new CategoriesAdapter(categories);
        recyclerView.setAdapter(adapter);

        //Top Recommended Code
//        RecyclerView recommendedRecyclerView = findViewById(R.id.recyclerViewRecommended);
//        LinearLayoutManager recommendedLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recommendedRecyclerView.setLayoutManager(recommendedLayoutManager);

// Create a list of products
//        List<ReccomendedProduct> productList = new ArrayList<>();
//        productList.add(new ReccomendedProduct("Product 1", "Company 1", "Location 1", "$100", R.drawable.medical_torch));
//        productList.add(new ReccomendedProduct("Product 1", "Company 1", "Location 1", "$100", R.drawable.gloves));
// Add more products here

// Set up adapter
//        RecommendationsAdapter recommendationsAdapter = new RecommendationsAdapter(productList);
//        recommendedRecyclerView.setAdapter(recommendationsAdapter);
        // Initialize RecyclerView
//        recommendedRecyclerView = findViewById(R.id.recyclerViewRecommended);

//        loadData();
        // Load cart data and update badge
        updateCartBadge();
    setupListeners();

    }
    private void updateCartBadge() {
        // Retrieve the current cart item count from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
        cartItemCount = sharedPreferences.getInt("cartItemCount", 0);

        // Update the badge visibility and text
        if (cartItemCount > 0) {
            cartItemCountView.setText(String.valueOf(cartItemCount));
            cartItemCountView.setVisibility(View.VISIBLE);
        } else {
            cartItemCountView.setVisibility(View.GONE);
        }
    }
    private void loadData() {

        Log.d("RecyclerView", "Loading data started...");

        // Simulate data load (can be from a server, database, etc.)
        List<ReccomendedProduct> productList = new ArrayList<>();

        // After data load is complete
        productList.add(new ReccomendedProduct("Product 1", "Company 1", "Location 1", "$100", R.drawable.medical_torch));

        Log.d("RecyclerView", "Data loaded: " + productList.size() + " items");

        // Ensure RecyclerView adapter is set after data is available
        setupRecyclerView(productList);
    }
    private void setupRecyclerView(List<ReccomendedProduct> productList) {

        // Log the size of the list to make sure data is there
        Log.d("RecyclerView", "Product list size: " + productList.size());

//        Ensure there is data
        if(productList != null && !productList.isEmpty()){
            // Set Layout Manager
            LinearLayoutManager recommendedLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recommendedRecyclerView.setLayoutManager(recommendedLayoutManager);


            // Attach the adapter after loading the data
            RecommendationsAdapter recommendationsAdapter = new RecommendationsAdapter(productList);
            recommendedRecyclerView.setAdapter(recommendationsAdapter);

            Log.d("RecyclerView", "Adapter is set with " + productList.size() + " items");
        }
        else {
            Log.e("RecyclerView", "No data to display in RecyclerView");
        }
    }


    private void setupListeners() {


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search text changes here
                return false;
            }
        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle notification icon click here
            }
        });
//Add to cart button
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add to cart icon click here

                // Navigate to CartActivity when the cart icon is clicked
                Intent intent = new Intent(SearchActivity.this, CartActivity.class);
                startActivity(intent);

//                // Store the product details in SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("cartItemCount", cartItemCount);
//                editor.putString("product_" + cartItemCount + "_name", "Surgical Gloves"); // Example product name
//                editor.putString("product_" + cartItemCount + "_price", "₹180");
//                editor.apply();
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle account icon click here
            }
        });


        //Add Button on the carts
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the current cart item count from SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
                cartItemCount = sharedPreferences.getInt("cartItemCount", 0);
                // Increment the cart item count
                cartItemCount++;

                // Get the image from ImageView (product image)
                ImageView productImageView = findViewById(R.id.productImage1);
                Drawable drawable = productImageView.getDrawable();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (drawable instanceof BitmapDrawable) {
                    // Save image as bitmap
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    String imageUri = saveBitmapToInternalStorage(bitmap); // Call the method to save bitmap
                    editor.putString("product_" + cartItemCount + "_image_uri", imageUri);
                } else {
                    editor.putString("product_" + cartItemCount + "_image_uri", "default_image_uri");
                }

                // Save product name and price dynamically
                TextView productNameView = findViewById(R.id.productName1);
                String productName = productNameView.getText().toString();
                TextView productPriceView = findViewById(R.id.productPrice1);
                String productPrice = productPriceView.getText().toString();

                editor.putString("product_" + cartItemCount + "_name", productName);
                editor.putString("product_" + cartItemCount + "_price", productPrice);
                editor.putInt("cartItemCount", cartItemCount);
                editor.apply();

                // Update the badge on the cart icon with the new item count
                cartItemCountView.setText(String.valueOf(cartItemCount));
                cartItemCountView.setVisibility(View.VISIBLE); // Make sure it's visible
            }
        });

// Method to save bitmap to internal storage


    }
   
    private String saveBitmapToInternalStorage(Bitmap bitmap) {
//        // Create a unique file name for the image
//        String fileName = "product_image_" + System.currentTimeMillis() + ".png";
//
//        // Use the context to access internal storage
//        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//
//        // Create or get the directory to store the image
//        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//
//        // Create the image file
//        File imagePath = new File(directory, fileName);
//
//        FileOutputStream fos = null;
//        try {
//            // Open a file output stream to write the image data
//            fos = new FileOutputStream(imagePath);
//
//            // Compress the bitmap and save it to the file
//            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // Close the file output stream
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Return the absolute path of the saved image file
//        return imagePath.getAbsolutePath();
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // Path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create a file with a unique name
        File mypath = new File(directory, "product_" + System.currentTimeMillis() + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Compress the bitmap and save it to the output stream
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Return the path as a string
        return mypath.getAbsolutePath();
    }
}
