package com.userapi.model;

import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "usersDetails",uniqueConstraints = @UniqueConstraint(columnNames = "gmail"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "Firstname not be null")
    private String firstName;
    @NotNull(message = "LastName not be null")
    private String lastName;
    @Email(message = "Provide a valid Email address")
    private String gmail;
    @Pattern(regexp="(^$|[0-9]{10})")
    @NotNull(message = "Message is not null")
    private String mobileNumber;
    private Date date;

    public User(long id, String firstName, String lastName, String gmail, String mobileNumber, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gmail = gmail;
        this.mobileNumber = mobileNumber;
        this.date = new Date();
    }
    public User(){
        super();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDate() {
        return new Date();
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
