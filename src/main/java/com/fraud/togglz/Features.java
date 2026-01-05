package com.fraud.togglz;

import jdk.jfr.Label;
import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.context.FeatureContext;

public enum Features implements Feature {
    @Label("CheckFraud")
    @EnabledByDefault
    CHECK_FRAUD;



    public boolean isActive(){
        return FeatureContext.getFeatureManager().isActive(this);
    }

}