package mobi.kewi.kcoffee.View;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.client.annotations.Nullable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mobi.kewi.kcoffee.FirebaseRecyclerAdapter;
import mobi.kewi.kcoffee.Model.Beverage;
import mobi.kewi.kcoffee.R;
import mobi.kewi.kcoffee.View.CoffeeAdapter.ViewHolder;

/**
 * Created by Kevin on 31/01/17.
 */

public class CoffeeAdapter extends FirebaseRecyclerAdapter<ViewHolder,Beverage> {

    private final Collection<Beverage> mCoffee;
    public Resources res;
    private String url;
    public static List<Beverage> items = new ArrayList<>();

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView coffeeTitle;
        TextView coffeePrice;
        ImageView coffeeImage;

        public ViewHolder(View convertView) {
            super(convertView);
            coffeeTitle = (TextView) convertView.findViewById(R.id.coffee_title);
            coffeePrice = (TextView)  convertView.findViewById(R.id.coffee_price);
            coffeeImage = (ImageView) convertView.findViewById(R.id.coffee_image);
        }
    }
    public CoffeeAdapter(Fragment fragment, Query query, Class<Beverage> itemClass, ArrayList<Beverage> items,  @Nullable ArrayList<String> keys) {
        super(fragment, query, itemClass, items, keys);

        CoffeeAdapter.items = items;
        mCoffee = new ArrayList<>();
        items.addAll(mCoffee);
       }

    @Override
    public CoffeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coffee_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoffeeAdapter.ViewHolder holder, int position) {
        final Beverage item = getItem(position);

        Context context = holder.coffeeImage.getContext();
        holder.coffeeTitle.setText(item.getType());
        holder.coffeePrice.setText(item.getPrice());

        url = item.getImageurl();
        Picasso.with(context).load(url).into(holder.coffeeImage);
    }

    @Override protected void itemAdded(Beverage item, String key, int position) {

    }

    @Override protected void itemChanged(Beverage oldItem, Beverage newItem, String key, int position) {

    }

    @Override protected void itemRemoved(Beverage item, String key, int position) {

    }

    @Override protected void itemMoved(Beverage item, String key, int oldPosition, int newPosition) {

    }

}