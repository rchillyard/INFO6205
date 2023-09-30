/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.equable;

public abstract class BaseComparableEquable extends BaseEquable {

    protected int compareTo(BaseEquable o) {
        return ((ComparableEquable) getEquable()).compareTo((ComparableEquable) o.getEquable());
    }
}