package pl.bisinski.mytrips;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View fragment;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, fragment);

        DaoSession daoSession = ((AppController)getActivity().getApplication()).getDaoSession();
        TripDao tripDao = daoSession.getTripDao();

        //tripDao.deleteAll();

        Trip trip1 = new Trip();
        trip1.setName("Szwajcaria");
        Trip trip2 = new Trip();
        trip2.setName("Anglia");

        tripDao.insert(trip1);
        Log.d("greenDao", trip1.getId().toString());
        tripDao.insert(trip2);
        Log.d("greenDao", trip2.getId().toString());

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Trip> trips = tripDao.loadAll();

        mAdapter = new RecyclerAdapter(trips);
        mRecyclerView.setAdapter(mAdapter);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
