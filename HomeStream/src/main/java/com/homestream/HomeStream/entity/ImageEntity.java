package com.homestream.HomeStream.entity;

import com.homestream.HomeStream.vo.ArtistVO;
import com.homestream.HomeStream.vo.UserVO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Represents everything from a photo to an hand-drawn piece of art
 * @author Justin Braack
 */
@Entity
@Table(name="Image")
public class ImageEntity extends MediaEntity {

    @ManyToMany
    @JoinTable(name="__ImageArtistRole",
            joinColumns = @JoinColumn(name="Artist_ID"),
               inverseJoinColumns = @JoinColumn(name="Image_ID"))
    @JoinColumn(name="ID")
    private List<ArtistVO> artists;
    @Column(name="width")
    private int width;
    @Column(name="height")
    private int height;

    /**
     * Creates a new ImageEntitiy
     * @param id The ID of the new entitiy
     * @param name The name of the image (e.g. 'Mona Lisa')
     * @param releaseDate When it was published/created
     * @param lastUpdated When the image was last updated
     * @param fileName Where the image lies within the file system
     * @param fileSize How big the file is
     * @param ownedBy The UserVO who uplaoded the file
     * @param accessibleBy The GroupVO that can access the image
     * @param thumbnailName Where the thumbnail lies (should be identical with <code>name</code>)
     * @param tags The tags for this image
     * @param uploadedOn When the file was uploaded to the server
     * @param lastStreamed When the media was last streamed
     * @param artists The artists who created this imgae (artist/photographer/...)
     * @param size The size dimensions of this image (<code>[0]</code>:width; <code>[1]</code>: height)
     */
    public ImageEntity(long id, String name, Date releaseDate, Date lastUpdated, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags, List<ArtistVO> artists, Date uploadedOn, Date lastStreamed, int[] size) {
        super(id, name, releaseDate, lastUpdated, fileName, fileSize, ownedBy, accessibleBy, thumbnailName, tags, uploadedOn, lastStreamed);
        this.artists = artists;
        this.width = size[0];
        this.height = size[1];
    }

    /**
     * Creates a new ImageEntitiy. <i>Use this if you're unsure about the ID, when it was last updated or where the thumbnail lies.</i>
     * @param name The name of the image (e.g. 'Mona Lisa')
     * @param releaseDate When it was published/created
     * @param fileName Where the image lies within the file system
     * @param fileSize How big the file is
     * @param ownedBy The UserVO who uplaoded the file
     * @param accessibleBy The GroupVO that can access the image
     * @param tags The tags for this image
     * @param lastStreamed When the media was last streamed
     * @param artists The artists who created this imgae (artist/photographer/...)
     * @param size The size dimensions of this image (<code>[0]</code>:width; <code>[1]</code>: height)
     */
    @Deprecated
    public ImageEntity(String name, Date releaseDate, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, List<String> tags, List<ArtistVO> artists, Date lastStreamed, int[] size) {
        this(name, releaseDate, fileName, fileSize, ownedBy, accessibleBy, tags, artists, lastStreamed,  size[0], size[1]);
    }

    /**
     * Creates a new ImageEntitiy. <i>Use this if you're unsure about the ID, when it was last updated or where the thumbnail lies.</i>
     * @param name The name of the image (e.g. 'Mona Lisa')
     * @param releaseDate When it was published/created
     * @param fileName Where the image lies within the file system
     * @param fileSize How big the file is
     * @param ownedBy The UserVO who uplaoded the file
     * @param accessibleBy The GroupVO that can access the image
     * @param tags The tags for this image
     * @param lastStreamed When the media was last streamed
     * @param artists The artists who created this imgae (artist/photographer/...)
     * @param width The wifth (in pixels) of this image
     * @param height The height (in pixels) of this image
     */
    public ImageEntity(String name, Date releaseDate, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, List<String> tags, List<ArtistVO> artists, Date lastStreamed, int width, int height) {
        super(name, releaseDate, fileName, fileSize, ownedBy, accessibleBy, fileName, tags, (new Date(Calendar.getInstance().getTimeInMillis())), lastStreamed);
        this.artists = artists;
        this.width = width;
        this.height = height;
    }

    public ImageEntity(long id, ImageEntity Idless){
        this(id, Idless.getName(), Idless.getReleaseDate(), new Date(Calendar.getInstance().getTimeInMillis()), Idless.getFileName(), Idless.getFileSize(), Idless.getOwnedBy(), Idless.getAccessibleBy(), Idless.getThumbnailName(), Idless.getTags(), Idless.getArtists(), Idless.getUploadedOn(), Idless.getLastStreamed(), Idless.getSize());
    }

    protected ImageEntity(){

    }

    /**
     * Returns the artists who have worked on this image
     * @return The artists who have worked on this image
     */
    public List<ArtistVO> getArtists() {
        return artists;
    }

    /**
     * Replaces the list of artists with a new one. Automatically updates the <code>lastUpdated</code> as well.
     * @param artists The updated list of artists who have worked on this image
     */
    public void setArtists(List<ArtistVO> artists) {
        this.artists = artists;
        this.setLastUpdated();
    }

    /**
     * Returns the dimension array representing the size of the image in Pixels
     * @return the dimension array representing the size of the image in Pixels
     * @see ImageEntity#getWidth()
     * @see ImageEntity#getHeight()
     * @see <p>for better/more readable code</p>
     */
    @Deprecated
    public int[] getSize() {
        return new int[] {this.getWidth(), this.getHeight()};
    }

    /**
     * Returns the width of the image
     * @return The width of the image
     */
    public int getWidth(){
        return width;
    }

    /**
     * Returns the height of the image
     * @return The height of the image
     */
    public int getHeight(){
        return height;
    }

    /**
     * Update the image's file path. <i>Automatically updates the <code>lastUpdated</code> and <code>thumbnailName</code></i>
     * @param fileName The upated path where this image can be found. <i>It simultaneously stands for the image's thumbnail.</i>
     */
    @Override
    public void setFileName(String fileName){
        super.setFileName(fileName);
        super.setThumbnailName(fileName);
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
}
