package com.markiiimark.daggertutorial.screens.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.markiiimark.daggertutorial.models.GithubRepo;
import com.markiiimark.daggertutorial.screens.HomeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MarkiiimarK on 12/4/16.
 */

public class AdapterRepos extends BaseAdapter {

    private final List<GithubRepo> repoList = new ArrayList<>(0);
    private final Context context;
    private final Picasso picasso;

    // Because of injection, we were able to reduce couple lines on HomeActiviy-level
    @Inject
    public AdapterRepos(HomeActivity context, Picasso picasso ) {
        this.context = context;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return repoList.size();
    }

    @Override
    public GithubRepo getItem(int pos) {
        return repoList.get(pos);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int pos) {
        return repoList.get(pos).id;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        RepoListItem repoListItem;

        if (convertView == null) {
            repoListItem = new RepoListItem(context, picasso);
        } else {
            repoListItem = RepoListItem.class.cast(convertView);
        }

        repoListItem.setRepo(repoList.get(pos));
        return repoListItem;

    }

    public void swapData(Collection<GithubRepo> githubRepos) {
        repoList.clear();
        if(githubRepos != null) {
            repoList.addAll(githubRepos);
        }
        notifyDataSetChanged();
    }
}
