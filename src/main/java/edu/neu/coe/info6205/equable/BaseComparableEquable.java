/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.equable;

import edu.neu.coe.info6205.ComparableTuple;

public abstract class BaseComparableEquable extends BaseEquable {

    public int compareTo(ComparableTuple o) {
        return ((ComparableEquable) getEquable()).compareTo((ComparableEquable) o.getEquable());
    }
}
