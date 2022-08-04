package com.fsfb.branchregister.branchregister.model;


import com.fsfb.branchregister.branchregister.Utils.Constants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_master")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    Long id;
//    @NotBlank(message = Constants.EMPTY_VALUE)
//    @Size(min = 3, max=10 , message = "FirstName - ${validatedValue} should be between {min}  and {max} chars")
//    @Pattern(regexp="^[a-zA-Z][ ]*$", message="Enter valid FirstName - ${validatedValue}")
    String first_name;
//    @NotBlank(message = Constants.EMPTY_VALUE)
//    @Size(min = 3, max=10 , message = "FirstName - ${validatedValue} should be between {min}  and {max} chars")
//    @Pattern(regexp="^[a-zA-Z][ ]*$", message="Enter valid FirstName - ${validatedValue}")
    String last_name;
//    @NotBlank(message = Constants.EMPTY_VALUE)
    String branch_id;
//    @NotBlank(message = Constants.EMPTY_VALUE)
    String branch_name;
//    @Enumerated(EnumType.STRING)
    UserRole user_role;
//    @CreationTimestamp
//    @Generated(GenerationTime.ALWAYS)
//    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdDate;
//    @CreationTimestamp
//    @Generated(GenerationTime.ALWAYS)
//    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime modifiedDate;

    public User() {
        this.createdDate= LocalDateTime.now();
        this.modifiedDate= LocalDateTime.now();
    }

    public User(Long id, String first_name, String last_name, String branch_id, String branch_name, UserRole user_role, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.branch_id = branch_id;
        this.branch_name = branch_name;
        this.user_role = user_role;
        this.createdDate= LocalDateTime.now();
        this.modifiedDate= LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public UserRole getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRole user_role) {
        this.user_role = user_role;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(branch_id, user.branch_id) && Objects.equals(branch_name, user.branch_name) && user_role == user.user_role && Objects.equals(createdDate, user.createdDate) && Objects.equals(modifiedDate, user.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, branch_id, branch_name, user_role, createdDate, modifiedDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", branch_id='" + branch_id + '\'' +
                ", branch_name='" + branch_name + '\'' +
                ", user_role=" + user_role +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}

enum UserRole{
    BOM, ROM, DOM, BC, ZH
}