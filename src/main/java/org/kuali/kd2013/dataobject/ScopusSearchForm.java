package org.kuali.kd2013.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.KradDataServiceLocator;
import org.kuali.rice.krad.web.form.UifFormBase;

public class ScopusSearchForm extends UifFormBase implements Serializable {
	private static final long serialVersionUID = 6857088926834897587L;
	
	protected String name;
	protected PrincipalInvestigator pi;
	protected List<ScopusPI> scopusIds = new ArrayList<ScopusPI>();
	
	public ScopusSearchForm() {
		super();
	}
	
	/*@Override
    protected String getDefaultDocumentTypeName() {
        return "ScopusSearchView";
    }*/
	
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
			this.setScopusIds(pi.getScopusID());
		}
	}

	public List<ScopusPI> getScopusIds() {
		return scopusIds;
	}

	public void setScopusIds(List<ScopusPI> scopusIds) {
		this.scopusIds = scopusIds;
	}
	
}
