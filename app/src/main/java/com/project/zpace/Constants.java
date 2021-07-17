package com.project.zpace;

import com.project.zpace.interfac.Ad_Single_Interface;
import com.project.zpace.interfac.CatInterface;
import com.project.zpace.interfac.CategoriesActivityInterface;
import com.project.zpace.interfac.Fr_CartInterface;
import com.project.zpace.interfac.Fr_Dash_Interface;
import com.project.zpace.interfac.Fr_SearchBefore_Interface;
import com.project.zpace.interfac.Fr_Single_Interface;
import com.project.zpace.interfac.HomeInterface;
import com.project.zpace.interfac.PaymentActivityInterface;
import com.project.zpace.interfac.ProfileInterface;
import com.project.zpace.interfac.WishlistInterface;

public class Constants {

    private static String today_date="";

    public static String getToday_date() {
        return today_date;
    }

    public static void setToday_date(String today_date) {
        Constants.today_date = today_date;
    }

    public static String Add_an_del_ads="Add new delivery address";
    public static String Buy_Qty_Free_Qty="Buy Qty Free Qty";
    public static String Buy_Qty_Free_Percent="Buy Qty Free Percent";
    public static String Offer="Offer";
    public static String Today_Offer="Today Offer";

    public static String Parcel4="Parcel4";
    public static String slno="slno";
    private  static String search_word="";

    public static String getSearch_word() {
        return search_word;
    }

    public static void setSearch_word(String search_word) {
        Constants.search_word = search_word;
    }

    public static String Parcel3_Category="Parcel3_Category";
    public static String Parcel3_Sub1="Parcel3_Sub1";
    public static String Parcel3_Sub2="Parcel3_Sub2";
    public static String search_before="search_before";
    private static  boolean flag_style1 = false;
    private static  boolean flag_style2 = false;
    private static  boolean flag_style3 = false;

    private static  boolean flag_scroll1 = false;
    private static  boolean flag_square = false;
    private static  boolean flag_linear = false;


    public static boolean isFlag_style1() {
        return flag_style1;
    }

    public static void setFlag_style1(boolean flag_style1) {
        Constants.flag_style1 = flag_style1;
    }

    public static boolean isFlag_style2() {
        return flag_style2;
    }

    public static void setFlag_style2(boolean flag_style2) {
        Constants.flag_style2 = flag_style2;
    }

    public static boolean isFlag_style3() {
        return flag_style3;
    }

    public static void setFlag_style3(boolean flag_style3) {
        Constants.flag_style3 = flag_style3;
    }

    public static boolean isFlag_scroll1() {
        return flag_scroll1;
    }

    public static void setFlag_scroll1(boolean flag_scroll1) {
        Constants.flag_scroll1 = flag_scroll1;
    }

    public static boolean isFlag_square() {
        return flag_square;
    }

    public static void setFlag_square(boolean flag_square) {
        Constants.flag_square = flag_square;
    }

    public static boolean isFlag_linear() {
        return flag_linear;
    }

    public static void setFlag_linear(boolean flag_linear) {
        Constants.flag_linear = flag_linear;
    }

    public static String api_key="100";
    public static String Parcel1 = "Parcel1";
    public static String Parcel2="Parcel2";


    public  static String  sceen="";

    public static String shop_by_category="shop_by_category";
    public static String cart="cart";
    public static String order="order";

    public static String wish="wish";

    public static String dash="dash";

    public static String category="category";

    public static String profile="profile";
    public static String section="section";
    public static String single="single";
    public static String from="from";

    public static String fragment_heading="";
    public static String Database_Name="ZPaceDB";
    public static String search_result="search_result";
    public static String Parcel_single_fname="";
    public static String Parcel_single_stockid="";


    private static int fragment_order_done_flag=0;

    private static int fragment_wish_done_flag=0;
    private static int fragment_cart_done_flag=0;


    private static PaymentActivityInterface paymentActivityInterface=null;

    public static PaymentActivityInterface getPaymentActivityInterface() {
        return paymentActivityInterface;
    }

