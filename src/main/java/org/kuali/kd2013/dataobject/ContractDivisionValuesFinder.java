package org.kuali.kd2013.dataobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class ContractDivisionValuesFinder extends KeyValuesBase {
	private static final long serialVersionUID = 1L;

	static List<KeyValue> VALUES = new ArrayList<KeyValue>(4);
	static {
		VALUES.add( new ConcreteKeyValue("", "") );
		VALUES.add( new ConcreteKeyValue("BESE", "BESE") );
		VALUES.add( new ConcreteKeyValue("PSE", "PSE") );
		VALUES.add( new ConcreteKeyValue("CEMSE", "CEMSE") );
		VALUES.add( new ConcreteKeyValue("CORE", "CORE") );
		VALUES.add( new ConcreteKeyValue("MULTIPLE", "MULTIPLE") );
		VALUES.add( new ConcreteKeyValue("OTHER", "OTHER") );
	}
	
	@Override
	public List<KeyValue> getKeyValues() {
		return Collections.unmodifiableList(VALUES);
	}
}
