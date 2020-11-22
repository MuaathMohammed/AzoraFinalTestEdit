package com.example.azorafinaltest.ui.DataBaseManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.azorafinaltest.R;
import com.example.azorafinaltest.ui.LocalData.DBHelper;
import com.example.azorafinaltest.ui.Model.UserItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DataBaseManager extends Fragment {
    TextView u1;
    TextView u2;
    ImageView im1, im2;
    public static ArrayList<UserItems> userItems;
DBHelper db;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.database_fragment, container, false);
        UserItems information = new UserItems();
        db=new DBHelper(getContext());
        information.setUser("naveenkkkk");
        information.setImage("https://i.redd.it/0h2gm1ix6p501.jpg");
        if (new DBHelper(getActivity()).insert(information)&&new DBHelper(getActivity()).insertImage(information)) {
                Log.v(TAG, "save ok.");
        } else {
            Log.v(TAG, "save failed.");
        }
        userItems = new ArrayList<UserItems>();
        userItems.addAll(db.getAll());
        u1 = root.findViewById(R.id.Uid);
        u2 = root.findViewById(R.id.Uid1);
        im1 = root.findViewById(R.id.uimage);
        im2 = root.findViewById(R.id.uimage1);
        u1.setText(userItems.get(0).getUser());
        u2.setText(userItems.get(5).getUser());
        Picasso.get().load(userItems.get(0).getImage()).into(im1);
        Picasso.get().load(userItems.get(5).getImage()).into(im2);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}