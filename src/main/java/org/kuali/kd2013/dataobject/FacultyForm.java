package org.kuali.kd2013.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.KradDataServiceLocator;
import org.kuali.rice.krad.web.form.UifFormBase;

public class FacultyForm extends UifFormBase implements Serializable {
	private static final long serialVersionUID = 6857088926834897587L;
	
	protected String name;
	protected PrincipalInvestigator pi;
	protected List<OcrfAward> awards = new ArrayList<OcrfAward>(); // List of awards
	
	public FacultyForm() {
		super();
		this.setName("Ravi Samtaney");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if (name != null) {
			DataObjectService dos = KradDataServiceLocator.getDataObjectService();
			PrincipalInvestigator pi = dos.find(PrincipalInvestigator.class, name);
			if (pi != null) {
				this.setPi(pi);
			}
		}
	}
	
	public PrincipalInvestigator getPi() {
		return pi;
	}

	public void setPi(PrincipalInvestigator pi) {
		this.pi = pi;
		if (pi != null) {
			this.setAwards(pi.getAwards());
		}
	}

	public List<OcrfAward> getAwards() {
		return awards;
	}

	public void setAwards(List<OcrfAward> awards) {
		this.awards = awards;
	}
	
}
