package io;

import java.time.LocalDate;
import java.util.Scanner;

import commands.CommandWrapper;
import data.*;
import exceptions.*;

public interface InputManager {
    /**
     * reads name from input
     * @return
     * @throws EmptyStringException
     */
    public String readName() throws EmptyStringException;

    /**
     * reads fullName from input
     * @return
     */
    public String readFullName();

    /**
     * reads x from input
     * @return
     * @throws InvalidNumberException
     */
    public float readXCoord() throws InvalidNumberException;

    /**
     * reads y from input
     * @return
     * @throws InvalidNumberException
     */
    public Long readYCoord() throws InvalidNumberException;

    /**
     * reads coordinates from input
     * @return
     * @throws InvalidNumberException
     */
    public Coordinates readCoords() throws InvalidNumberException;

    /**
     * reads salary from input
     * @return
     * @throws InvalidNumberException
     */
    public long readSalary() throws InvalidNumberException;

    /**
     * reads endDate from input
     * @return
     * @throws InvalidDateFormatException
     */
    public LocalDate readEndDate() throws InvalidDateFormatException;

    /**
     * reads position from input
     * @return
     * @throws InvalidEnumException
     */
    public Position readPosition() throws InvalidEnumException;

    /**
     * reads status from input
     * @return
     * @throws InvalidEnumException
     */
    public Status readStatus() throws InvalidEnumException;

    /**
     * reads organizationType from input
     * @return
     * @throws InvalidEnumException
     */
    public OrganizationType readOrganizationType() throws InvalidEnumException;

    /**
     * reads organization from input
     * @return
     * @throws InvalidDataException
     */
    public Organization readOrganization() throws InvalidDataException;

    /**
     * reads Worker from input
     * @return
     * @throws InvalidDataException
     */
    public Worker readWorker() throws InvalidDataException;

    /**
     * reads command-argument pair from input
     * @return
     */
    public CommandWrapper readCommand();

    /**
     * gets input scanner
     * @return
     */
    public Scanner getScanner();
}
