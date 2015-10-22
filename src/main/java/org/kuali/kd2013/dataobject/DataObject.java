package org.kuali.kd2013.dataobject;

import java.util.Map;

/**
 * This is a general Data interface that all objects will implement.
 *
 * @author Ali K Thabet
 */
public interface DataObject {

    /**
     * Add a given object to a collection using a unique
     * key defined by the object type
     *
     * @param obj Object to add to collection
     * @param results Map with collection values
     */
    public void addToCollection(Object obj, Map<String, Object> results);

    /**
     * Returns a String array with the variable names
     * to include in the {@link org.kuali.rice.krad.lookup.LookupableImpl}
     * for general keyword search
     *
     * @return
     */
    public String[] getValues();
}
