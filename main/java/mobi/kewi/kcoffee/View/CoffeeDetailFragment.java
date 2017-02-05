package mobi.kewi.kcoffee.View;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import mobi.kewi.kcoffee.R;

import static mobi.kewi.kcoffee.R.id.beverage_name;

/**
 * Created by Kevin on 31/01/17.
 */
public class CoffeeDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "ARG_ITEM_ID";

    private String beveragePrice;
    private String beverageInfo;
    private String beverageImage;
    private String beverageName;

    private int cup;
    private int cost;
    private double total;

    public Resources res;

    View rootView;

    /**
     * Create a new instance of CoffeeDetailFragment, initialized to
     * show the text at 'index'.
     */
    public static CoffeeDetailFragment newInstance(int index) {
        CoffeeDetailFragment f = new CoffeeDetailFragment();

        // Supply index input as an argument.
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_ITEM_ID, index);
        f.setArguments(arguments);

        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

           if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.activity_beverage_detail,
                    container, false);

            Intent intent = getActivity().getIntent();
               beverageName = intent.getStringExtra("type");

               ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // CollapsingToolbar displaying the beverage name
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.beverage_detail_toolbar_layout);
            collapsingToolbarLayout.setTitle(beverageName);
            collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppTheme);
            collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
            collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

               beverageInfo = intent.getStringExtra("info");
               beveragePrice = intent.getStringExtra("price");
               beverageImage = intent.getStringExtra("imageurl");

             final Double cost = Double.parseDouble(beveragePrice);
        // Define views and set the elements of the details page
               TextView beverage_name = (TextView) rootView.findViewById(R.id.beverage_name);
               TextView beverage_info = (TextView) rootView.findViewById(R.id.beverage_info);
               TextView beverage_price = (TextView) rootView.findViewById(R.id.beverage_price);
               ImageView beverage_image = (ImageView) rootView.findViewById(R.id.beverage_image);

               beverage_info.setText(beverageInfo);
               beverage_price.setText(beveragePrice);
               beverage_name.setText(beverageName);
//Set beverage event image from Picasso
               String url = beverageImage;
               Picasso.with(getContext()).load(url).into(beverage_image);


               Button orderButton = (Button) rootView.findViewById(R.id.confirm);
               orderButton.setOnClickListener(new OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       //Send orders to basket
                       Toast.makeText(getActivity(), "Order placed", Toast.LENGTH_SHORT).show();
                   }
               });

        //Floating action button allows user to order beverage
            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.beverage_detail_fab);

            assert fab != null;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cup++;

                    Toast.makeText(getActivity(), cup+" cup", Toast.LENGTH_SHORT).show();
                    total = cup * cost;
                    beverageTotal();
                }

            });
        }
        return rootView;
    }

    private void beverageTotal() {
        TextView order_total = (TextView) rootView.findViewById(R.id.total);
        String orderTotal = "Total: £"+total;
        order_total.setText(orderTotal);
    }

}
