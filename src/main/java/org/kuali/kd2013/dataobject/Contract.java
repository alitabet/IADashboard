package org.kuali.kd2013.dataobject;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.rice.krad.data.provider.annotation.KeyValuesFinderClass;
import org.kuali.rice.krad.data.provider.annotation.Label;
import org.kuali.rice.krad.data.provider.annotation.NonPersistentProperty;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHint;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHintType;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHints;

@Entity
@Table(name="contracts")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY, UifAutoCreateViewType.LOOKUP})
public class Contract implements Serializable {

	private static final long serialVersionUID = 4768156680246084251L;
	
	@Id
	@Column(name = "ors_number",length=50)
	@Label("Contract Number")
    private String orsNumber; // Unique identifier from ORS MDB
	
	
	@Column(name = "sponsor",length=250)
	@Label("Sponsor")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String sponsor; // Sponsor of agreement
	
	
	@Column(name = "sponsor_contact",length=250)
	@Label("Sponsor Contact")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String sponsorContact; // Contact person at sponsor
	
	
	@Column(name = "award_number",length=250)
	@Label("Award Number")
    private String awardNumber; // OSR award number if available 
	
	
	@Column(name = "name",length=250)
	@Label("KAUST PI")
    private String name; // Name of KAUST PI
	
	
	@Column(name = "co_i",length=250)
	@Label("KAUST Co-I")
	@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
    private String coI; // Name of KAUST Co-I(s)
	
	
	@Column(name = "institution",length=250)
	@Label("Collaborating Institution")
    private String institution; // Name of collaborating institution(s)
	
	
	@Column(name = "collaborator",length=250)
	@Label("External Collaborators")

    private String collaborator; // Name of external collaborator(s)
	
	
	@Column(name = "spon_coll_type",length=250)
	@Label("Sponsor/Collaboratro Type")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String sponColl; // 
	
	
	@Column(name = "kaust_role",length=250)
	@Label("KAUST Role")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String kaustRole; // 
	
	
	@Column(name = "contract_type",length=250)
	@Label("Type of Contract")
	@KeyValuesFinderClass(ContractTypeValuesFinder.class)
    private String contractType; // 
	
	
	@Column(name = "status",length=250)
	@Label("Status")
	@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
    private String status; // 
	
	
	@Column(name = "division",length=250)
	@Label("Division")
	@KeyValuesFinderClass(ContractDivisionValuesFinder.class)
    private String division; // 
	
	
	@Column(name = "activity_start",length=250)
	@Label("Activity Start Date")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String activityStart; // 
	
	
	@Column(name = "agreement_start",length=250)
	@Label("Agreement Start Date")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT),
	})
    private String agreementStart; // 
	
	
	@Column(name = "agreement_end",length=250)
	@Label("Agreement End Date")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String agreementEnd; // 
	
	
	@Column(name = "duration",length=250)
	@Label("Duration")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String duration; // 
	
	
	@Column(name = "funding_to_kaust",length=250)
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT),
		@UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    private Double fundingKaust; // 
	
	@NonPersistentProperty
	@Label("Funding to KAUST")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getFundingKaustString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(fundingKaust);
	}
	
	@Column(name = "funding_to_colaborator",length=250)
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT),
		@UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    private Double fundingColaborator; // 
	
	@NonPersistentProperty
	@Label("Funding to Collaborator")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getFundingCollaboratorString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(fundingColaborator);
	}
	
	@Column(name = "total_funding",length=250)
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT),
		@UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    private Double totalFunding; // 
	
	@NonPersistentProperty
	@Label("Total Funding")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getTotalFundingString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(totalFunding);
	}
	
	@Column(name = "kaust_contribution",length=250)
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT),
		@UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    private Double kaustContribution; // 
	
	@NonPersistentProperty
	@Label("KAUST Contribution")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getKaustContributionString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(kaustContribution);
	}
	
	@Column(name = "title")
	@Label("Title")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String title; // 
	
	
	@Column(name = "preaward_lead")
	@Label("Pre-award Lead")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String preawardLead; // 
	
	
	@Column(name = "contracts_lead",length=250)
	@Label("Award and Contracts Lead")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String contractsLead; // 
	
	
	@Column(name = "postaward_lead",length=250)
	@Label("Post-award Lead")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String postawardLead; // 
	
	
	@Column(name = "comments")
	@Label("Comments")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA),
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	})
    private String comments; // 
	
	@ManyToOne
	@JoinColumn(name = "name", insertable=false, updatable=false, nullable=true)
    PrincipalInvestigator pi;
	
	@ManyToOne
	@JoinColumn(name = "award_number",insertable=false, updatable=false, nullable=true)
    OcrfAward award;

	public String getOrsNumber() {
		return orsNumber;
	}

	public void setOrsNumber(String orsNumber) {
		this.orsNumber = orsNumber;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getSponsorContact() {
		return sponsorContact;
	}

	public void setSponsorContact(String sponsorContact) {
		this.sponsorContact = sponsorContact;
	}

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoI() {
		return coI;
	}

	public void setCoI(String coI) {
		this.coI = coI;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(String collaborator) {
		this.collaborator = collaborator;
	}

	public String getSponColl() {
		return sponColl;
	}

	public void setSponColl(String sponColl) {
		this.sponColl = sponColl;
	}

	public String getKaustRole() {
		return kaustRole;
	}

	public void setKaustRole(String kaustRole) {
		this.kaustRole = kaustRole;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getActivityStart() {
		return activityStart;
	}

	public void setActivityStart(String activityStart) {
		this.activityStart = activityStart;
	}

	public String getAgreementStart() {
		return agreementStart;
	}

	public void setAgreementStart(String agreementStart) {
		this.agreementStart = agreementStart;
	}

	public String getAgreementEnd() {
		return agreementEnd;
	}

	public void setAgreementEnd(String agreementEnd) {
		this.agreementEnd = agreementEnd;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Double getFundingKaust() {
		return fundingKaust;
	}

	public void setFundingKaust(Double fundingKaust) {
		this.fundingKaust = fundingKaust;
	}

	public Double getFundingColaborator() {
		return fundingColaborator;
	}

	public void setFundingColaborator(Double fundingColaborator) {
		this.fundingColaborator = fundingColaborator;
	}

	public Double getTotalFunding() {
		return totalFunding;
	}

	public void setTotalFunding(Double totalFunding) {
		this.totalFunding = totalFunding;
	}

	public Double getKaustContribution() {
		return kaustContribution;
	}

	public void setKaustContribution(Double kaustContribution) {
		this.kaustContribution = kaustContribution;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreawardLead() {
		return preawardLead;
	}

	public void setPreawardLead(String preawardLead) {
		this.preawardLead = preawardLead;
	}

	public String getContractsLead() {
		return contractsLead;
	}

	public void setContractsLead(String contractsLead) {
		this.contractsLead = contractsLead;
	}

	public String getPostawardLead() {
		return postawardLead;
	}

	public void setPostawardLead(String postawardLead) {
		this.postawardLead = postawardLead;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public PrincipalInvestigator getPi() {
		return pi;
	}

	public void setPi(PrincipalInvestigator pi) {
		this.pi = pi;
	}

	public OcrfAward getAward() {
		return award;
	}

	public void setAward(OcrfAward award) {
		this.award = award;
	}
}
