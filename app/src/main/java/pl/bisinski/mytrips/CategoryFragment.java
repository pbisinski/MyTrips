package pl.bisinski.mytrips;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.BtnAddTrip)
    Button mBtnAddTrip;

    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View fragment;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDataChangeEvent(DataChangeEvent event) {
        mAdapter.setList(event.trips);
        mAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, fragment);

        mBtnAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DaoSession daoSession = ((AppController) getActivity().getApplication()).getDaoSession();
                TripDao tripDao = daoSession.getTripDao();
                Trip trip = new Trip();
                trip.setName(UUID.randomUUID().toString());
                tripDao.insert(trip);
                List<Trip> trips = tripDao.loadAll();
                tripDao.detachAll();
                EventBus.getDefault().post(new DataChangeEvent(trips));
            }
        });

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        DaoSession daoSession = ((AppController) getActivity().getApplication()).getDaoSession();
        TripDao tripDao = daoSession.getTripDao();
        List<Trip> trips = tripDao.loadAll();
        tripDao.detachAll();

        mAdapter = new RecyclerAdapter(trips);
        mRecyclerView.setAdapter(mAdapter);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public class DataChangeEvent {
        public final List<Trip> trips;

        public DataChangeEvent(List<Trip> trips) {
            this.trips = trips;
        }
    }
}
