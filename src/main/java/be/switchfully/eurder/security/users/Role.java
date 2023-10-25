package be.switchfully.eurder.security.users;

import be.switchfully.eurder.security.Feature;

import java.util.List;

import static be.switchfully.eurder.security.Feature.*;

public enum Role {
    ADMIN(List.of(
            ADD_NEW_ITEM,
            UPDATE_ITEM,
            CONSULT_ITEMS,
            CONSULT_CUSTOMERS,
            CONSULT_ORDERS)),
    CUSTOMER(List.of(
            CREATE_ORDER,
            CONSULT_ITEMS,
            ADD_ITEMGROUP));


    private final List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
