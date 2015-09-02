package org.kuali.kd2013.dataobject;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.rice.krad.data.provider.annotation.InheritProperty;
import org.kuali.rice.krad.data.provider.annotation.Label;
import org.kuali.rice.krad.data.provider.annotation.NonPersistentProperty;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHint;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHintType;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHints;

@Entity
@Table(name="sub_awards")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY, UifAutoCreateViewType.LOOKUP})
public class OcrfSubAward implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "sub_award_number")
    @GeneratedValue
    @Label("Sub-award Number")
    private String subAwardNumber;

    @Column(name="award_number",length=50)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    @Label("Award Number")
    private String awardNumber;
    
    @Column(name = "name")
    @Label("Name of PI")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    private String name;
    
    @Column(name = "collaborator")
    @Label("Name of External Collaborator")
    private String collaborator;
    
    @Column(name = "institution")
    @Label("Name of Collaborating Institution")
    private String institution;
    
    @Column(name = "amount")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    //@Label("Amount")
    private Double amount;
    
    @Column(name = "amount_contributed")
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    //@Label("Amount Contributed")
    private Double amountContributed;
    
    @NonPersistentProperty
	@Label("Total Amount")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getAmountString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(amount);
	}
    
    @NonPersistentProperty
	@Label("Total Amount Contributed")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getAmountContributedString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(amountContributed);
	}

	@ManyToOne
    @JoinColumn(name = "award_number" ,insertable=false, updatable=false)
	@InheritProperty(name = "awardNumber",label = @Label("Award Number"), displayHints = @UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)}))
	/*@InheritProperties({ 
		@InheritProperty(name = "awardNumber")})*/
    OcrfAward award;
    
    @ManyToOne
	@JoinColumn(name = "name",insertable=false, updatable=false)
    @InheritProperty(name = "name",label = @Label("Name of PI"), displayHints = @UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)}))
    /*@InheritProperties({ 
		@InheritProperty(name = "name"),
		@InheritProperty(name = "idNumber")})*/
    PrincipalInvestigator pi;

	public String getSubAwardNumber() {
		return subAwardNumber;
	}

	public void setSubAwardNumber(String subAwardNumber) {
		this.subAwardNumber = subAwardNumber;
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

	public String getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(String collaborator) {
		this.collaborator = collaborator;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountContributed() {
		return amountContributed;
	}

	public void setAmountContributed(Double amountContributed) {
		this.amountContributed = amountContributed;
	}

	public OcrfAward getAward() {
		return award;
	}

	public void setAward(OcrfAward award) {
		this.award = award;
	}

	public PrincipalInvestigator getPi() {
		return pi;
	}

	public void setPi(PrincipalInvestigator pi) {
		this.pi = pi;
	}
    
}

