/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.equable;

import com.sun.istack.internal.Nullable;
import org.jetbrains.annotations.NotNull;

public abstract class BaseEquable {

    @NotNull
    protected abstract Equable getEquable();

    @Override
    public int hashCode() {
        return getEquable().hashCode();
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEquable equable = (BaseEquable) o;
        return getEquable().equals(equable.getEquable());
    }
}
