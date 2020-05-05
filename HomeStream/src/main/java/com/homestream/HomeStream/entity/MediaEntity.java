package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.UserVO;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

/**
 * A template class that groups common properties for a media resource together
 * @author Justin Braack
 */
@Inheritance(strategy=InheritanceType.JOINED) //Creates a separate table for this, and all subclasses
@Entity
@Table(name="Media")
public abstract class MediaEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="releaseDate")
    private Date releaseDate;

    @Column(name="lastUpdated")
    private Date lastUpdated;

    @Column(name="fileName")
    private String fileName;

    @Column(name="fileSize")
    private long fileSize;
    @OneToOne
    @JoinColumn(name="User_ID")
    private UserVO ownedBy;
    @ManyToMany
    @JoinTable(name="__MediaRoleTable",
        joinColumns = @JoinColumn(name="Role_ID"),
        inverseJoinColumns = @JoinColumn(name="Media_ID")
    )
    @JoinColumn(name="ID")
    private List<RoleEntity> accessibleBy;

    @Column(name="thumbnailName")
    private String thumbnailName;
    private String tags; //Decided to switch from List<String> to just String to make ORM easier

    @Column(name="uploadedOn")
    private Date uploadedOn;

    @Column(name="lastStreamedOn")
    private Date lastStreamed;

    /**
     * Creates a new MediaEntity template
     * @param id The ID of this media piece
     * @param name The title gven to this media piece
     * @param releaseDate When this media piece was published/created
     * @param lastUpdated When this media piece recieved its last update
     * @param fileName Where it is located in the file system
     * @param fileSize How big this media piece is
     * @param ownedBy Who uploaded the media piece
     * @param accessibleBy By whom this pecia piece can be accessed by
     * @param thumbnailName Where this media piece's thumbnail lies in teh file system
     * @param tags The tags assigned to this media piece
     * @param uploadedOn When this file was uploaded to the server
     * @param lastStreamed When this file was last streamed
     */
    public MediaEntity(long id, String name, Date releaseDate, Date lastUpdated, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags, Date uploadedOn, Date lastStreamed) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.lastUpdated = lastUpdated;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.ownedBy = ownedBy;
        this.accessibleBy = accessibleBy;
        this.thumbnailName = thumbnailName;
        this.setTagsUpdatingLastUpdated(tags, false); //Don't set lastUpdated already by setting the tags in the CTOR
        this.uploadedOn = uploadedOn;
        this.lastStreamed = lastStreamed;
    }

    /**
     * Creates a new MediaEntity template. <i>Use this if you don't know the id, or when it was last updated</i>
     * @param name The title gven to this media piece
     * @param releaseDate When this media piece was published/created
     * @param fileName Where it is located in the file system
     * @param fileSize How big this media piece is
     * @param ownedBy Who uploaded the media piece
     * @param accessibleBy By whom this pecia piece can be accessed by
     * @param thumbnailName Where this media piece's thumbnail lies in teh file system
     * @param tags The tags assigned to this media piece
     * @param uploadedOn When this file was uploaded to the server
     * @param lastStreamed When this file was last streamed
     */
    public MediaEntity(String name, Date releaseDate, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags, Date uploadedOn, Date lastStreamed) {
        this.id = -1;
        this.name = name;
        this.releaseDate = releaseDate;
        this.lastUpdated = new Date(Calendar.getInstance().getTimeInMillis());
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.ownedBy = ownedBy;
        this.accessibleBy = accessibleBy;
        this.thumbnailName = thumbnailName;
        this.setTagsUpdatingLastUpdated(tags, false); //Don't set lastUpdated already by setting the tags in the CTOR
        this.uploadedOn = uploadedOn;
        this.lastStreamed = lastStreamed;
    }

    protected MediaEntity()
    {

    }

    /**
     * Returns this entitiy's ID
     * @return This entitiy's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the hame/title of this media piece
     * @return The hame/title of this media piece
     */
    public String getName() {
        return name;
    }

    /**
     * Gives this media piece a new name/tile. Automatically updates the <code>lastUpdated</code> as well.
     * @param name The updated name/title
     */
    public void setName(String name) {
        this.name = name;
        this.lastUpdated = new Date(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * Returns when this media piece was released/created
     * @return The <code>Date</code> on which this media piece was released/created
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Give this media piece an updated date on which it was released/created, e.g. when it goes from "created" to "published". Automatically updates the <code>lastUpdated</code> as well.
     * @param releaseDate The new date on which it was released/updated
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        this.lastUpdated = new Date(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * Returns when this media piece received it's latest update
     * @return The exact <code>DateTime</code> when the last update occurred
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the lastUpdated to the current DateTime
     */
    protected void setLastUpdated() {
        this.lastUpdated = new Date(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * Returns the location on which this media piece lies within th file system
     * @return The location on which this media piece lies within th file system
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Update the file path on which this media piece lies. Automatically updates the <code>lastUpdated</code> as well.
     * @param fileName The upated path where this media piece can be found
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.setLastUpdated();
    }

    /**
     * Returns the file size of this media piece
     * @return The file size of this media piece
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Returns whoever uploaded this media piece
     * @return Whoever uploaded this media piece
     */
    public UserVO getOwnedBy() {
        return ownedBy;
    }

    /**
     * Sets the new owner of this file. Automatically updates the <code>lastUpdated</code> as well.
     * @param ownedBy The new owner of this file
     */
    public void setOwnedBy(UserVO ownedBy) {
        this.ownedBy = ownedBy;
        this.setLastUpdated();
    }

    /**
     * Returns which group of users can access this media piece
     * @return Which group of users can access this media piece
     */
    public List<RoleEntity> getAccessibleBy() {
        return accessibleBy;
    }

    /**
     * Update the group of users which can access this media piece. Automatically updates the <code>lastUpdated</code> as well.
     * @param accessibleBy The updated <code>RoleEntitiy</code> that can access this media piece
     */
    public void setAccessibleBy(List<RoleEntity> accessibleBy) {
        this.accessibleBy = accessibleBy;
        this.setLastUpdated();
    }

    /**
     * Returns where the thumbnail for this media piece lies within the file system
     * @return Where the thumbnail for this media piece lies within the file system
     */
    public String getThumbnailName() {
        return thumbnailName;
    }

    /**
     * Updates where the thumbnail for this media piece can be found. Automatically updates the <code>lastUpdated</code> as well.
     * @param thumbnailName The updated location of the thumbnail in the file system
     */
    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
        this.setLastUpdated();
    }

    /**
     * Returns the list of tags associated with this media piece
     * @return The list of tags associated with this media piece
     */
    public List<String> getTags() {
        return new LinkedList<>(Arrays.asList(tags.split(",")));
    }

    /**
     * Returns when this media was uploaded to the server
     * @return When this media was uploaded to the server
     */
    public Date getUploadedOn() { return uploadedOn; }

    /**
     * Updates when the file was uploaded to the server
     * @param uploadedOn when this media was uploaded to the server
     */
    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
        this.setLastUpdated();
    }

    /**
     * Returns when this media was last streamed
     * @return When this media was last streamed
     */
    public Date getLastStreamed() { return lastStreamed; }


    /**
     * Updates when this media was last streamed. <i>Does not refresh the <code>lastChanged</code> property</i>
     * @param lastStreamed when this media was last streamed
     */
    public void setLastStreamed(Date lastStreamed) {

        this.lastStreamed = lastStreamed;
    }

    /**
     * Updates the list of tags with a new one. Automatically updates the <code>lastUpdated</code> as well.
     * @param tags The updated list of tags
     */
    public void setTags(List<String> tags) {
        setTagsUpdatingLastUpdated(tags, true);
    }

    protected void setTagsUpdatingLastUpdated(List<String> tags, boolean updateLastUpdatedAsWell){
        StringBuilder temp = new StringBuilder();

        //Sort strings in alphabetical order
        Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);
        //Iterate through all tags and append them to a comma-seperated string
        if(tags.size() > 0)
        {
            for(String s: tags){
                temp.append(s + ",");
            }
            temp.deleteCharAt(temp.length() - 1); //Remove last superfluous comma
        }
        else
            {
                temp.append("null");
            }
        this.tags = temp.toString();
        if(updateLastUpdatedAsWell){ //if we want to update the lastUpdated as well (don't want to do this in the CTOR, but anywhere ele, we do)
            this.setLastUpdated();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaEntity that = (MediaEntity) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "MediaEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", lastUpdated=" + lastUpdated +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", ownedBy=" + ownedBy +
                ", accessibleBy=" + accessibleBy +
                ", thumbnailName='" + thumbnailName + '\'' +
                ", tags=" + tags +
                '}';
    }


}
