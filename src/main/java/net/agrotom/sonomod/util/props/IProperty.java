package net.agrotom.sonomod.util.props;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

public interface IProperty<T> {
	void setValue(@Nonnull T newValue);

	T getValue();
	
	boolean testValue(@Nonnull T value);

	void bindDependency(@Nonnull IProperty<T> dependency) throws Exception;

	void bindTwoDirectional(@Nonnull IProperty<T> dependency) throws Exception;

	void unBindDependency(@Nonnull IProperty<T> dependency);

	IProperty<T> setOnUpdate(@Nonnull UpdateListener<T> listener);
	
	IProperty<T> setOnValueChanged(@Nonnull ValueChangedListener<T> listener);
	
	void update(@Nonnull IProperty<T> caller);
	
	void onValueChanged(@Nonnull T oldValue, @Nonnull T newValue);

	IProperty<T> addCondition(@Nonnull Predicate<T> condition);
	
	IProperty<T> addAllConditions(@Nonnull List<Predicate<T>> conditions);
	
	void setIsStrict(boolean isStrict);
}
