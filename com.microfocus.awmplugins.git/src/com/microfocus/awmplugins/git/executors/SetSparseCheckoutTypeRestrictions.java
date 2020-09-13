package com.microfocus.awmplugins.git.executors;

import java.util.Arrays;
import java.util.Collection;

import com.microfocus.awm.meta.modelextension.ICustomRestriction;

public class SetSparseCheckoutTypeRestrictions implements ICustomRestriction<String> {

    @Override
    public String getDefaultValue() {
        return "Set";
    }

    @Override
    public Collection<String> getRestrictions() {
        return Arrays.asList("Add", "Remove", "Set");
    }

    @Override
    public boolean isEditable() {
        return false;
    }

}
