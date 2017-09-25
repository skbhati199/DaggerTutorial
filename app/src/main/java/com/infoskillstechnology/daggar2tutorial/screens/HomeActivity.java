package com.infoskillstechnology.daggar2tutorial.screens;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.infoskillstechnology.daggar2tutorial.GithubApplication;
import com.infoskillstechnology.daggar2tutorial.R;
import com.infoskillstechnology.daggar2tutorial.models.GithubRepo;
import com.infoskillstechnology.daggar2tutorial.network.GithubService;
import com.infoskillstechnology.daggar2tutorial.screens.home.AdapterRepos;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MarkiiimarK on 12/4/16.
 */

public class HomeActivity extends LifecycleLogginActivity {

    @BindView(R.id.repo_home_list)
    ListView listView;

    Call<List<GithubRepo>> reposCall;

    @Inject
    GithubService githubService;

    @Inject
    AdapterRepos adapterRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        HomeActivityComponent homeActivityComponent = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this))
                .githubApplicationComponent(GithubApplication.get(this).component())
                .build();
        homeActivityComponent.injectHomeActivity(this);
        listView.setAdapter(adapterRepos);

        reposCall = githubService.getAllRepos();
        reposCall.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                adapterRepos.swapData(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error getting repos " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reposCall != null) {
            reposCall.cancel();
        }
    }


}
