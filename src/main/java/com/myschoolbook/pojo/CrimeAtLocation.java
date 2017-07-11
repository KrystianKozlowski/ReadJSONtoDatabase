package com.myschoolbook.pojo;

import javax.persistence.*;

/**
 * Created by Krystian on 2017-05-31.
 */

@Entity
@Table(name="crime")
public class CrimeAtLocation {

    @Id
    private Long id;
    private String category;
    private String location_type;
//    @ManyToOne
//    @JoinTable(name = "location", joinColumns = { @JoinColumn(name = "id") })
@Embedded private Location location;
    private String context;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "outcomestatus", joinColumns = { @JoinColumn(name = "id") })
@Embedded private OutcomeStatus outcome_status;
    private String persistent_id;
    private String location_subtype;
    private String month;

    public CrimeAtLocation(){

    }

    public CrimeAtLocation(Long id, String category, String locationType, Location location, String context, OutcomeStatus outcomeStatus, String persistentId, String locationSubtype, String month) {
        this.id = id;
        this.category = category;
        this.location_type = locationType;
        this.location = location;
        this.context = context;
        this.outcome_status = outcomeStatus;
        this.persistent_id = persistentId;
        this.location_subtype = locationSubtype;
        this.month = month;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocationType() {
        return location_type;
    }

    public void setLocationType(String locationType) {
        this.location_type = locationType;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


    public OutcomeStatus getOutcomeStatus() {
        return outcome_status;
    }

    public void setOutcomeStatus(OutcomeStatus outcomeStatus) {
        this.outcome_status = outcomeStatus;
    }

    public String getPersistentId() {
        return persistent_id;
    }

    public void setPersistentId(String persistentId) {
        this.persistent_id = persistentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationSubtype() {
        return location_subtype;
    }

    public void setLocationSubtype(String locationSubtype) {
        this.location_subtype = locationSubtype;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

}
