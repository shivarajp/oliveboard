package com.oliveboardapp.oliveboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oliveboardapp.oliveboard.interfaces.GetExamsDdata;
import com.oliveboardapp.oliveboard.models.ExamsResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private GetExamsDdata mExamsDdata;
    private ProgressBar progressBar;
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;
    public ArrayList<String> mTabTitles = new ArrayList<>();
    public ArrayList<String> mTabData = new ArrayList<>();
    private RestAdapter restAdapter;
    public String API = "http://android.oliveboard.in/hiring";
    public List<List<String>> responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();
        populate();
    }

    private void initInstances() {
        setupTablayout();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API)
                .build();
        mExamsDdata = restAdapter.create(GetExamsDdata.class);
    }


    private void populate() {
        mExamsDdata.getData(new Callback<ExamsResponseModel>() {
            @Override
            public void success(ExamsResponseModel examsResponseModel, Response response) {
                progressBar.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                responseData = examsResponseModel.getExams();
                for (int i = 0; i < responseData.size(); i++) {
                    mTabTitles.add(responseData.get(i).get(0));
                    mTabData.add(responseData.get(i).get(1));
                }
                fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

                    @Override
                    public int getCount() {
                        return mTabTitles.size();
                    }

                    @Override
                    public Fragment getItem(int position) {
                        ExamDataFragment fragment = new ExamDataFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", mTabData.get(position).toString());
                        fragment.setArguments(bundle);
                        return fragment;
                    }

                    @Override
                    public CharSequence getPageTitle(int position) {
                        return mTabTitles.get(position);
                    }
                };
                viewPager.setAdapter(fragmentStatePagerAdapter);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
                mTabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),"Something Went wrong, Please check you internet connection",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupTablayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
    }
}
