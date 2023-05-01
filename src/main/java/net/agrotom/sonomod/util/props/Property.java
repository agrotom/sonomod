package net.agrotom.sonomod.util.props;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import net.agrotom.sonomod.util.UniqueObject;

public abstract class Property <T> extends UniqueObject {
    private T value;
    private List<Property<T>> dependencies = new LinkedList<>();
    
    private Optional<UpdateListener> listener = Optional.empty();
    private List<Predicate<T>> conditions = new LinkedList<Predicate<T>>();
    
    private boolean isStrict = true;
    
    private boolean and(T newValue) {
	for(Predicate<T> cond : conditions) {
	    if(!cond.test(newValue)) {
		return false;
	    }
	}
	
	return true;
    }
    
    private boolean or(T newValue) {
	for(Predicate<T> cond : conditions) {
	    if(cond.test(newValue)) {
		return true;
	    }
	}
	
	return false;
    }
    
    private void update(Property<T> caller, T oldValue, T newValue) {
	listener.ifPresent(l -> {
	    l.updated(caller, oldValue, newValue);
	});
    }
    
    public Property() {}
    
    public Property(T value) {
	this.value = value;
    }
    
    public Property(T value, Predicate<T> condition) {
	this(value);
	this.addCondition(condition);
    }
    
    public void setValue(T newValue) {
	if((isStrict && !and(newValue)) || (!isStrict && !or(newValue))) {
	    return;
	}
	
	dependencies.forEach(dependency -> {
	    dependency.update(this, value, newValue);
	});
	
	this.value = newValue;
    }
    
    public T getValue() {
	return value;
    }
    
    public void bindDependency(Property<T> dependency) throws Exception {
	if(this.equals(dependency)) {
	    throw new Exception("Trying to bind same property!");
	}
	
	for(Property<T> property : dependencies) {
	    if(property.equals(dependency)) {
		return;
	    }
	}
	
	dependencies.add(dependency);
    }
    
    public void bindTwoDirectional(Property<T> dependency) throws Exception {
	this.bindDependency(dependency);
	dependency.bindDependency(this);
    }
    
    public void unBindDependency(Property<T> dependency) {
	dependencies.removeIf(property -> {
	    return property.equals(dependency);
	});
    }
    
    public Property<T> setOnUpdate(UpdateListener listener) {
	this.listener = Optional.ofNullable(listener);
	
	return this;
    }
    
    public Property<T> addCondition(Predicate<T> condition) {
	this.conditions.add(condition);
	
	return this;
    }
    
    public void setIsStrict(boolean isStrict) {
	this.isStrict = isStrict;
    }
}
