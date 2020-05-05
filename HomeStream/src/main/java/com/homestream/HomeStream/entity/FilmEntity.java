package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.ArtistVO;
import com.homestream.HomeStream.vo.UserVO;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

/**
 * An entity that represents everything between a homemade film to a blockbuster movie
 * @author Justin Braack
 */
@Entity
@Table(name="Film")
public class FilmEntity extends MediaEntity {

    @ManyToMany
    @JoinTable(name="__FilmMainActorTable",
        joinColumns = @JoinColumn(name="Artist_ID"),
        inverseJoinColumns = @JoinColumn(name="Film_ID"))
    @JoinColumn(name="ID")
    private List<ArtistVO> mainActors;
    @ManyToMany
    @JoinTable(name="__FilmSideActorTable",
        joinColumns = @JoinColumn(name="Artist_ID"),
        inverseJoinColumns = @JoinColumn(name="Film_ID"))
    @JoinColumn(name="ID")
    private List<ArtistVO> sideActors;
    @Column(name="length")
    private LocalTime length;

    /**
     * Createa a new FilmEntitiy.
     * @param id The ID of this entity
     * @param name The name of the movie
     * @param releaseDate On which date it was released
     * @param fileName Where in the file system it can be found
     * @param fileSize How big the file is
     * @param ownedBy Who uploaded it
     * @param accessibleBy Which role can access this film/movie?
     * @param thumbnailName Where in the file system is the thumbnail for this?
     * @param tags The tags for this
     * @param uploadedOn When the media was uploaded to the server
     * @param lastStreamed When the media was last streamed
     * @param mainActors The main actors in this movie
     * @param sideActors The other actors in this movie
     * @param length How long it is
     */
    public FilmEntity(long id, String name, Date releaseDate, Date lastUpdated, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags, Date uploadedOn, Date lastStreamed, List<ArtistVO> mainActors, List<ArtistVO> sideActors, LocalTime length) {
        super(id, name, releaseDate, lastUpdated, fileName, fileSize, ownedBy, accessibleBy, thumbnailName, tags, uploadedOn, lastStreamed);
        this.mainActors = mainActors;
        this.sideActors = sideActors;
        this.length = length;
    }

    /**
     * Creates a new FilmEntity instance. <i>Use this if you don't know the id, or when it was last updated</i>
     * @param name The name of the movie
     * @param releaseDate On which date it was released
     * @param fileName Where in the file system it can be found
     * @param fileSize How big the file is
     * @param ownedBy Who uploaded it
     * @param accessibleBy Which role can access this film/movie?
     * @param thumbnailName Where in the file system is the thumbnail for this?
     * @param tags The tags for this
     * @param lastStreamed When the media was last streamed
     * @param length How long it is
     * @param mainActors The main actors in this movie
     * @param sideActors The other actors in this movie
     */
    public FilmEntity(String name, Date releaseDate, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags, Date lastStreamed, List<ArtistVO> mainActors, List<ArtistVO> sideActors, LocalTime length) {
        super(name, releaseDate, fileName, fileSize, ownedBy, accessibleBy, thumbnailName, tags, new Date(Calendar.getInstance().getTimeInMillis()), lastStreamed);
        this.mainActors = mainActors;
        this.sideActors = sideActors;
        this.length = length;
    }

    public FilmEntity(long id, FilmEntity Idless){
        this(id, Idless.getName(), Idless.getReleaseDate(), new Date(Calendar.getInstance().getTimeInMillis()), Idless.getFileName(), Idless.getFileSize(), Idless.getOwnedBy(), Idless.getAccessibleBy(), Idless.getThumbnailName(), Idless.getTags(), Idless.getUploadedOn(), Idless.getLastStreamed(), Idless.getMainActors(), Idless.getSideActors(), Idless.getLength());
    }

    protected FilmEntity() {

    }

    /**
     * Gets the list of main actors of this movie/film
     * @return the list of main actors of this movie/film
     */
    public List<ArtistVO> getMainActors() {
        return mainActors;
    }

    /**
     * Sets the list of main actors of this movie/film. Automatically updates the <code>lastUpdated</code> as well.
     * @param mainActors The new list of main actors for this movie/film
     */
    public void setMainActors(List<ArtistVO> mainActors) {
        this.mainActors = mainActors;
        this.setLastUpdated();
    }

    /**
     * Gets the list of side actors of this movie/film.
     * @return the list of side actors of this movie/film
     */
    public List<ArtistVO> getSideActors() {
        return sideActors;
    }

    /**
     * Sets the list of side actors of this movie/film. Automatically updates the <code>lastUpdated</code> as well.
     * @param sideActors The new list of side actors for this movie/film
     */
    public void setSideActors(List<ArtistVO> sideActors) {
        this.sideActors = sideActors;
        this.setLastUpdated();
    }

    /**
     * Gets the length of the movie/film
     * @return The length of the movie/film, as a dedicated LocalTime object
     */
    public LocalTime getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; //This is enough, because we only need ID checking - and that's already done in the super class
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //The hash code shall only be dependant on teh ID, and that's alredy done in teh super class
    }

    @Override
    public String toString() {
        return "FilmEntitiy{" +
                super.toString() +
                ", mainActors=" + mainActors +
                ", sideActors=" + sideActors +
                ", length=" + length +
                '}';
    }
}
