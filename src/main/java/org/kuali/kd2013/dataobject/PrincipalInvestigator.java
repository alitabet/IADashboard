package org.kuali.kd2013.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.kuali.rice.krad.data.provider.annotation.KeyValuesFinderClass;
import org.kuali.rice.krad.data.provider.annotation.Label;
import org.kuali.rice.krad.data.provider.annotation.NonPersistentProperty;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHint;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHintType;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHints;

@Entity
@Table(name="kaust_pi")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY, UifAutoCreateViewType.LOOKUP})
public class PrincipalInvestigator implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "name",length=250)
    @Label("Name")
	private String name; // PI's name
    
    @NonPersistentProperty
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getLastName() {
		Scanner sc = new Scanner(name);
		String lastName = null;
		while(sc.hasNext()) {
			lastName = sc.next();
		}
		sc.close();
		return lastName;
	}
    
    @Column(name = "pi_id_number",length=50)
    @Label("ID Number")
    private String idNumber; // PI's KAUST ID
    
    @Column(name = "email",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Email")
	private String email; // PI's email
    
    @Column(name = "website",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Website")
	private String website; // PI's website
    
    @Column(name = "rank",length=250)
    @Label("Rank")
    @KeyValuesFinderClass(FacultyRankValuesFinder.class)
	private String rank; // PI's rank
    
    @Column(name = "division",length=250)
    @Label("Division")
    @KeyValuesFinderClass(DivisionValuesFinder.class)
	private String division; // PI's division
    
    @Column(name = "program",length=250)
    @UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	})
    @Label("Program")
	private String program; // PI's program
    
    @Column(name = "center",length=250)
    @Label("Center Affiliation")
    @KeyValuesFinderClass(CenterValuesFinder.class)
	private String center; // PI's center
    
    @Column(name="number_publications")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    @Label("Number of Publications") 
	private int number_publications; // Round of call
    
    @Column(name="h_index")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    @Label("H-index") 
	private int hIndex; // Round of call
    
    @Column(name="cited_by")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    @Label("Cited-by Count") 
	private int citedBy; // Round of call
    
    @Column(name="citations")
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
    @Label("Citations Count") 
	private int citations; // Round of call
    
    @NonPersistentProperty
    @Label("Scopus ID(s)")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getScopusIDString() {
		StringBuilder sb = new StringBuilder();
		if ( scopusID != null ) {
			for ( ScopusPI si : scopusID ) {
				sb.append( si.getScopusID() );
				sb.append( "\n" );
			}
		}
		return sb.toString();
	}
    
    @NonPersistentProperty
    @Label("Publications")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public Integer getScopusNumberPublications() {
		Integer temp = 0;
		if ( publications != null ) {
			temp = publications.size();
		}
		return temp;
	}
    
    @NonPersistentProperty
    @Label("Cited By")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public Integer getScopusCitedBy() {
    	Integer temp = 0;
		if ( publications != null ) {
			for (PublicationData tempPub : publications) {
				temp = temp + tempPub.getCitedBy();
			}
		}
		return temp;
	}
    
    @NonPersistentProperty
    @Label("H-index")
	@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public Integer getScopusHIndex() {
    	Integer temp = 0;
		if ( scopusID != null ) {
			List<Integer> allCitations = new ArrayList<Integer>();
			for (PublicationData tempPub : publications) {
				allCitations.add(tempPub.getCitedBy());
			}
			Collections.sort(allCitations);
			for (int i = 1; i <= allCitations.size(); i++) {
				List<Integer> subList = allCitations.subList(allCitations.size() - i, allCitations.size());
				for (Integer cit : subList) {
					if (cit < i) {
						Integer hIndex = i - 1;
						return hIndex;
					}
				}
			}
			return temp;
		}
		else {
			return temp; 
		}
	}
    
    @OneToMany(fetch= FetchType.EAGER, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy = "pi")
    protected List<OcrfAward> awards = new ArrayList<OcrfAward>(); // List of awards
    
    @OneToMany(fetch= FetchType.EAGER, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy = "pi")
    protected List<OcrfSubAward> subAwards = new ArrayList<OcrfSubAward>(); // List of subwards
    
    @OneToMany(fetch= FetchType.EAGER, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy = "pi")
    protected List<Contract> contracts = new ArrayList<Contract>();
    
    @OneToMany(fetch= FetchType.EAGER, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy = "pi")
    protected List<ScopusPI> scopusID = new ArrayList<ScopusPI>(); // List of scopus IDs
    
    @OneToMany(fetch= FetchType.EAGER, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy = "pi")
    protected List<PublicationData> publications = new ArrayList<PublicationData>(); // List of publications
    
    @ManyToMany(fetch= FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinTable(
        name="ip_faculty",
        joinColumns={@JoinColumn(name="name", referencedColumnName="name")},
        inverseJoinColumns={@JoinColumn(name="publication_number", referencedColumnName="publication_number")})
    private List<PatentData> patents = new ArrayList<PatentData>();
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
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

	public List<OcrfAward> getAwards() {
		return awards;
	}

	public void setAwards(List<OcrfAward> awards) {
		this.awards = awards;
	}

	public List<OcrfSubAward> getSubAwards() {
		return subAwards;
	}

	public void setSubAwards(List<OcrfSubAward> subAwards) {
		this.subAwards = subAwards;
	}

	public List<ScopusPI> getScopusID() {
		return scopusID;
	}

	public void setScopusID(List<ScopusPI> scopusID) {
		this.scopusID = scopusID;
	}

	public List<PatentData> getPatents() {
		return patents;
	}

	public void setPatents(List<PatentData> patents) {
		this.patents = patents;
	}

	public List<PublicationData> getPublications() {
		return publications;
	}

	public void setPublications(List<PublicationData> publications) {
		this.publications = publications;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contract) {
		this.contracts = contract;
	}
	
}
