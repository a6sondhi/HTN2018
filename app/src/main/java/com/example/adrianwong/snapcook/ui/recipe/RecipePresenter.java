package com.example.adrianwong.snapcook.ui.recipe;

import com.example.adrianwong.snapcook.adapter.RecipeAdapter;
import com.example.adrianwong.snapcook.model.Recipe;
import com.example.adrianwong.snapcook.network.SnapcookRestAdapter;
import com.example.adrianwong.snapcook.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecipePresenter extends BasePresenter<RecipeActivity> {

    private RecipeAdapter recipeAdapter;
    private SnapcookRestAdapter snapcookRestAdapter;
    private CompositeDisposable disposable;

    @Inject
    public RecipePresenter(SnapcookRestAdapter snapcookRestAdapter, RecipeAdapter recipeAdapter) {
        this.snapcookRestAdapter = snapcookRestAdapter;
        this.disposable = new CompositeDisposable();
        this.recipeAdapter = recipeAdapter;
    }

    public void getRecipeList(String key, String accept, String ingredients, int number, int ranking) {
        disposable.add(snapcookRestAdapter.getRecipeList(key, accept, ingredients, number, ranking)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Recipe>>() {
                    @Override
                    public void onNext(List<Recipe> recipes) {
                        //recipeAdapter.set
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

}
