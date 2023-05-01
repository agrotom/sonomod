package net.agrotom.sonomod.util.props;

import java.util.function.Predicate;

public class IntegerProperty extends Property<Integer> implements Comparable<IntegerProperty> {

    public IntegerProperty(int value) {
	super(value);
    }
    
    public IntegerProperty(int value, Predicate<Integer> condition) {
	super(value, condition);
    }
    
    @Override
    public int compareTo(IntegerProperty o) {
	return getValue().compareTo(o.getValue());
    }

}
