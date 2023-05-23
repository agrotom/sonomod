package net.agrotom.sonomod.util.props;

import java.util.function.Predicate;

import javax.annotation.Nonnull;

public interface IProperty<T> {
	void setValue(@Nonnull T newValue);

	T getValue();

	void bindDependency(@Nonnull IProperty<T> dependency) throws Exception;

	void bindTwoDirectional(@Nonnull IProperty<T> dependency) throws Exception;

	void unBindDependency(@Nonnull IProperty<T> dependency);

	IProperty<T> setOnUpdate(@Nonnull UpdateListener<T> listener);
	
	IProperty<T> setOnValueChanged(@Nonnull ValueChangedListener<T> listener);
	
	void update(IProperty<T> caller);
	
	void onValueChanged(T oldValue, T newValue);

	IProperty<T> addCondition(@Nonnull Predicate<T> condition);
	
	void setIsStrict(boolean isStrict);
}
