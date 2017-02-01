package mobi.kewi.kcoffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Kevin on 31/01/17.
 */

public class CoffeeListFragment extends Fragment {

    private static final String FIREBASE_URL = "https://kcoffee-2207c.firebaseio.com/";
    private Firebase mFirebaseRef;
    public CoffeeAdapter mCoffeeAdapter;
    private ArrayList<Beverage> mAdapterItems;
    private ArrayList<String> mAdapterKeys;

    private View rootView;
    private boolean mTwoPane;

    private FragmentActivity activity;
    private int cup;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        setupFirebase();
        activity = getActivity();

        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.activity_main,
                    container, false);

            View recyclerView = activity.findViewById(R.id.beverage_list);
            assert recyclerView != null;

            if (activity.findViewById(R.id.beverage_details_container) != null) {
                // The detail container view will be present only in the
                // large-screen layouts (res/values-w900dp).
                // If this view is present, then the
                // activity should be in two-pane mode.
                mTwoPane = true;
            }

            RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.beverages_list);
            mRecyclerView.setHasFixedSize(true);
            mCoffeeAdapter = new CoffeeAdapter(this, mFirebaseRef, Beverage.class, mAdapterItems, mAdapterKeys);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mCoffeeAdapter);


            // Order button
            ImageButton plus_button = (ImageButton) rootView.findViewById(R.id.plus_button);
            plus_button.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    // add beverage
                    addCup(arg0);
                }
            });

            final Intent intent = new Intent(activity, CoffeeDetailActivity.class);

            ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                    if (mTwoPane) {
                        Bundle arguments = new Bundle();

                        arguments.putString(CoffeeDetailFragment.ARG_ITEM_ID, "index");
                        CoffeeDetailFragment fragment = new CoffeeDetailFragment();
                        fragment.setArguments(arguments);
                        activity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.beverage_details_container, fragment)
                                .commit();
                    } else {

                        Beverage beverage = mAdapterItems.get(position);

                        intent.putExtra("type", beverage.getType());
                        intent.putExtra("price", beverage.getPrice());
                        intent.putExtra("info", beverage.getInfo());
                        startActivity(intent);
                    }
                }
            });
        }
        return rootView;
    }

    private void addCup(View arg0) {
        cup++;
    }

    private void setupFirebase() {
        mFirebaseRef = new Firebase(FIREBASE_URL).child("beverages");

        mFirebaseRef.addValueEventListener(new ValueEventListener() {@Override
        public void onDataChange(DataSnapshot snapshot) {
        }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mCoffeeAdapter.destroy();
    }
}
