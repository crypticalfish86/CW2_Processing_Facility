package com.example.coursework_2;

import java.util.ArrayList;

/**
 * Class to represent a ProcessingFacility object.
 * You will heavily edit this class and therefore no further comments have been added.
 * Ensure you add comments when submitting.
 */
public class ProcessingFacility {

    protected final int ID;
    private static int nextId = 1;
    private final int X;
    private final int Y;
    private final String ADDRESS;
    private final ArrayList<ProcessingFacility> neighbours;

    /**
     *Constructor for processing facility
     * @param x
     * X location in space
     * @param y
     * Y location in space
     */
    public ProcessingFacility(int x, int y){
        ID = nextId;
        nextId++;
        this.X = x;
        this.Y = y;
        this.neighbours = new ArrayList<ProcessingFacility>();
        this.ADDRESS = String.format("X%dY%d", this.X, this.Y);
    }

    /**
     *getter for X
     * @return
     * X location in space
     */
    public int getX() {
        return this.X;
    }

    /**
     *getter for Y
     * @return
     * Y location in space
     */
    public int getY() {
        return this.Y;
    }

    /**
     * getter for address
     * @return
     * Full address for location in space (x,y coords)
     */
    public String getADDRESS(){
        return this.ADDRESS;
    }

    /**
     * getter for ID
     * @return
     * The ID number for the processing facility
     */
    public int getID(){
        return this.ID;
    }

    /**
     *Getter for neighbours list
     * @return
     * array list containing location of all neighbour facilities in space
     */
    public ArrayList<ProcessingFacility> getNeighbours() {
        return this.neighbours;
    }

    /**
     *Appends a processing facility to a facilities list of neighbours
     * @param processingFacility
     * The processing facility to be appended
     */
    public void addNeighbour(ProcessingFacility processingFacility){
        this.neighbours.add(processingFacility);
    }

    /**
     * overridden toString which now gives information on the facility
     * @return
     * returns the address of this processing facility and all of it's neighbours
     */
    @Override
    public String toString(){
        return String.format("Address:%s Neighbours:%s", this.ADDRESS, this.returnNeighbours());
    }

    /**
     *private method to return information on the facilities neighbours
     * @return
     * returns the address of all neighbouring processing facilities
     */
    private String returnNeighbours(){
        String returningString = "";
        for (int i = 0; i < this.neighbours.size() - 1; i++){
            returningString += this.neighbours.get(i).getADDRESS() + ",\n";
        }
        returningString += this.neighbours.get(this.neighbours.size() - 1).getADDRESS();
        return returningString;
    }
}
