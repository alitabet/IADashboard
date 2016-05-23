package org.kuali.kd2013.dataobject;

import org.kuali.rice.krad.data.provider.annotation.InheritProperty;
import org.kuali.rice.krad.data.provider.annotation.KeyValuesFinderClass;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Entity
@Table(name="awards")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY, UifAutoCreateViewType.LOOKUP})
public class OcrfAward implements Serializable, DataObject {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="award_number",length=50)
    @Label("Award Number")
	private String awardNumber; // The unique award number (DB Key)
	
    @Column(name="uapn")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    //@Label("UAPN") 
	private Double uapn; // UAPN
    
	@Column(name="program",length=50)
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    @Label("Program") 
	private String program; // Award program (CRG, CCF, CS, etc..)
	
	@Column(name="round")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    @Label("Round") 
	private int awardRound; // Round of call
	
	// Positioned here since properties are processed in order found
	@NonPersistentProperty
	@Label("Program")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getAwardProgramString() {
		return program
			   + " "
			   + Integer.toString(awardRound);
	}
	
	@Column(name="name",length=250)
	@Label("Name of PI")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
	private String name;
	
	@Column(name="project_title",length=250)
//	@UifDisplayHints({
//		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
//	})
    @Label("Project Title") 
	private String projectTitle; // Title of the project
	
	@Column(name="keywords")
    @Label("Keywords") 
	private String keyWords; // Keywords
	
	@Column(name="type")
    @Label("Type") 
	@KeyValuesFinderClass(AwardTypeValuesFinder.class)
	private String type; // Type of Proposal

	@Column(name="co_pis",length=500)
    @Label("Co-PIs")
    private String coPis;
	
	@Column(name="begin_date")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
	@Temporal(TemporalType.DATE)
    @Label("Start Date") 
	private Date startDate; // Start date of award
	
	@Column(name="end_date")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
	@Temporal(TemporalType.DATE)
    @Label("End Date")
	private Date endDate; // End date of award
	
	@Column(name="total_budget",length=19)
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    //@Label("Total Project Budget") 
	private BigDecimal totalBudget; // Total project budget
	
	@NonPersistentProperty
	@Label("Total Project Budget")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getBudgetString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(totalBudget);
	}
	
	@OneToMany(fetch= FetchType.EAGER, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy = "award")
    protected List<OcrfSubAward> subAwards = new ArrayList<OcrfSubAward>();
	
	@OneToMany(fetch= FetchType.EAGER, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy = "award")
    protected List<Contract> contracts = new ArrayList<Contract>();
	
	@ManyToOne
	@JoinColumn(name = "name",insertable=false, updatable=false)
	@InheritProperty(name = "name",label = @Label("Name of PI"), displayHints = @UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)}))
	/*@InheritProperties({ 
		@InheritProperty(name = "name",label = @Label("Name of PI")),
		@InheritProperty(name = "idNumber",label = @Label("KAUST ID of PI"))})*/
    PrincipalInvestigator pi;

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public Double getUapn() {
		return uapn;
	}

	public void setUapn(Double uapn) {
		this.uapn = uapn;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public int getAwardRound() {
		return awardRound;
	}

	public void setAwardRound(int awardRound) {
		this.awardRound = awardRound;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public String getCoPis() {
        return coPis;
    }

    public void setCoPis(String coPis) {
        this.coPis = coPis;
    }

    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(BigDecimal totalBudget) {
		this.totalBudget = totalBudget;
	}

	public List<OcrfSubAward> getSubAwards() {
		return subAwards;
	}

	public void setSubAwards(List<OcrfSubAward> subAwards) {
		this.subAwards = subAwards;
	}

	public PrincipalInvestigator getPi() {
		return pi;
	}

	public void setPi(PrincipalInvestigator pi) {
		this.pi = pi;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contract) {
		this.contracts = contract;
	}

	@Override
	public void addToCollection(Object obj, Map<String, Object> results) {
        OcrfAward award = (OcrfAward) obj;
        if (!results.containsKey(award.getAwardNumber())) results.put(award.getAwardNumber(), award);
	}

	@Override
	public String[] getValues() {
//        this.getAwardNumber().getClass().getName()
        String[] values = {"awardNumber", "name", "coPis", "projectTitle", "keyWords"};
        return values;
	}
}
