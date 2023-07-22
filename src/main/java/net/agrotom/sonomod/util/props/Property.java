package net.agrotom.sonomod.util.props;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import net.agrotom.sonomod.util.UniqueObject;

public abstract class Property<T> extends UniqueObject implements IProperty<T> {
	private T value;
	private List<IProperty<T>> dependencies = new LinkedList<>();

	private Optional<UpdateListener<T>> listener = Optional.empty();
	private Optional<ValueChangedListener<T>> onValueChanged = Optional.empty();
	private List<Predicate<T>> conditions = new LinkedList<Predicate<T>>();

	private boolean isStrict = true;

	private boolean and(T newValue) {
		for (Predicate<T> cond : conditions) {
			if (!cond.test(newValue)) {
				return false;
			}
		}

		return true;
	}

	private boolean or(T newValue) {
		for (Predicate<T> cond : conditions) {
			if (cond.test(newValue)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void update(IProperty<T> caller) {
		listener.ifPresent(l -> {
			l.updated(this, caller);
		});
	}
	
	@Override
	public void onValueChanged(T oldValue, T newValue) {
		onValueChanged.ifPresent(changed -> {
			changed.updated(this, oldValue, newValue);
		});
	}

	public Property() {
	}

	public Property(T value) {
		this.value = value;
	}

	public Property(T value, Predicate<T> condition) {
		this(value);
		this.addCondition(condition);
	}
	
	public Property(T value, List<Predicate<T>> conditions) {
		this(value);
		this.addAllConditions(conditions);
	}

	@Override
	public void setValue(T newValue) {
		if (testValue(newValue)) {
			return;
		}

		dependencies.forEach(dependency -> {
			dependency.update(this);
		});

		this.onValueChanged(this.value, newValue);
		
		this.value = newValue;
	}

	@Override
	public T getValue() {
		return value;
	}
	
	@Override
	public boolean testValue(T value) {
		return (isStrict && !and(value)) || (!isStrict && !or(value));
	}

	@Override
	public void bindDependency(IProperty<T> dependency) throws Exception {
		if (this.equals(dependency)) {
			throw new Exception("Trying to bind same property!");
		}

		for (IProperty<T> property : dependencies) {
			if (property.equals(dependency)) {
				return;
			}
		}

		dependencies.add(dependency);
	}

	@Override
	public void bindTwoDirectional(IProperty<T> dependency) throws Exception {
		this.bindDependency(dependency);
		dependency.bindDependency(this);
	}

	@Override
	public void unBindDependency(IProperty<T> dependency) {
		dependencies.removeIf(property -> {
			return property.equals(dependency);
		});
	}

	@Override
	public IProperty<T> setOnUpdate(UpdateListener<T> listener) {
		this.listener = Optional.ofNullable(listener);

		return this;
	}
	
	@Override
	public IProperty<T> setOnValueChanged(ValueChangedListener<T> listener) {
		this.onValueChanged = Optional.ofNullable(listener);

		return this;
	}

	@Override
	public IProperty<T> addCondition(Predicate<T> condition) {
		this.conditions.add(condition);

		return this;
	}
	
	@Override
	public IProperty<T> addAllConditions(List<Predicate<T>> conditions) {
		this.conditions.addAll(conditions);

		return this;
	}

	@Override
	public void setIsStrict(boolean isStrict) {
		this.isStrict = isStrict;
	}
}
