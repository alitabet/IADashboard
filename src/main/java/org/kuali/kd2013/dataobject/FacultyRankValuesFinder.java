package org.kuali.kd2013.dataobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class FacultyRankValuesFinder extends KeyValuesBase {
	private static final long serialVersionUID = 1L;

	static List<KeyValue> VALUES = new ArrayList<KeyValue>(4);
	static {
		VALUES.add( new ConcreteKeyValue("", "") );
		VALUES.add( new ConcreteKeyValue("Assistant Professor", "Assistant Professor") );
		VALUES.add( new ConcreteKeyValue("Associate Professor", "Associate Professor") );
		VALUES.add( new ConcreteKeyValue("Professor", "Full Professor") );
		VALUES.add( new ConcreteKeyValue("Adjunct", "Adjunct") );
		VALUES.add( new ConcreteKeyValue("Distinguished Professor", "Distinguished Professor") );
		VALUES.add( new ConcreteKeyValue("Emeritus", "Emeritus") );
		VALUES.add( new ConcreteKeyValue("Professor of Practice", "Professor of Practice") );
		VALUES.add( new ConcreteKeyValue("Research Professor", "Research Professor") );
	}
	
	@Override
	public List<KeyValue> getKeyValues() {
		return Collections.unmodifiableList(VALUES);
	}
}
