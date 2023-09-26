package com.userapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usersDetails",uniqueConstraints = @UniqueConstraint(columnNames = "gmail"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull(message = "Firstname not be null")
    private String firstName;
    @NotNull(message = "LastName not be null")
    private String lastName;
    @Email(message = "Provide a valid Email address")
    private String gmail;
    @Pattern(regexp="(^$|[0-9]{10})")
    @NotNull(message = "Message is not null")
    private String mobileNumber;
    private String password;
    private String name;
    @CreationTimestamp
    private Date date;

    public User(Long id, String firstName, String lastName, String gmail, String mobileNumber, String password, String name, Date date, List<UserRole> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gmail = gmail;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.name = name;
        this.date = date;
        this.roles = roles;
    }

    public User(){
        super();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name="id",referencedColumnName ="id"),
    inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))

    private List<UserRole> roles=new ArrayList<>();

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
