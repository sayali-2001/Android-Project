package com.user.healthmart;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.content.Context;
import java.util.List;

public class RecommendedActivity extends AppCompatActivity {


//
//    private RecyclerView recyclerView;
//    private RecommendationsAdapter horizontalAdapter;
//    private RecommendationsAdapter verticalAdapter;
//    private List<Recommended> recommendedList;
//    private RecyclerView recyclerViewHorizontal;
//    private RecyclerView recyclerViewVertical;
//
//    public RecommendedActivity(SearchActivity searchActivity, RecyclerView recyclerViewRecommendations) {
//    }
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search); // Assuming this is your layout
//
//        // Initialize the RecyclerViews
//        recyclerViewHorizontal = findViewById(R.id.recyclerViewHorizontal);
//        recyclerViewVertical = findViewById(R.id.recyclerViewVertical);
//
//        // Set up the LayoutManager for horizontal and vertical recyclers
//        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//
//        recyclerViewHorizontal.setLayoutManager(horizontalLayoutManager);
//        recyclerViewVertical.setLayoutManager(verticalLayoutManager);
//
//        // Initialize the list of recommended items
//        recommendedList = new ArrayList<>();
//        recommendedList.add(new Recommended("Surgical Gloves for Hospital", "Dotcom Pharma", "Andheri East, Mumbai", "₹180 /Box", R.drawable.gloves));
//        recommendedList.add(new Recommended("Cheatle Forceps", "Dotcom Pharma", "Andheri East, Mumbai", "₹270 /Piece", R.drawable.cheatle_forceps));
//        recommendedList.add(new Recommended("Medical Torch", "DotCom Pharma", "Pune", "₹249", R.drawable.medical_torch));
//        recommendedList.add(new Recommended("Weight", "DotCom Pharma", "Bangalore", "₹1000", R.drawable.weight));
//
//        // Set up adapters for both RecyclerViews
//        horizontalAdapter = new RecommendationsAdapter(recommendedList);
//        verticalAdapter = new RecommendationsAdapter(recommendedList);
//
//        // Attach adapters to their respective RecyclerViews
//        recyclerViewHorizontal.setAdapter(horizontalAdapter);
//        recyclerViewVertical.setAdapter(verticalAdapter);
//    }
//
//    // Method to add a new recommended item
//    public void addRecommendedItem(Recommended item) {
//        recommendedList.add(item);
//        horizontalAdapter.notifyItemInserted(recommendedList.size() - 1);
//        verticalAdapter.notifyItemInserted(recommendedList.size() - 1);
//    }
    }

