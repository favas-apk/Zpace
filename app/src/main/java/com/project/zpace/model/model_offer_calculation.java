package com.project.zpace.model;

import com.project.zpace.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class model_offer_calculation {

    private float rate;

    private float offer_price;

    private int buy_qty;
    private int free_qty;

    private float free_percent;

    private String offer_end_date;

    private String offer_type = "";
    private Date offer_end_date_date, today_date_date,error_date;
    private String result_offer_price = "", result_rate = "", result_perc_off = "",result_buy_get="";

    public model_offer_calculation(float rate, float offer_price, int buy_qty, int free_qty, float free_percent, String offer_end_date) {
        this.rate = rate;
        this.offer_price = offer_price;
        this.buy_qty = buy_qty;
        this.free_qty = free_qty;
        this.free_percent = free_percent;
        this.offer_end_date = offer_end_date;


        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 1,1);

        error_date=calendar.getTime();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        try {
            offer_end_date_date = sdf.parse(offer_end_date);

        } catch (Exception e) {

            offer_end_date_date= error_date;
        }

        try {
            today_date_date = sdf.parse(Constants.getToday_date());
            //today_date_date = sdf.parse(Constants.getToday_date());
        } catch (Exception e) {

            today_date_date=error_date;

        }



            if ((offer_end_date_date.equals(today_date_date) || offer_end_date_date.after(today_date_date) ) && today_date_date !=error_date && offer_end_date_date != error_date  ) {
                //offer1
                if (offer_price > 0) {
                    this.offer_type = "A";
                    this.result_offer_price = "" + offer_price;
                    this.result_rate = "" + rate;
                    this.result_buy_get="";

                    BigDecimal rate_big = new BigDecimal(rate);
                    BigDecimal offer_big = new BigDecimal(offer_price);
                    BigDecimal diff_big = rate_big.subtract(offer_big);
                    BigDecimal offer_percent_big = diff_big.divide(rate_big, 3, RoundingMode.HALF_UP);
                    offer_percent_big = offer_percent_big.multiply(new BigDecimal("100.0"));

                    offer_percent_big = offer_percent_big.setScale(1, RoundingMode.HALF_UP);

                    this.result_perc_off = offer_percent_big.toPlainString() + "% off";


                }
//            offer2
                else if (buy_qty > 0 && free_qty > 0) {
                    this.offer_type = "B";
                    this.result_offer_price="";
                    this.result_rate = "" + rate;
                    this.result_buy_get="Buy " + buy_qty + " Get " + free_qty;
                    this.result_perc_off="";
//                this.val2 = "Buy " + buy_qty + " Get " + free_qty;
//                this.val3 = "";
                }
                //offer3
                else if (buy_qty > 0 && free_percent > 0) {
                    this.  offer_type = "C";
                    this.result_offer_price="";
                    this.result_rate = "" + rate;
                    this.result_buy_get="Buy " + buy_qty + " Get " + free_percent+"% off";
                    this.result_perc_off="";


                }
                else {
                    this.offer_type = "";
                    this.result_rate = "" + rate;
                    this.result_offer_price="";
                    this.result_perc_off="";
                    this.result_buy_get="";
                }

            } else {
                this.offer_type = "";
                this.result_rate = "" + rate;
                this.result_offer_price="";
                this.result_perc_off="";
                this.result_buy_get="";

            }




    }


    public String get_result_offer_price() {
        return this.result_offer_price;
    }

    public String get_result_rate() {
        return this.result_rate;
    }

    public String get_result_perc_off() {
        return this.result_perc_off;
    }

    public String get_result_buy_get()
    {
       return this.result_buy_get;
    }


    public String get_offer_type() {
        return this.offer_type;
    }

}
