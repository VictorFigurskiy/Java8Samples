package com.sample.patterns.behavioral.state;

public class Developer {

    private Activity activity;

    public void changeActivity() {
        if (activity instanceof Sleeping) {
            setActivity(new Traning());
        } else if (activity instanceof Traning) {
            setActivity(new Coding());
        } else if (activity instanceof Coding) {
            setActivity(new Reading());
        } else if (activity instanceof Reading) {
            setActivity(new Sleeping());
        }
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void justDoIt(){
        activity.justDoIt();
    }
}
