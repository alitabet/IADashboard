package org.kuali.kd2013.dataobject;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CenterValuesFinder extends KeyValuesBase {
	private static final long serialVersionUID = 1L;

	static List<KeyValue> VALUES = new ArrayList<KeyValue>(11);
	static {
		VALUES.add( new ConcreteKeyValue("", "") );
		VALUES.add( new ConcreteKeyValue("Water Desalination and Reuse Center", "WDRC") );
		VALUES.add( new ConcreteKeyValue("Visual Computing Center", "VCC") );
		VALUES.add( new ConcreteKeyValue("Solar and Photovoltaics Engineering Research Center", "SPERC") );
		VALUES.add( new ConcreteKeyValue("Red Sea Research Center", "RSRC") );
		VALUES.add( new ConcreteKeyValue("Catalysis Center", "KCC") );
		VALUES.add( new ConcreteKeyValue("Computational Bioscience Research Center", "CBRC") );
		VALUES.add( new ConcreteKeyValue("Clean Combustion Research Center", "CCRC") );
		VALUES.add( new ConcreteKeyValue("Center for Desert Agriculture", "CDA") );
		VALUES.add( new ConcreteKeyValue("Advanced Membranes and Porous Materials Center", "AMPM") );
		VALUES.add( new ConcreteKeyValue("Upstream Petroleum Engineering Center", "UPEC") );
        VALUES.add( new ConcreteKeyValue("Extreme Computing Research Center", "ECRC") );
		VALUES.add( new ConcreteKeyValue("N/A", "None") );}
	
	@Override
	public List<KeyValue> getKeyValues() {
		return Collections.unmodifiableList(VALUES);
	}
}
