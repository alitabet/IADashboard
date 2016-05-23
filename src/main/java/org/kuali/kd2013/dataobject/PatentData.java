package org.kuali.kd2013.dataobject;

import org.kuali.rice.krad.data.provider.annotation.Label;
import org.kuali.rice.krad.data.provider.annotation.NonPersistentProperty;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHint;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHintType;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHints;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="ip_data")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY, UifAutoCreateViewType.LOOKUP})
public class PatentData implements Serializable, DataObject {

	private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "publication_number",length=250)
    @Label("Publicaton Number")
	private String publicationNumber; // PI's name
    
    @Column(name = "source",length=250)
    @Label("Source")
    private String source; // PI's KAUST ID (DB Key)
    
    @Column(name = "title")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Title")
	private String title; // PI's email
    
    @Column(name = "filing_number",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Filing Number")
    private String filingNumber;
    
    @Column(name = "filing_date")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    //@Temporal(TemporalType.DATE)
    @Label("Filing Date")
    private String filingDate;
    
    @Column(name = "country",length=50)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Country")
    private String country;
    
    @Column(name = "publication_date")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    //@Temporal(TemporalType.DATE)
    @Label("Publication Date")
    private String publicationDate;
    
    @Column(name = "pct_number",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("PCT Number")
    private String pctNumber;
    
    @Column(name = "pct_filing_date")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    //@Temporal(TemporalType.DATE)
    @Label("PCT Filing Date")
    private String pctDate;
    
    @Column(name = "patent_number",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Patent Number Number")
    private String patentNumber;
    
    @Column(name = "patent_issue_date")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    //@Temporal(TemporalType.DATE)
    @Label("Patent Issue Date")
    private String patentDate;
    
    @Column(name = "type",length=50)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Type")
    private String type;
    
    @Column(name = "field_description")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Field Description")
    private String fieldDescription;
    
    @Column(name = "assignee",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Assignee")
    private String assignee;
    
    @Column(name = "link",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Link")
    private String link;
    
    @Column(name = "status")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Status")
    private String status;
    
    @Column(name = "award_number",length=250)
    @Label("Award Number")
    private String awardNumber;
    
    @ManyToMany(fetch= FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinTable(
            name="ip_faculty",
            joinColumns={@JoinColumn(name="publication_number", referencedColumnName="publication_number")},
            inverseJoinColumns={@JoinColumn(name="name", referencedColumnName="name")})
    private List<PrincipalInvestigator> faculty = new ArrayList<PrincipalInvestigator>();
    
    @NonPersistentProperty
    @Label("KAUST Faculty")
	public String getFacultyNames() {
		StringBuilder sb = new StringBuilder();
		if ( faculty != null ) {
			for ( PrincipalInvestigator pi : faculty ) {
				sb.append( pi.getName() );
				sb.append( "\n" );
			}
		}
		return sb.toString();
	}
    
	public String getPublicationNumber() {
		return publicationNumber;
	}

	public void setPublicationNumber(String publicationNumber) {
		this.publicationNumber = publicationNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilingNumber() {
		return filingNumber;
	}

	public void setFilingNumber(String filingNumber) {
		this.filingNumber = filingNumber;
	}

	public String getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getPctNumber() {
		return pctNumber;
	}

	public void setPctNumber(String pctNumber) {
		this.pctNumber = pctNumber;
	}

	public String getPctDate() {
		return pctDate;
	}

	public void setPctDate(String pctDate) {
		this.pctDate = pctDate;
	}
	
	public String getPatentNumber() {
		return patentNumber;
	}

	public void setPatentNumber(String patentNumber) {
		this.patentNumber = patentNumber;
	}

	public String getPatentDate() {
		return patentDate;
	}

	public void setPatentDate(String patentDate) {
		this.patentDate = patentDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public List<PrincipalInvestigator> getFaculty() {
		return faculty;
	}

	public void setFaculty(List<PrincipalInvestigator> faculty) {
		this.faculty = faculty;
	}

	@Override
	public void addToCollection(Object obj, Map<String, Object> results) {
		PatentData patentData = (PatentData) obj;
		if (!results.containsKey(patentData.getPublicationNumber())) results.put(patentData.getPublicationNumber(), patentData);
	}

	@Override
	public String[] getValues() {
		String[] values = {"status", "source", "title", "assignee"};
		return values;
	}
}
