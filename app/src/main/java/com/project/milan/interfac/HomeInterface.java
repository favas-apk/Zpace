package com.project.milan.interfac;

public interface HomeInterface {


    void show_heading(String heading);
    void show_sub_heading(String subheading);

    void hide_hamburger();

    void unselect_all_navigation_items();
    void select_navigation_item(int position);

    void select_bottom_navigation_item(int position);

   // void lock_nav_drawer();

    void unlock_nav_drawer();

    void show_back();

    void show_ham();

    void Show_hide_logout();

    void hide_bottom_nav();
    void show_bottom_nav();

    void show_search_cart();

    void hide_search_cart();

    void Switch_to_fragment(String fragment_tag);

    void do_payment();

}
