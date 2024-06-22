package com.example.coursework_2;

/**
 * Class to represent a Parcel object.
 * You will heavily edit this class and therefore no further comments have been added.
 * Ensure you add comments when submitting.
 */
public abstract class Parcel{
    private static int nextId = 1;/*Constant value to increment and assign an ID*/
    protected ProcessingFacility sender;/*Where the parcel is coming from*/
    protected ProcessingFacility recipient;/*Where the parcel is going*/
    protected double price;/*Current price of the parcel*/
    protected int priority;/*The priority level the parcel has*/
    protected int ID;/*ID number of the specific parcel*/

    /**
     *Constructor for parcel
     * @param sender
     * Where the package was sent from
     * @param recipient
     * Where the package is going
     * @param priority
     * The priority of the package
     */
    public Parcel(ProcessingFacility sender, ProcessingFacility recipient, int priority){
        this.sender = sender;
        this.recipient = recipient;
        this.priority = priority;
        this.ID = nextId;
        nextId++;
    }

    /**
     *getter for sender
     * @return
     * Where the package was sent from
     */
    public ProcessingFacility getSender() {
        return this.sender;
    }

    /**
     *getter for recipient
     * @return
     * Where the package is going
     */

    public ProcessingFacility getRecipient() {
        return this.recipient;
    }

    /**
     * getter for price
     * @return
     * The price of the package
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * getter for priority
     * @return
     * The priority of the package
     */
    public int getPriority() {
        return priority;
    }

    /**
     * getter for id
     * @return
     * the id of the package
     */
    public int getID() {
        return ID;
    }

    /**
     *Abstract method to process the package (complete in subclass methods)
     * @param processingFacility
     * The processing facility the package is a part of
     */
    public abstract void process(ProcessingFacility processingFacility);

    /**
     * To string override which gives basic information about package (subclasses will expand this)
     * @return
     * A string of basic information about the package
     */
    @Override
    public String toString(){
        return String.format("[from %s to %s] - ID: %d Price: %.2f ",
                this.sender.getADDRESS(),
                this.recipient.getADDRESS(),
                this.ID,
                this.price
        );
    }
}
