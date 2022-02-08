package io;

import java.util.Scanner;

import commands.CommandWrapper;

import java.time.LocalDate;
import static utils.DateConverter.*;
import data.*;
import exceptions.*;

/**
 * basic implementation of InputManager
 */
public abstract class InputManagerImpl implements InputManager{
    private Scanner scanner;

    public InputManagerImpl(Scanner sc){
        scanner = sc;
        scanner.useDelimiter("\n");
    }

    public Scanner getScanner(){
        return scanner;
    }

    public void setScanner(Scanner sc){
        scanner = sc;
    }
    public String readName() throws EmptyStringException{
        String s = scanner.nextLine().trim();
        if (s.equals("")){
            throw new EmptyStringException();
        }
        return s;
    }

    public String readFullName(){
        String s = scanner.nextLine().trim();
        if (s.equals("")){
            return null;
        }
        return s;
    }

    public float readXCoord() throws InvalidNumberException{
        float x;
        try{
            x = Float.parseFloat(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        if (Float.isInfinite(x) || Float.isNaN(x)) throw new InvalidNumberException("invalid float value");
        return x;
    }

    public Long readYCoord() throws InvalidNumberException{
        Long y;
        try{
            y = Long.parseLong(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        if (y<=-123) throw new InvalidNumberException("must be greater than -123");
        return y;
    }
    public Coordinates readCoords() throws InvalidNumberException{
        float x = readXCoord();
        Long y = readYCoord();
        Coordinates coord = new Coordinates(x,y);
        return coord;
    }

    public long readSalary() throws InvalidNumberException{
        Long s;
        try{
            s = Long.parseLong(scanner.nextLine().trim());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }

        if(s<=0) throw new InvalidNumberException("must be greater than 0");

        return s;
    }

    public LocalDate readEndDate() throws InvalidDateFormatException{
        String buf = scanner.nextLine().trim();
        if(buf.equals("")){
            return null;
        }
        else{
            return parseLocalDate(buf);
        }
    }

    public Position readPosition() throws InvalidEnumException{
        String s = scanner.nextLine().trim();
        if(s.equals("")){
            return null;
        } 
        else {
            try{
                return Position.valueOf(s);
            } catch(IllegalArgumentException e){
                throw new InvalidEnumException();
            }
        }
    }

    public Status readStatus() throws InvalidEnumException{
        String s = scanner.nextLine().trim();
        try{
            return Status.valueOf(s);
        } catch(IllegalArgumentException e){
            throw new InvalidEnumException();
        }
    }

    public OrganizationType readOrganizationType() throws InvalidEnumException{
        String s = scanner.nextLine().trim();
        try{
            return OrganizationType.valueOf(s);
        } catch(IllegalArgumentException e){
            throw new InvalidEnumException();
        }
    }

    public Organization readOrganization() throws InvalidDataException{
        String fullName = readFullName();
        OrganizationType orgType = readOrganizationType();
        return new Organization(fullName, orgType);
    }

    public Worker readWorker() throws InvalidDataException{
        Worker worker = null;
        
        String name = readName();     
        Coordinates coords = readCoords();
        long salary = readSalary();
        LocalDate date = readEndDate();
        Position pos = readPosition();
        Status stat = readStatus();
        Organization org = readOrganization();
        worker = new Worker(name, coords, salary, date, pos, stat, org);
        
        return worker;
            
    }

    public CommandWrapper readCommand(){
        String cmd = scanner.nextLine();
        if (cmd.contains(" ")){ //if command has argument
            String arr [] = cmd.split(" ",2);
            cmd = arr[0];
            String arg = arr[1];
            return new CommandWrapper(cmd,arg);
        } else {
            return new CommandWrapper(cmd);
        }
    }
}