    public static void setPaymentActivityInterface(PaymentActivityInterface paymentActivityInterface) {
        Constants.paymentActivityInterface = paymentActivityInterface;
    }

    private static Fr_CartInterface fr_cartInterface=null;

    public static Fr_CartInterface getFr_cartInterface() {
        return fr_cartInterface;
    }

    public static void setFr_cartInterface(Fr_CartInterface fr_cartInterface) {
        Constants.fr_cartInterface = fr_cartInterface;
    }

    private static Ad_Single_Interface ad_single_interface=null;

    public static Ad_Single_Interface getAd_single_interface() {
        return ad_single_interface;
    }

    public static void setAd_single_interface(Ad_Single_Interface ad_single_interface) {
        Constants.ad_single_interface = ad_single_interface;
    }

    private static Fr_SearchBefore_Interface fr_searchBefore_interface=null;


    public static Fr_SearchBefore_Interface getFr_searchBefore_interface() {
        return fr_searchBefore_interface;
    }

    public static void setFr_searchBefore_interface(Fr_SearchBefore_Interface fr_searchBefore_interface) {
        Constants.fr_searchBefore_interface = fr_searchBefore_interface;
    }

    private static ProfileInterface profileInterface =null;

    public static ProfileInterface getProfileInterface() {
        return profileInterface;
    }

    public static void setProfileInterface(ProfileInterface profileInterface) {
        Constants.profileInterface = profileInterface;
    }

    private static Fr_Single_Interface fr_single_interface =null;

    public static Fr_Single_Interface getFr_single_interface() {
        return fr_single_interface;
    }

    public static void setFr_single_interface(Fr_Single_Interface fr_single_interface) {
        Constants.fr_single_interface = fr_single_interface;
    }

    private  static CatInterface catInterface=null;

    private static WishlistInterface wishlistInterface=null;

    public static WishlistInterface getWishlistInterface() {
        return wishlistInterface;
    }

    public static void setWishlistInterface(WishlistInterface wishlistInterface) {
        Constants.wishlistInterface = wishlistInterface;
    }

    public static CatInterface getCatInterface() {
        return catInterface;
    }

    public static void setCatInterface(CatInterface catInterface) {
        Constants.catInterface = catInterface;
    }




    public static int getFragment_cart_done_flag() {
        return fragment_cart_done_flag;
    }

    public static void setFragment_cart_done_flag(int fragment_cart_done_flag) {
        Constants.fragment_cart_done_flag = fragment_cart_done_flag;
    }

    public static int getFragment_wish_done_flag() {
        return fragment_wish_done_flag;
    }

    public static void setFragment_wish_done_flag(int fragment_wish_done_flag) {
        Constants.fragment_wish_done_flag = fragment_wish_done_flag;
    }

    public static int getFragment_order_done_flag() {
        return fragment_order_done_flag;
    }

    public static void setFragment_order_done_flag(int fragment_order_done_flag) {
        Constants.fragment_order_done_flag = fragment_order_done_flag;
    }

    private static String Current_fragment="";

    public static String getCurrent_fragment() {
        return Current_fragment;
    }

    public static void setCurrent_fragment(String current_fragment) {
        Current_fragment = current_fragment;
    }







    private static HomeInterface homeInterface=null;

    private static Fr_Dash_Interface fr_dash_interface=null;

    public static Fr_Dash_Interface getFr_dash_interface() {
        return fr_dash_interface;
    }

    public static void setFr_dash_interface(Fr_Dash_Interface fr_dash_interface) {
        Constants.fr_dash_interface = fr_dash_interface;
    }

    public static HomeInterface getHomeInterface() {
        return homeInterface;
    }

    public static void setHomeInterface(HomeInterface homeInterface) {
        Constants.homeInterface = homeInterface;
    }

    private  static CategoriesActivityInterface categoriesActivityInterface=null;

    public static CategoriesActivityInterface getCategoriesActivityInterface() {
        return categoriesActivityInterface;
    }

    public static void setCategoriesActivityInterface(CategoriesActivityInterface categoriesActivityInterface) {
        Constants.categoriesActivityInterface = categoriesActivityInterface;
    }



}
