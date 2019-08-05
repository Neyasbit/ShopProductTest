package com.example.shopprotucttest.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.shopprotucttest.R;
import com.example.shopprotucttest.Utils;
import com.example.shopprotucttest.adapter.ExpandableListAdapter;
import com.example.shopprotucttest.adapter.RecyclerViewHomeAdapter;
import com.example.shopprotucttest.adapter.RecyclerViewOnScroll;
import com.example.shopprotucttest.model.ExpandedMenuModel;
import com.example.shopprotucttest.model.ShopData;
import com.example.shopprotucttest.view.detail.DetailActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationmenu)
    ExpandableListView expandableListView;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout refreshLayout;

    ExpandableListAdapter menuAdapter;
    private HashMap<ExpandedMenuModel, List<String>> listDataChild = new HashMap<>();
    private List<ExpandedMenuModel> listDataHeader = new ArrayList<>();

    private HomePresenter presenter;
    private RecyclerViewHomeAdapter homeAdapter;
    private List<ShopData.Item> items = new ArrayList<>();
    private static int page = 1;
    public static boolean isLoading;
    public static void setIsLoading(boolean isLoading) {
        HomeActivity.isLoading = isLoading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


        presenter = new HomePresenter(this);
        isLoading = true;
        presenter.getData(page);
        homeAdapter = new RecyclerViewHomeAdapter(items, this);
        recyclerView.setAdapter(homeAdapter);

        presenter.getDataListMenu();

        expandableListView.setAdapter(menuAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                items.clear();
                                presenter.getDetailCategories("book", 1);
                                drawerLayout.closeDrawers();
                                break;
                            case 1:
                                items.clear();
                                presenter.getDetailCategories("book", 2);
                                drawerLayout.closeDrawers();
                                break;
                            case 2:
                                items.clear();
                                presenter.getDetailCategories("book", 3);
                                drawerLayout.closeDrawers();
                                break;
                        }
                        break;
                    case 1:
                        break;
                }
                return false;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        items.clear();
                        presenter.getCategories("book");
                        break;
                    case 1:
                        items.clear();
                        presenter.getCategories("cd");
                        break;
                }
                return false;
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerViewOnScroll() {
            @Override
            public void loadMore() {
                if (isLoading) {
                    if (page < 3) {
                        page += 1;
                        presenter.getData(page);
                        isLoading = false;
                        homeAdapter.setItemList(items);
                    }
                }
            }
        });
        homeAdapter.setItemClickListener(new RecyclerViewHomeAdapter.ListItemClickListener() {
            @Override
            public void itemOnClick(View view, int position) {
                ShopData.Item item = items.get(position);
                Intent in = new Intent(HomeActivity.this, DetailActivity.class);
                in.putExtra("item", item);
                startActivity(in);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                presenter.getData(1);
                homeAdapter.setItemList(items);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void setDataList(List<ShopData.Item> itemList) {
        items.addAll(itemList);
        homeAdapter.setItemList(items);
    }
    @Override
    public void setListDataMenu(List<ExpandedMenuModel> plistDataHeader, HashMap<ExpandedMenuModel, List<String>> plistDataChild) {
        listDataHeader.addAll(plistDataHeader);
        listDataChild.putAll(plistDataChild);
        menuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableListView);
        menuAdapter.setListDataHeader(listDataHeader);
        menuAdapter.setListDataChild(listDataChild);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Tittle", message);
    }


}
