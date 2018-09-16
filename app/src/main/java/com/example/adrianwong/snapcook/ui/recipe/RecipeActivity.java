package com.example.adrianwong.snapcook.ui.recipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.adrianwong.snapcook.MyApplication;
import com.example.adrianwong.snapcook.R;
import com.example.adrianwong.snapcook.adapter.RecipeAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity implements RecipeView {

    @BindView(R.id.recipe_toolbar) Toolbar toolbar;
    @BindView(R.id.recipe_recycler_view) RecyclerView recyclerView;

    @Inject RecipePresenter recipePresenter;
    @Inject RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        MyApplication.getApp().getAppComponent().inject(this);

        recipePresenter.attachView(this);

        Intent intent = getIntent();
        String ingredients = intent.getStringExtra("INGREDIENTS");
        Log.d("RecipeActivity", ingredients);

        setupGui();
    }

    private void setupGui() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Available Recipes");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
    }
}
