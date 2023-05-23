package net.agrotom.sonomod.util.props;

import java.util.function.Predicate;

import javax.annotation.Nonnull;

public class IntegerProperty extends Property<Integer> implements Comparable<Integer> {

	public IntegerProperty(int value) {
		super(value);
	}

	public IntegerProperty(int value, @Nonnull Predicate<Integer> condition) {
		super(value, condition);
	}

	@Override
	public int compareTo(Integer o) {
		return getValue().compareTo(o);
	}

}
