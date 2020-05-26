package com.microfocus.awmplugins.git.executors;

import java.util.Arrays;
import java.util.Collection;

import com.microfocus.awm.meta.modelextension.ICustomRestriction;

public class CloneRepositoryTypeRestrictions implements ICustomRestriction<String> {

    @Override
    public String getDefaultValue() {
        return "Full";
    }

    @Override
    public Collection<String> getRestrictions() {
        return Arrays.asList("Full", "Depth", "Since");
    }

    @Override
    public boolean isEditable() {
        return false;
    }

}
