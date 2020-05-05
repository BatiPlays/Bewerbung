package com.homestream.HomeStream.vo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A value object representing an artist (musician, actor, painter, soemthing inbetween, ...)
 * @author Justin Braack
 */
@Entity
@Table(name="Artist")
public class ArtistVO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private long dbid;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="bday")
    private LocalDate bday;

    /**
     * Creates a new ArtistVO instance
     * @param dbid The ID of this VO in the database
     * @param name The name of the artist
     * @param bday When the artist was born
     */
    public ArtistVO(long dbid, String name, LocalDate bday) {
        this.dbid = dbid;
        this.name = name;
        this.bday = bday;
    }

    /**
     * Creates a new ArtistVO instance. <i>Use this if you're unsure about the dbid</i>
     * @param name The name of the artist
     * @param bday When the artist was born
     */
    public ArtistVO(String name, LocalDate bday) {
        this.name = name;
        this.bday = bday;
    }

    protected ArtistVO(){

    }

    public ArtistVO(long dbid, ArtistVO Idless){
        this(dbid, Idless.getName(), Idless.getBday());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistVO artistVO = (ArtistVO) o;
        return name.equals(artistVO.name) &&
                bday.equals(artistVO.bday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bday);
    }

    @Override
    public String toString() {
        return "ArtistVO{" +
                "dbid=" + dbid +
                ", name='" + name + '\'' +
                ", bday=" + bday +
                '}';
    }

    @Override
    public Object clone()
    {
        ArtistVO cloned = new ArtistVO(this.getDbid(), this.getName(), this.getBday());
        return cloned;
    }

    /**
     * Returns the ID of the artist in the database
     * @return The ID of the artist in the database
     */
    public long getDbid() {
        return dbid;
    }

    /**
     * Returns the name of the artist
     * @return The name of the artist
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the artists birthday
     * @return The artists birthday
     */
    public LocalDate getBday() {
        return bday;
    }
}
