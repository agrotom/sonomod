package net.agrotom.sonomod.util;

import java.util.UUID;

public abstract class UniqueObject {
    
    private final String id = UUID.randomUUID().toString();
    
    //The method is final due to its unique id, so there is no necessary to compare objects by their data.
    @Override
    public final boolean equals(Object obj) {
        
	if(!(obj instanceof UniqueObject)) {
	    return false;
	}
	
	final UniqueObject castedObj = (UniqueObject) obj;
	
	return this.id.equals(castedObj.id);
	
    }

}
