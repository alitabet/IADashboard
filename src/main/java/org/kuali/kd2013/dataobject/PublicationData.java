package org.kuali.kd2013.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kuali.rice.krad.data.provider.annotation.InheritProperty;
import org.kuali.rice.krad.data.provider.annotation.Label;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHint;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHintType;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHints;

@Entity
@Table(name="publications_new")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY, UifAutoCreateViewType.LOOKUP})
public class PublicationData implements Serializable {

	private static final long serialVersionUID = 4768156680246084251L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Label("ID")
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "eid",length=250)
    @Label("EID")
    private String eid; // EID
    
    @Column(name = "doi",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("DOI")
    private String doi; // DOI
 
	@Column(name = "name",length=50)
	@Label("KAUST Author")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    private String name; // PI's KAUST ID (DB Key)
	
	@Column(name = "award_number",length=50)
	@Label("Award Number")
    private String awardNumber; // Award Number if exists
	
	@Column(name = "title")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
	@Label("Title")
    private String title; // Title of publication (DB Key)
    
    @Column(name = "date")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Temporal(TemporalType.DATE)
    @Label("Date of Publication")
    private Date date; // Year of publication
    
    @Column(name = "source_title",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Source")
    private String source; // Source
    
    @Column(name = "volume",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Volume")
    private String volume; // Volume
    
    @Column(name = "issue",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Issue")
    private String issue; // Issue
    
    @Column(name = "cited_by")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Cited by")
    private Integer citedBy; // Number of citations
    
    @Column(name = "type",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Type")
    private String type; // Type of publication
    
    @Column(name = "authors")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    @Label("Authors")
    private String authors; // All Authors
    
    @ManyToOne
	@JoinColumn(name = "name",insertable=false, updatable=false)
    @InheritProperty(name = "name",label = @Label("Name of PI"), displayHints = @UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)}))
    PrincipalInvestigator pi;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public Integer getCitedBy() {
		return citedBy;
	}

	public void setCitedBy(Integer citedBy) {
		this.citedBy = citedBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public PrincipalInvestigator getPi() {
		return pi;
	}

	public void setPi(PrincipalInvestigator pi) {
		this.pi = pi;
	}

}
