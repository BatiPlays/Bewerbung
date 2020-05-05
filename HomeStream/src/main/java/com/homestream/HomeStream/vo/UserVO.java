package com.homestream.HomeStream.vo;

import javax.persistence.*;
import java.util.Objects;

/**
 * Represents a user of HomeStream
 * @author JustinBraack
 */
@Entity
@Table(name="User")
public class UserVO implements IValueObject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long dbid;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    /**
     * Creates a new user of HomeStream
     * @param dbid The ID of the user in the DB
     * @param name The user's name
     * @param email The email address of the user
     * @param password The <b>hashed</b> password of the user
     */
    public UserVO(long dbid, String name, String email, String password) {
        this.dbid = dbid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserVO(long dbid, UserVO Idless){
        this(dbid, Idless.getName(), Idless.getEmail(), Idless.getPassword());
    }

    protected UserVO(){

    }

    /**
     * Gets the name of the user
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email address
     * @return The user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the <b>hashed</b> password of the user
     * @return The <b>hashed</b> password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the ID of this VO in the database
     * @return The ID of this VO in the database
     */
    public long getDbid() {
        return dbid;
    }

    @Override
    public boolean equals(Object o)
    {
        UserVO casted;

        if (o == null){
            return false;
        }
        if (this == o){
            return true;
        }
        if (this.getClass() != o.getClass())
        {
            return false;
        }

        casted = (UserVO)o;

        if(this.hashCode() != casted.hashCode()){
            return false;
        }
        if(!this.getEmail().equals(casted.getEmail())){
            return false;
        }
        if(!this.getName().equals(casted.getName())){
            return false;
        }
        if(!this.getPassword().equals(casted.getName())){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "dbid=" + dbid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Object clone() {
        UserVO clone = new UserVO(this.getDbid(), this.getName(), this.getEmail(), this.getPassword());
        return clone;
    }


}
