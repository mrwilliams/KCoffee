package mobi.kewi.kcoffee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kevin on 30/01/17.
 */

public class Beverages {
    @SerializedName("beverages")
    @Expose
    private List<Beverage> beverages = null;

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }
}
