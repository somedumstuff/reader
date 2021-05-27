package com.example.readerzeropointone;

import java.util.ArrayList;

public class DaSubActivity {

    int subID;
    int logoImage;

    public DaSubActivity(int subID, int logoImage){
            this.subID = subID;
            this.logoImage = logoImage;
    }

    public int getSubID(){
        return subID;
    }

    public int getLogoImage(){
        return logoImage;
    }
}
