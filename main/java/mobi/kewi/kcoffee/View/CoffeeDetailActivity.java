package mobi.kewi.kcoffee.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import mobi.kewi.kcoffee.R;

/**
 * Created by Kevin on 31/01/17.
 */
public class CoffeeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverage_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment mCoffeeDetailFragment = new CoffeeDetailFragment();
        fragmentTransaction.add(R.id.beverage_details_container, mCoffeeDetailFragment);
        fragmentTransaction.commit();
    }
}