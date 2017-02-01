package mobi.kewi.kcoffee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kevin on 30/01/17.
 */

public class Beverage {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("info")
        @Expose
        private String info;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }