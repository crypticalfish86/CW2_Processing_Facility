package com.example.coursework_2;

public class StandardParcel extends Parcel{
    private final static double COST = 1.25; /*cost factor for this type of parcel*/
    private final double WEIGHT_KG;/*Weight in KG of this parcel*/

    /**
     * Constructor for a standard parcel
     * @param sender
     * Where the parcel is coming from
     * @param recipient
     * Where the parcel is going
     * @param weight
     * The weight(kg) of the parcel
     */
    public StandardParcel(ProcessingFacility sender, ProcessingFacility recipient, double weight){
        super(sender, recipient, 3);
        this.WEIGHT_KG = weight;
    }

    /**
     * getter for weight
     * @return
     * the weight of the parcel
     */
    public double getWEIGHT_KG() {
        return WEIGHT_KG;
    }

    /**
     *process the package by setting the price of the package
     * @param processingFacility
     * The processing facility the package is a part of
     */
    @Override
    public void process(ProcessingFacility processingFacility){
        this.price = COST * WEIGHT_KG;
    }

    /**
     * overridden toString which gives basic information about the package
     * @return
     * A string of basic information about the package
     */
    @Override
    public String toString(){
        String returningString = "Standard Parcel " + super.toString();
        returningString += String.format("Weight: %.2f", this.WEIGHT_KG);
        return returningString;
    }
}
