package com.kalpana.prasun.message;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

public class Content extends AppCompatActivity {
    private AppCompatActivity activity = Content.this;
    Context context = Content.this;
    private RecyclerView recyclerViewContent;
    private ArrayList<Data> listData;
    private ContentRecyclerAdapter contentRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    SearchView searchBox;
    String message;
    private ArrayList<Data> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        recyclerViewContent = (RecyclerView)findViewById(R.id.recyclerViewContent);

        listData = new ArrayList<>();
        contentRecyclerAdapter = new ContentRecyclerAdapter(listData, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewContent.setLayoutManager(mLayoutManager);
        recyclerViewContent.setItemAnimator(new DefaultItemAnimator());
        recyclerViewContent.setHasFixedSize(true);
        recyclerViewContent.setAdapter(contentRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        getDataFromSQLite();
        Intent i2 = getIntent();
        //message = i2.getExtras().getString("MESSAGE");

    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listData.clear();
                listData.addAll(databaseHelper. getAllBeneficiary());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                contentRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
