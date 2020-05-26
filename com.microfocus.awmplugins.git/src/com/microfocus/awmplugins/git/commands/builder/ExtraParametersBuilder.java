package com.microfocus.awmplugins.git.commands.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ExtraParametersBuilder<T extends ExtraParametersBuilder<T>> {

    private final List<String> extraParameters;

    protected ExtraParametersBuilder() {
        this(new ArrayList<>());
    }

    protected ExtraParametersBuilder(final List<String> extraParameters) {
        this.extraParameters = extraParameters;
    }

    public T param(final String parameter) {
        this.extraParameters.add(parameter);
        return getThis();
    }

    public T params(final String... parameters) {
        this.extraParameters.addAll(Arrays.asList(parameters));
        return getThis();
    }

    protected List<String> getExtraParameters() {
        return new ArrayList<>(extraParameters);
    }

    abstract protected T getThis();

}
