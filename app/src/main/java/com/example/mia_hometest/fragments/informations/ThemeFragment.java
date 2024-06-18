package com.example.mia_hometest.fragments.informations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mia_hometest.UserMainActivity;
import com.example.mia_hometest.R;
import com.example.mia_hometest.common.ThemeItem;
import com.example.mia_hometest.common.ThemeListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThemeFragment extends Fragment implements ThemeListAdapter.OnThemeClickListener{
    private final String TAG = ThemeFragment.class.getSimpleName();
    private Context mContext = null;
    private ImageView mGoback = null;
    private ThemeListAdapter<ThemeItem> mAdapter;
    private List<ThemeItem> mItemList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Intent mIntent;
//    private final String red = "com.android.action.red";
//    private final String orange = "com.android.action.orange";
//    private final String violet = "com.android.action.violet";

    public ThemeFragment (Context context) {
        mContext = context;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, " ThemeFragment onCreateView: ");
        View view = inflater.inflate(R.layout.theme, container, false);

        mGoback = view.findViewById(R.id.back);
        mAdapter = new ThemeListAdapter<>(mContext, this, "theme");
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        mGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 뒤로가기 눌렀다...");
                ((UserMainActivity) getActivity()).goBack();
            }
        });
        setThemeItems();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onThemeClick(int position) {
        switch (position) {
            case 0:
                sendIntent("red");
                break;
            case 1:
                sendIntent("orange");
                break;
            case 2:
                sendIntent("yellow");
                break;
            case 3:
                sendIntent("green");
                break;
            case 4:
                sendIntent("blue");
                break;
            case 5:
                sendIntent("violet");
                break;
            case 6:
                sendIntent("gray");
                break;
            default:
                break;
        }
    }

    private void sendIntent(String color) {
        Log.d(TAG, "sendIntent: whats color ..... " + color);
        mIntent = new Intent(mContext, UserMainActivity.class);
        mIntent.putExtra("color", color);
        startActivity(mIntent);
    }

    private void setThemeItems() {
        String[] titles = getResources().getStringArray(R.array.theme_color_title);
        int[] colors = {
                R.color.red,
                R.color.orange,
                R.color.yellow,
                R.color.green,
                R.color.blue,
                R.color.violet,
                R.color.gray
        };

        for (int i=0; i<titles.length; i++) {
            ThemeItem item = new ThemeItem(titles[i], colors[i]);
            mItemList.add(item);
        }

        mAdapter.setItems(mItemList);
    }
}
