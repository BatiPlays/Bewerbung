package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.UserVO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * A group of users that can/should access the same files in HomeStream
 * @author Justin Braack
 */
@Entity
@Table(name="Role")
public class RoleEntity implements IEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    @Column(name="name")
    private String name;
    @OneToMany //(mappedBy="User") -- müssen wir ggf noch anfügen
    @JoinColumn(name="Role_ID")
    private List<UserVO> users;

    /**
     * Creates a new RoleEntitiy instance
     * @param id The ID of this entitiy
     * @param name The name of the role (e.g. 'Guests' or 'Family')
     * @param users The list of users initially assigned to this group
     */
    public RoleEntity(long id, String name, List<UserVO> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    /**
     * Creates a new RoleEntitiy instance. <i>Use this if you don't know the id</i>
     * @param name The name of the role (e.g. 'Guests' or 'Family')
     * @param users The list of users initially assigned to this group
     */
    public RoleEntity(String name, List<UserVO> users) {
        this.id = -1;
        this.name = name;
        this.users = users;
    }

    public RoleEntity(long id, RoleEntity roleIdless){
        this(id, roleIdless.getName(), roleIdless.getUsers());
    }

    protected RoleEntity(){

    }

    /**
     * Performs an equality check based on an ID
     * @param o The object that shall be compared with
     * @return Whether the given Object <code>o</code> and this are identical (see above)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Return this object into a string repesentation; useful for development
     * @return A string repesentation of this object
     */
    @Override
    public String toString() {
        return "RoleEntitiy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    /**
     * Returns the ID of this entitiy
     * @return The ID of this entitiy
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of this role
     * @return The name of this role
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the users in this group
     * @return The list of users in this group
     */
    public List<UserVO> getUsers() {
        return users;
    }

    /**
     * Update the name of this group
     * @param name The newly-chosen name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Replace the current list of users with a new one
     * @param users The updated list of users in this group
     */
    public void setUsers(List<UserVO> users) {
        this.users = users;
    }
}
