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
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Entity
@Table (name = "publications")
@UifAutoCreateViews ({UifAutoCreateViewType.INQUIRY, UifAutoCreateViewType.LOOKUP})
public class PublicationData implements Serializable, DataObject {

    private static final long serialVersionUID = 4768156680246084251L;

    @ManyToOne
    @JoinColumn (name = "name", insertable = false, updatable = false)
    @InheritProperty (name = "name", label = @Label ("Name of PI"), displayHints = @UifDisplayHints ({@UifDisplayHint (value = UifDisplayHintType.NO_LOOKUP_CRITERIA)}))
    PrincipalInvestigator pi;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Label ("ID")
    @Column (name = "id")
    private Integer id;

    @Column (name = "eid", length = 250)
    @Label ("EID")
    private String eid; // EID

    @Column (name = "doi", length = 250)
    @Label ("DOI")
    private String doi; // DOI

    @Column (name = "name", length = 50)
    @Label ("KAUST Author")
    private String name; // PI's KAUST ID (DB Key)

    @Column (name = "division", length = 250)
    @Label ("Division")
    @KeyValuesFinderClass (DivisionValuesFinder.class)
    private String division; // PI's division

    @Column (name = "program", length = 250)
    @Label ("Program")
    private String program; // PI's program

    @Column (name = "center", length = 250)
    @Label ("Center Affiliation")
    @KeyValuesFinderClass (CenterValuesFinder.class)
    private String center; // PI's center

    @Column (name = "award_number", length = 50)
    @Label ("Award Number")
    private String awardNumber; // Award Number if exists

    @Column (name = "title")
    @Label ("Title")
    private String title; // Title of publication (DB Key)

    @Column (name = "date")
    @Temporal (TemporalType.DATE)
    @Label ("Date")
    private Date date; // Year of publication

    @Column (name = "source_title", length = 250)
    @Label ("Source title")
    private String source; // Source

    @Column (name = "volume", length = 250)
    @Label ("Volume")
    private String volume; // Volume

    @Column (name = "issue", length = 250)
    @Label ("Issue")
    private String issue; // Issue

    @Column (name = "article_number", length = 250)
    @Label ("Art. No.")
    private String articleNumber; // Article number

//    @Column (name = "page_start", length = 10)
//    @Label("Page start")
//    private String pageStart;
//
//    @Column (name = "page_end", length = 10)
//    @Label("Page end")
//    private String pageEnd;
//
//    @Column (name = "page_count", length = 10)
//    @Label("Page count")
//    private String pageCount;

    @Column (name = "cited_by")
    @Label ("Cited by")
    private Integer citedBy; // Number of citations

    @Column (name = "type", length = 250)
    @Label ("Document type")
    private String type; // Type of publication

    @Column (name = "authors")
    @Label ("Authors")
    private String authors; // All Authors

    @Column (name = "affiliations")
    @Label ("Affiliations")
    private String affiliations; // All affiliations

    @Column (name = "authors_affiliations")
    @Label ("Authors with affiliations")
    private String authorsAffiliations; // All authors with affiliations

    @NonPersistentProperty
    @Label("Page Start")
    public String getPageStart() {
        return " ";
    }

    @NonPersistentProperty
    @Label("Page End")
    public String getPageEnd() {
        return " ";
    }

    @NonPersistentProperty
    @Label("Page Count")
    public String getPageCount() {
        return " ";
    }

    @NonPersistentProperty
    @Label("Source")
    public String getScopusSource() {
        return "Scopus";
    }

    @NonPersistentProperty
    @Label("Abstract")
    public String getAbstract() {
        return " ";
    }

    @NonPersistentProperty
    @Label("Link")
    public String getLink() {
        return " ";
    }

    @NonPersistentProperty
    @Label("Year")
    public int getYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getDate());
        return calendar.get(Calendar.YEAR);
    }

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

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getAuthorsAffiliations() {
        return authorsAffiliations;
    }

    public void setAuthorsAffiliations(String authorsAffiliations) {
        this.authorsAffiliations = authorsAffiliations;
    }

    @Override
    public void addToCollection(Object obj, Map<String, Object> results) {
        PublicationData pd = (PublicationData) obj;
        if (!results.containsKey(pd.getId())) results.put(pd.getId().toString(), pd);
    }

    @Override
    public String[] getValues() {
        String[] values = {"name"};
        return values;
    }
}
