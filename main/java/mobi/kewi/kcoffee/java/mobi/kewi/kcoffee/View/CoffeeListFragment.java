package mobi.kewi.kcoffee.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import mobi.kewi.kcoffee.ItemClickSupport;
import mobi.kewi.kcoffee.Model.Beverage;
import mobi.kewi.kcoffee.R;

/**
 * Created by Kevin on 31/01/17.
 */

public class CoffeeListFragment extends Fragment {

    private static final String ARG_PAGE = "ARG_PAGE";
    private static final String FIREBASE_URL = "https://kcoffee-2207c.firebaseio.com/";
    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    private Firebase mFirebaseRef;
    private ArrayList<Beverage> mBeverageItems;
    private ArrayList<String> mBeverageKeys;
    private List<Beverage> mBeverages;
    private boolean mTwoPane;
    private View rootView;
    private int cup;

    public CoffeeAdapter mCoffeeAdapter;
    FragmentActivity activity;
    private int itemCount;

    public static CoffeeListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CoffeeListFragment fragment = new CoffeeListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        handleInstanceState(savedInstanceState);
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

            final RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.beverages_list);
            mRecyclerView.setHasFixedSize(true);
            mCoffeeAdapter = new CoffeeAdapter(this, mFirebaseRef, Beverage.class, mBeverageItems, mBeverageKeys);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mCoffeeAdapter);


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

                        Beverage beverage = mBeverageItems.get(position);

                        intent.putExtra("type", beverage.getType());
                        intent.putExtra("price", beverage.getPrice());
                        intent.putExtra("info", beverage.getInfo());
                        intent.putExtra("imageurl", beverage.getImageurl());
                        startActivity(intent);
                    }
                }
            });

                   /* for ( v : itemCount)

                    switch (itemCount) {
                        case 0: cup++;
                    Toast.makeText(getActivity(), "Latte x "+cup+" cup", Toast.LENGTH_SHORT).show();

                            break;
                        case 2: cup++;
                            Toast.makeText(getActivity(), "Cappuccino x "+cup+" cup", Toast.LENGTH_SHORT).show();

                            break;
                        case 3: cup++;
                            Toast.makeText(getActivity(), "Flat White x"+cup+" cup", Toast.LENGTH_SHORT).show();

                            break;
                        case 4: cup++;
                            Toast.makeText(getActivity(), "Tea x "+cup+" cup", Toast.LENGTH_SHORT).show();

                            break;
                        case 5: cup++;
                            Toast.makeText(getActivity(), "Espresso x "+cup+" cup", Toast.LENGTH_SHORT).show();

                            break;
                        default:
                            break;
                    }*/

        }
        return rootView;
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

    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mBeverageItems = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mBeverageKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mBeverageItems = new ArrayList<>();
            mBeverageKeys = new ArrayList<>();
        }
    }
    // Saving the list of items and keys of the items on rotation
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mCoffeeAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mCoffeeAdapter.getKeys());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mCoffeeAdapter.destroy();
    }

}
