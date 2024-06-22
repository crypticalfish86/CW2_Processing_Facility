package com.example.coursework_2;

import java.util.ArrayList;

public class TrackedParcel extends Parcel{
    private final static double COST = 1.5; /*cost factor for this type of parcel*/
    private String trackingLocation; /*Current location of the parcel*/
    private final ArrayList<ProcessingFacility> locations; /*Present and past locations of the parcel*/

    /**
     *constructor for tracked parcel
     * @param sender
     * Where the parcel is coming from
     * @param recipient
     * Where the parcel is going
     */
    public TrackedParcel(ProcessingFacility sender, ProcessingFacility recipient){
        super(sender, recipient, 2);
        this.trackingLocation = "";
        this.locations = new ArrayList<ProcessingFacility>();
    }

    /**
     * getter for trackingLocation
     * @return
     * current location
     */
    public String getTrackingLocation() {
        return trackingLocation;
    }

    /**
     * process the package by tracking its current and past location and set the price
     * @param facility
     * The processing facility the package is a part of
     */
    @Override
    public void process(ProcessingFacility facility){
        this.locations.add(facility);
        this.trackingLocation += String.format(", %s", facility.getADDRESS());
        this.price = COST * this.locations.size();
    }

    /**
     * overridden toString which gives basic information about the package
     * @return
     * A string of basic information about the package
     */
    @Override
    public String toString(){
        String returningString = "Tracked Parcel " + super.toString();
        returningString += String.format("Locations: %s", this.getLocationsList());
        return returningString;
    }

    /**
     * private method to use in toString to get all locations parcel has been to
     * @return
     * Locations of all the facilities the parcel has been to
     */
    private String getLocationsList(){
        String returningString = "";
        /*appends all but the last location to the string with a ","*/
        for (int i = 0; i < this.locations.size() - 1; i++){
            returningString += this.locations.get(i).getADDRESS() + ",";
        }
        /*appends the last location to the string if "locations" is not empty*/
        if (!this.locations.isEmpty()){
            returningString += this.locations.get(this.locations.size() - 1).getADDRESS();
        }
        return returningString;
    }
}
