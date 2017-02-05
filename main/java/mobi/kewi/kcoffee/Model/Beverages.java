package mobi.kewi.kcoffee.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.List;

/**
 * Created by Kevin on 30/01/17.
 */

@Parcel
public class Beverages {
    @SerializedName("beverages")
    @Expose

    @ParcelPropertyConverter(Beverage.class)
    public List<Beverage> beverages = null;

    @ParcelPropertyConverter(Beverage.class)
    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

}
