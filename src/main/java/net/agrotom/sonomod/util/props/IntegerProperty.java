package net.agrotom.sonomod.util.props;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

public class IntegerProperty extends Property<Integer> implements Comparable<Integer> {

	public static final Predicate<Integer> UNSIGNED_INT_EXCEPTION = value -> {
		if (value < 0) {
			throw new IllegalArgumentException("Given argument is negative!");
		}
		
		return true;
	};
	
	public IntegerProperty(int value) {
		super(value);
	}

	public IntegerProperty(int value, @Nonnull Predicate<Integer> condition) {
		super(value, condition);
	}
	
	public IntegerProperty(int value, @Nonnull List<Predicate<Integer>> conditions) {
		super(value, conditions);
	}
	
	public static IntegerProperty create(int value) {
		return new IntegerProperty(value);
	}
	
	public static IntegerProperty create(int value, @Nonnull Predicate<Integer> condition) {
		return new IntegerProperty(value, condition);
	}
	
	public static IntegerProperty create(int value, @Nonnull List<Predicate<Integer>> conditions) {
		return new IntegerProperty(value, conditions);
	}
	
	@SafeVarargs
	public static IntegerProperty create(int value, @Nonnull Predicate<Integer>... conditions) {
		return create(value, Arrays.asList(conditions));
	}

	@Override
	public int compareTo(Integer o) {
		return getValue().compareTo(o);
	}

}
