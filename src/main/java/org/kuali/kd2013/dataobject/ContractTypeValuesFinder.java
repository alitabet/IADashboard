package org.kuali.kd2013.dataobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class ContractTypeValuesFinder extends KeyValuesBase {
	private static final long serialVersionUID = 1L;

	static List<KeyValue> VALUES = new ArrayList<KeyValue>(4);
	static {
		VALUES.add( new ConcreteKeyValue("", "") );
		VALUES.add( new ConcreteKeyValue("*ASA*", "ASA") );
		VALUES.add( new ConcreteKeyValue("*CDA*", "CDA") );
		VALUES.add( new ConcreteKeyValue("*CRA*", "CRA") );
		VALUES.add( new ConcreteKeyValue("*MOU*", "MOU") );
		VALUES.add( new ConcreteKeyValue("*MTA*", "MTA") );
		VALUES.add( new ConcreteKeyValue("*NDA*", "NDA") );
		VALUES.add( new ConcreteKeyValue("*RCA*", "RCA") );
		VALUES.add( new ConcreteKeyValue("*SRA*", "SRA") );
	}
	
	@Override
	public List<KeyValue> getKeyValues() {
		return Collections.unmodifiableList(VALUES);
	}
}
