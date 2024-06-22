package com.example.coursework_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

/**
 * Class to hold data that is added to the "database".
 * You may add methods to this class.
 */
public class Data {

    /**
     * Attributes to save our data to the "database"
     */
    private static ArrayList<ProcessingFacility> processingFacilities = new ArrayList<>();
    private static Stack<Parcel> parcels = new Stack<>();
    private static Stack<Parcel> processedParcels = new Stack<>();

    /**
     * Method to return the Stack of parcels
     * @return parcels which is a stack of parcels
     */
    public static Stack<Parcel> getParcels(){
        return parcels;
    }

    /**
     * Method to return the processing facilities
     * @return array list of processing facilities
     */
    public static ArrayList<ProcessingFacility> getProcessingFacilities(){
        return processingFacilities;
    }

    /**
     * Method to return the processed parcels.
     * @return stack of parcels called completed deliveries.
     */
    public static Stack<Parcel> getProcessedParcels(){
        return processedParcels;
    }

    /**
     * DO NOT EDIT ANY CODE ABOVE THIS COMMENT. You may need to write additional methods below.
     */

    /**
     * Finds and returns a processing facility by its address
     * @param addressOfFacilityToFind
     * The address of the facility you want to locate
     * @return
     * The processing facility at that address (or null if none)
     */
    public static ProcessingFacility findProcessingFacility(String addressOfFacilityToFind){
        for (ProcessingFacility facility: processingFacilities){
            if (facility.getADDRESS().equals(addressOfFacilityToFind)){
                return facility;
            }
        }
        return null;
    }

    /**
     * Finds and returns a processing facility by its ID
     * @param identificationNumberOfFacilityToFind
     * The ID number of the facility you want to locate
     * @return
     * The processing facility at that id number (or null if none)
     */
    public static ProcessingFacility findProcessingFacility(int identificationNumberOfFacilityToFind){
        for (ProcessingFacility facility: processingFacilities){
            if (facility.getID() == identificationNumberOfFacilityToFind){
                return facility;
            }
        }
        return null;
    }

    /**
     * reads processingFacilities.txt, creates facilites and appends them all to processingFacilities list
     * @throws FileNotFoundException
     * thrown if processingFacilities.txt isn't found
     */
    public static void readProcessingFacilities() throws FileNotFoundException {
        File inputFile = new File("processingFacilities.txt");
        Scanner in = new Scanner(inputFile);
        /*While processingFacilities.txt still has coords to read this will loop*/
        while (in.hasNextInt()){
            int x = in.nextInt();
            int y = in.nextInt();
            ProcessingFacility facility = new ProcessingFacility(x, y);
            Data.getProcessingFacilities().add(facility);
        }
        in.close();
    }

    /**
     *reads paired addresses from connections.txt and adds each one as neighbours to their facility class
     * @throws FileNotFoundException
     * Thrown if connections.txt isn't found
     */
    public static void readConnections() throws FileNotFoundException {
        File inputFile = new File("connections.txt");
        Scanner in = new Scanner(inputFile);
        /*While connections.txt still has addresses to read this will loop*/
        while (in.hasNextLine()){
            String[] addressPair = in.nextLine().split(" ");
            String address1 = addressPair[0];
            String address2 = addressPair[1];
            ProcessingFacility facility1 = findProcessingFacility(address1);
            ProcessingFacility facility2 = findProcessingFacility(address2);
            /*If both facilities are found they will add each other as neighbours*/
            if (facility1 != null && facility2 != null){
                facility1.addNeighbour(facility2);
                facility2.addNeighbour(facility1);
            }
        }
        in.close();
    }

    /**
     * Reads all parcels in parcels.csv and adds them to the parcel stack
     * @throws FileNotFoundException
     * Thrown if parcels.csv isn't found
     */
    public static void readParcels() throws FileNotFoundException {
        File inputFile = new File("parcels.csv");
        Scanner in = new Scanner(inputFile);
        /*While parcels.csv still has lines to read this will loop*/
        while (in.hasNextLine()){
            String[] facilityInformation = in.nextLine().split(",");
            ProcessingFacility sender = findProcessingFacility(facilityInformation[0]);
            ProcessingFacility recipient = findProcessingFacility(facilityInformation[1]);
            /*Next part of the line determines what happens with the next bit of information before adding to stack*/
            if (facilityInformation[2].equals("Standard")){
                double weight = Double.parseDouble(facilityInformation[3]);
                Parcel parcel = new StandardParcel(sender, recipient, weight);
                insertStack(parcel);
            } else if (facilityInformation[2].equals("Medical")){
                int biohazardLevel = Integer.parseInt(facilityInformation[3]);
                Parcel parcel = new MedicalParcel(sender, recipient, biohazardLevel);
                insertStack(parcel);
            } else{
                Parcel parcel = new TrackedParcel(sender, recipient);
                insertStack(parcel);
            }
        }

        in.close();
    }

    /**
     *Private method for "readParcels" Inserts the parcel into the correct position in the stack
     * @param parcel
     * The parcel to be inserted into the stack
     */
    private static void insertStack(Parcel parcel){
        Stack<Parcel> tempStack = new Stack<Parcel>();
        /*pop and add to a temp stack until find package with lower priority*/
        while (!Data.parcels.isEmpty() && parcel.getPriority() > Data.parcels.peek().getPriority()){
            tempStack.push(Data.parcels.pop());
        }
        Data.parcels.push(parcel);

        /*re-push all parcels to Data.parcels*/
        while(!tempStack.isEmpty()){
            Data.parcels.push(tempStack.pop());
        }
    }
}