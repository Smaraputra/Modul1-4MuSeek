package id.smaraputra.modul4progmob.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.smaraputra.modul4progmob.DBHandler;
import id.smaraputra.modul4progmob.adapter.UserAdapter;
import id.smaraputra.modul4progmob.R;
import id.smaraputra.modul4progmob.model.UserModel;

public class FragmentUserManagement extends Fragment {

    private DBHandler dbHandler;
    private ArrayList<UserModel> user=new ArrayList<>();
    private UserAdapter mAdapter;

    RadioGroup groupRecylerType;
    RadioButton ver, notver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_management, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView userView = (RecyclerView) view.findViewById(R.id.recylerviewuser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        userView.setLayoutManager(linearLayoutManager);
        userView.setHasFixedSize(true);
        dbHandler = new DBHandler(getContext());
        user = dbHandler.listUserVerified();
        mAdapter = new UserAdapter(getContext(), user);
        userView.setAdapter(mAdapter);

        groupRecylerType = (RadioGroup) view.findViewById(R.id.ver_group);
        groupRecylerType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String id=getResources().getResourceEntryName(i);
                if(id.equals("verifed")){
                    dbHandler = new DBHandler(getContext());
                    user = dbHandler.listUserVerified();
                    mAdapter = new UserAdapter(getContext(), user);
                    userView.setAdapter(mAdapter);
                }else if(id.equals("notverifed")){
                    dbHandler = new DBHandler(getContext());
                    user = dbHandler.listUserNotVerified();
                    mAdapter = new UserAdapter(getContext(), user);
                    userView.setAdapter(mAdapter);
                }
            }
        });
    }

}