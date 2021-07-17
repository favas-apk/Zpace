package com.project.zpace.model;

public class model_main {

    private String main;
    private boolean enabled;


    public model_main(String main, boolean enabled) {
        this.main = main;
        this.enabled = enabled;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
