package olgababeeva.homework.elegion.myapplication;

import android.app.SearchManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import olgababeeva.homework.elegion.myapplication.model.DaoMaster;
import olgababeeva.homework.elegion.myapplication.model.DaoSession;
import olgababeeva.homework.elegion.myapplication.model.DatabaseOpenHelper;
import olgababeeva.homework.elegion.myapplication.model.Recipe;
import olgababeeva.homework.elegion.myapplication.model.RecipeDao;
import olgababeeva.homework.elegion.myapplication.model.Relations;
import olgababeeva.homework.elegion.myapplication.model.RelationsDao;


public class SearchableActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        List<String> strings = new ArrayList<>();
        strings.add("at least i can do that");
        mAdapter = new MyAdapter(strings);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);


    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("got query", query);
            doMySearch(query);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }


    private void doMySearch(String query) {
        Log.d("recyclerview", "i was in doMySearch");
        DatabaseOpenHelper helper = new DatabaseOpenHelper(this, "recipe.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        RecipeDao recipeDao = daoSession.getRecipeDao();
        RelationsDao relationsDao = daoSession.getRelationsDao();

        List<Relations> searched = relationsDao.queryBuilder()
                .where(RelationsDao.Properties.Ingredient_id.eq(Integer.valueOf(query)))
                .list();

        Log.d("searched", "found " + searched.size());

        List<Recipe> listIng = recipeDao.loadAll();
        List<Recipe> searchedRecipes = new ArrayList<>();

        for (Recipe r : listIng) {
            for (Relations rel : searched) {
                if (r.getId() == (long) rel.getRecipe_id()) {
                    searchedRecipes.add(r);
                }
            }

        }


        Log.d("recyclerview", "i found " + searchedRecipes.size());

        List<String> strings = new ArrayList<>();

        for (Recipe r : searchedRecipes) {
            strings.add(r.getText());
            int i = 0;
            Log.d("recyclerview", strings.get(i++));
        }

        mAdapter.setData(strings);
        mRecyclerView.setAdapter(mAdapter);

    }
}
