package mobi.kewi.kcoffee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Kevin on 31/01/17.
 */
public class CoffeeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverage_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.coffee_detail_toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment mCoffeeDetailFragment = new CoffeeDetailFragment();
        fragmentTransaction.add(R.id.beverage_details_container, mCoffeeDetailFragment);
        fragmentTransaction.commit();
    }
}