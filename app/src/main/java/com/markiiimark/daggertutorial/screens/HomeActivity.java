package com.markiiimark.daggertutorial.screens;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


import com.markiiimark.daggertutorial.GithubApplication;
import com.markiiimark.daggertutorial.R;
import com.markiiimark.daggertutorial.models.GithubRepo;
import com.markiiimark.daggertutorial.network.GithubService;
import com.markiiimark.daggertutorial.screens.home.AdapterRepos;
import com.squareup.picasso.Picasso;


import java.util.List;

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

    Picasso picasso;

    GithubService githubService;

    AdapterRepos adapterRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        githubService = GithubApplication.get(this).getGithubService();
        picasso = GithubApplication.get(this).getPicasso();

        adapterRepos = new AdapterRepos(this, picasso);
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
        if(reposCall != null) {  reposCall.cancel();  }
    }

}
