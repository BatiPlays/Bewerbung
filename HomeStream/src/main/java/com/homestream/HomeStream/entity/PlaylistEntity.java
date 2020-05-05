package com.homestream.HomeStream.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Representing a playlist, i.e. an assortment of MediaEntities that can be played consecutively
 * @author Justin Braack
 */
@Entity
@Table(name="Playlist")
public class PlaylistEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="createdOn")
    private LocalDate createdOn;
    @Column(name="lastUpdated")
    private LocalDate lastUpdated;
    @Column(name="lastStreamed")
    private LocalDate lastStreamed;
    @ManyToMany
    @JoinTable(name="__PlaylistMediaTable",
        joinColumns = @JoinColumn(name="Playlist_ID"),
        inverseJoinColumns = @JoinColumn(name="Media_ID"))
    @JoinColumn(name="ID")
    private List<MediaEntity> content;

    /**
     * Creates a playlist with emdia that can be consecutively played
     * @param id The ID of this entity
     * @param name The name of the playlist
     * @param createdOn When the playlist was created
     * @param lastUpdated When the playlist was last updated
     * @param lastStreamed When the playlist was most recently streamed
     * @param content The media files in this playlist
     */
    public PlaylistEntity(long id, String name, LocalDate createdOn, LocalDate lastUpdated, LocalDate lastStreamed, List<MediaEntity> content) {
        this.id = id;
        this.name = name;
        this.createdOn = createdOn;
        this.lastUpdated = lastUpdated;
        this.lastStreamed = lastStreamed;
        this.content = content;
    }

    /**
     * Creates a playlist with emdia that can be consecutively played
     * @param name The name of the playlist
     * @param lastUpdated When the playlist was last updated
     * @param lastStreamed When the playlist was most recently streamed
     * @param content The media files in this playlist
     */
    public PlaylistEntity(String name, LocalDate lastUpdated, LocalDate lastStreamed, List<MediaEntity> content) {
        this(-1, name, LocalDate.now(), lastUpdated, lastStreamed, content);
    }

    public PlaylistEntity(long id, PlaylistEntity Idless){
        this(id, Idless.getName(), Idless.getCreatedOn(), Idless.getLastUpdated(), Idless.getLastStreamed(), Idless.getContent());
    }

    protected PlaylistEntity() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public LocalDate getLastStreamed() {
        return lastStreamed;
    }

    public List<MediaEntity> getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
        this.setLastUpdated();
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        this.setLastUpdated();
    }

    protected void setLastUpdated() {
        this.lastUpdated = LocalDate.now();
    }

    /**
     * When the playlist was last streamed. <i>Does not refresh the <code>lastUpdated</code> parameter</i>
     * @param lastStreamed When the playlist was last streamed
     */
    public void setLastStreamed(LocalDate lastStreamed) {
        this.lastStreamed = lastStreamed;
    }

    public void setContent(List<MediaEntity> content) {
        this.content = content;
        this.setLastUpdated();
    }
}
