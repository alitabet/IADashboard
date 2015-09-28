package org.kuali.kd2013.dataobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class AwardProgramValuesFinder extends KeyValuesBase {
	private static final long serialVersionUID = 1L;

	static List<KeyValue> VALUES = new ArrayList<KeyValue>(4);
	static {
		VALUES.add( new ConcreteKeyValue("", "") );
		VALUES.add( new ConcreteKeyValue("CRG 1", "CRG 1") );
		VALUES.add( new ConcreteKeyValue("CRG 2", "CRG 2") );
		VALUES.add( new ConcreteKeyValue("CRG 3", "CRG 3") );
		VALUES.add( new ConcreteKeyValue("CCF", "CCF") );
		VALUES.add( new ConcreteKeyValue("Sensors", "Sensors") );
	}
	
	@Override
	public List<KeyValue> getKeyValues() {
		return Collections.unmodifiableList(VALUES);
	}

}
