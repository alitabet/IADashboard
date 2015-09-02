package org.kuali.kd2013.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.rice.krad.data.provider.annotation.Label;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;

@Entity
@Table(name="scopus")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY, UifAutoCreateViewType.LOOKUP})
public class ScopusPI implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "scopus_id",length=50)
    @Label("Scopus ID")
	private String scopusID; // PI's name
	
	@Column(name = "name",length=250)
    @Label("Name")
    private String name; // PI's KAUST ID (DB Key)
	
	@Column(name = "scopus_name",length=250)
    @Label("Scopus Name")
    private String scopusName;
    
    @Column(name = "affiliation",length=250)
    @Label("Affiliation")
    private String affiliation;
    
    @Column(name = "city",length=250)
    @Label("City")
    private String city;
    
    @Column(name = "country",length=250)
    @Label("Country")
    private String country;
    
    @Column(name="number_publications")
	/*@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})*/
    @Label("Number of Publications") 
	private int number_publications; // Round of call
    
    @Column(name="h_index")
	/*@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})*/
    @Label("H-index") 
	private int hIndex; // Round of call
    
    @Column(name="cited_by")
	/*@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})*/
    @Label("Cited-by Count") 
	private int citedBy; // Round of call
    
    @Column(name="citations")
	/*@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})*/
    @Label("Citations Count") 
	private int citations; // Round of call
    
	@ManyToOne
	@JoinColumn(name = "name",insertable=false, updatable=false)
    PrincipalInvestigator pi;

	public String getScopusID() {
		return scopusID;
	}

	public void setScopusID(String scopusID) {
		this.scopusID = scopusID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getScopusName() {
		return scopusName;
	}

	public void setScopusName(String scopusName) {
		this.scopusName = scopusName;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getNumber_publications() {
		return number_publications;
	}

	public void setNumber_publications(int number_publications) {
		this.number_publications = number_publications;
	}

	public int gethIndex() {
		return hIndex;
	}

	public void sethIndex(int hIndex) {
		this.hIndex = hIndex;
	}

	public int getCitedBy() {
		return citedBy;
	}

	public void setCitedBy(int citedBy) {
		this.citedBy = citedBy;
	}

	public int getCitations() {
		return citations;
	}

	public void setCitations(int citations) {
		this.citations = citations;
	}

	public PrincipalInvestigator getPi() {
		return pi;
	}

	public void setPi(PrincipalInvestigator pi) {
		this.pi = pi;
	}
}
