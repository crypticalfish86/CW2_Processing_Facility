package com.example.coursework_2;

public class MedicalParcel extends Parcel{
    private final static double COST = 3; /*cost factor for this type of parcel*/
    private int biohazardLevel; /*What biohazard risk the parcel has*/

    /**
     *Constructor for MedicalParcel
     * @param sender
     * Where the package is coming from
     * @param recipient
     * Where the package is going
     * @param biohazardLevel
     * The current biohazard level of the package
     */
    public MedicalParcel(ProcessingFacility sender, ProcessingFacility recipient, int biohazardLevel){
        super(sender, recipient, 1);
        this.biohazardLevel = biohazardLevel;
    }

    /**
     * getter for biohazardLevel
     * @return
     * The current biohazardLevel of the package
     */
    public int getBiohazardLevel() {
        return biohazardLevel;
    }

    /**
     * process the package by setting its price and updating biohazard level
     * @param facility
     * The processing facility the package is a part of
     */
    @Override
    public void process(ProcessingFacility facility){
        this.price = COST * biohazardLevel;
        this.biohazardLevel++;
    }

    /**
     * overridden toString which gives basic information about the package
     * @return
     * A string of basic information about the package
     */
    @Override
    public String toString(){
        String returningString = "Medical Parcel " + super.toString();
        returningString += String.format("Biohazard Level: %s", this.biohazardLevel);
        return returningString;
    }
}
