package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Worker class
 */
public class Worker implements Collectionable{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long salary; //Значение поля должно быть больше 0
    private java.time.LocalDate endDate; //Поле может быть null
    private Position position; //Поле может быть null
    private Status status; //Поле не может быть null
    private Organization organization; //Поле может быть null

    /**
     * constructor, just set fields
     * @param name
     * @param coordinates
     * @param salary
     * @param endDate
     * @param position
     * @param status
     * @param organization
     */
    public Worker(String name, Coordinates coordinates, Long salary, LocalDate endDate, Position position, Status status, Organization organization){
        creationDate = new java.util.Date();
        
        this.name = name;
        this.coordinates = coordinates;
        this.salary = salary;
        this.endDate = endDate;
        this.position = position;
        this.status = status;
        this.organization = organization;
    }

    
    /** 
     * @return int
     */
    public int getId(){
        return id;
    }

    
    /** 
     * sets ID, usefull for replacing elements in collection
     * @param ID
     */
    public void setId(int ID){
        id = ID;
    }

    
    /** 
     * @return String
     */
    public String getName(){
        return name;
    }

    
    /** 
     * @return long
     */
    public long getSalary(){
        return salary;
    }
    
    /** 
     * @return LocalDate
     */
    public LocalDate getEndDate(){
        return endDate;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strCreationDate = dateFormat.format(creationDate);
        String strEndDate = "";
        if (endDate!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            strEndDate = endDate.format(formatter);
        }
        String s = "";
        s += "{\n";
        s += "  \"id\" : " + Integer.toString(id) + ",\n";
        s += "  \"name\" : " + "\"" + name + "\"" + ",\n";
        s += "  \"coordinates\" : " + coordinates.toString() + ",\n";
        s += "  \"creationDate\" : " + "\"" + strCreationDate + "\"" + ",\n";
        s += "  \"salary\" : " + Long.toString(salary) + ",\n";
        if (endDate!=null) s += "  \"endDate\" : " +  "\"" + strEndDate + "\"" + ",\n";
        if (position!=null) s += "  \"position\" : " + "\"" + position.toString() + "\"" + ",\n";
        s += "  \"status\" : " + "\"" + status.toString() + "\"" + ",\n";
        s += "  \"organization\" : " + organization.toString() + "\n";
        s += "}";
        return s;
    }
    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        Worker another = (Worker)obj;
        return this.getId() == another.getId();
    }

    
    /** 
     * @param worker
     * @return int
     */
    public int compareTo(Collectionable worker){
        int res = Long.compare(this.salary, worker.getSalary());
        return res;
    }

    
    /** 
     * @return boolean
     */
    public boolean validate(){
        return (
            coordinates!=null && coordinates.validate() &&
            (organization==null ||(organization!=null && organization.validate())) && 
            (salary>0) && (id>0) && 
            name!=null && !name.equals("") &&
            status!=null &&
            creationDate!=null
        );

    }
}
