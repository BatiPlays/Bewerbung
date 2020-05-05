package com.homestream.HomeStream.main;

import com.homestream.HomeStream.dao.FilmDAO;
import com.homestream.HomeStream.dao.ImageDAO;
import com.homestream.HomeStream.dao.MusicDAO;
import com.homestream.HomeStream.entity.*;
import com.homestream.HomeStream.main.assets.Assets;
import com.homestream.HomeStream.main.assets.property.Properties;
import com.homestream.HomeStream.main.exception.DuplicatedObjectOnServerFoundException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class RequestHandler {

    @Autowired
    MusicDAO music;
    @Autowired
    FilmDAO video;
    @Autowired
    ImageDAO image;

    /**
     * Primary Function to search database by query
     * @param searchInput
     * @return
     */
    public List<? extends IEntity> search(String searchInput){

        /**
         * catch extended Search query
          */
        if(searchInput.startsWith("ext"))
        {
            ArrayList<List<IEntity>> listArray = new ArrayList<>();
            List<IEntity> out = new LinkedList<>();
            String[] searchParam = searchInput.split("§");

            for(int i = 1; i < searchParam.length; i++)
            {
                switch (searchParam[i].split(",")[0])
                {
                    case "TITLE":
                        System.out.println("Search by Title: " + searchParam[i].split(",")[1]);
                        listArray.add(searchForParam(searchParam[i].split(",")[1],"name"));
                        break;
                    case "PLAYLIST":
                        System.out.println("Search by Playlist Name: " + searchParam[i].split(",")[1]);
                        listArray.add(searchForParam(searchParam[i].split(",")[1],"playlist"));
                        break;
                    case "ARTIST":
                        System.out.println("Search by Artist: " + searchParam[i].split(",")[1]);
                        listArray.add(searchForParam(searchParam[i].split(",")[1],"artist"));
                        break;
                    case "OWNER":
                        System.out.println("Search by Owner: " + searchParam[i].split(",")[1]);
                        listArray.add(searchForParam(searchParam[i].split(",")[1],"owner"));
                        break;
                    case "RELEASE":
                        System.out.println("Search by Release: " + searchParam[i].split(",")[1]);
                        listArray.add(searchForParam(searchParam[i].split(",")[1],"release"));
                        break;
                    case "GENRE":
                        System.out.println("Search by Genre: " + searchParam[i].split(",")[1]);
                        listArray.add(searchForParam(searchParam[i].split(",")[1],"genre"));
                        break;
                    case "TAGS":;
                        System.out.println("Search by Tags: " + searchParam[i].split(",")[1]);
                        listArray.add(searchForParam(searchParam[i].split(",")[1],"tags"));
                        break;
                }
            }

            out.addAll(listArray.get(0));
            for (int i = 1; i < listArray.size(); i++)
            {

                out.retainAll(listArray.get(i));
            }
            return out;
        }

        /**
         * catch default Search query
         */
        switch(searchInput){

            case "music":   return music.findAll();
            case "film":    return video.findAll();
            case "image":   return image.findAll();
            case "all":   return getFromAll();

            default: return getFromAll(searchInput);
        }
    }

    /**
     * Internal Function to request all types by query
     * @param SearchInput
     * @return
     */
    private List<MediaEntity> getFromAll(String SearchInput){
        List<MediaEntity> returnList = new LinkedList<>();

        returnList.addAll(music.getByName(SearchInput));
        returnList.addAll(video.getByName(SearchInput));
        returnList.addAll(image.getByName(SearchInput));

        return returnList;
    }

    /**
     * Internal Function to request all types without query
     * @return
     */
    private List<MediaEntity> getFromAll(){
        List<MediaEntity> returnList = new LinkedList<>();

        music.findById(1L);
        returnList.addAll(music.findAll());
        returnList.addAll(video.findAll());
        returnList.addAll(image.findAll());

        return Assets.randomizeList(returnList);
    }

    /**
     * Simple save Function to Upload Data
     * @param media
     * @param thumbnail
     * @param title
     * @param artist
     * @param tags
     * @return
     */
    public String save(MultipartFile media, MultipartFile thumbnail, String title, String artist, String tags) {

        try
        {
            String thumbnailName;

            File onServer = new File("./res/data/" + media.getOriginalFilename());
            FileUtils.writeByteArrayToFile(onServer, media.getBytes());
            if(!Properties.REPLACE_UPLOADED_FILES && onServer.exists()) throw new DuplicatedObjectOnServerFoundException(media.getOriginalFilename());

            if(thumbnail != null)
            {
                thumbnailName = thumbnail.getOriginalFilename();

                onServer = new File("./res/data/" + thumbnail.getOriginalFilename());
                FileUtils.writeByteArrayToFile(onServer, thumbnail.getBytes());
                if(!Properties.REPLACE_UPLOADED_FILES && onServer.exists()) throw new DuplicatedObjectOnServerFoundException(thumbnail.getOriginalFilename());
            }
            else thumbnailName = "default_thumbnail.png";

            if(Assets.checkFileType(media.getOriginalFilename(), "mp3"))
                music.save(new MusicEntity(title, null, media.getOriginalFilename(),media.getSize(),null,null,thumbnailName,Assets.stringToList(tags,","),new ArrayList<>(),null,null));
            else if(Assets.checkFileType(media.getOriginalFilename(), "png", "jpg", "jfif", "gif"))
                //long id, String name, Date releaseDate, Date lastUpdated, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags, List<ArtistVO> artists, Date uploadedOn, Date lastStreamed, int[] size
                image.save(new ImageEntity(0,title,null,null,media.getOriginalFilename(),media.getSize(),null,null,media.getOriginalFilename(),Assets.stringToList(tags,","), new ArrayList<>(),null,null,new int[]{0,0}));
            else if(Assets.checkFileType(media.getOriginalFilename(), "mp4"))
                //String name, Date releaseDate, String fileName, long fileSize, UserVO ownedBy, List<RoleEntity> accessibleBy, String thumbnailName, List<String> tags, Date lastStreamed, List<ArtistVO> mainActors, List<ArtistVO> sideActors, LocalTime length
                video.save(new FilmEntity(title,null,media.getOriginalFilename(),media.getSize(),null,null,thumbnailName,Assets.stringToList(tags,","),null,new ArrayList<>(),new ArrayList<>(),null));

            return "File saved";
        }
        catch(IOException ioE)
        {
            return null;
        }
    }

    /**
     * Internal Function to prepare extended Search by query
     * @param param
     * @param type
     * @return
     */
    private List<IEntity> searchForParam(String param, String type)
    {
        List tagList = new LinkedList();
        List<IEntity> out = new LinkedList<>();
        List<IEntity> temp = new LinkedList<>();
        List<String> name = new LinkedList<>();

        String[] params = param.split(" ");

        for(String s : params)
        {
            switch (type)
            {
                case "name":
                    temp.addAll(music.getByName(s));
                    temp.addAll(image.getByName(s));
                    temp.addAll(video.getByName(s));
                    break;
                case "playlist":
                    System.out.println("playlist: NOT IMPLEMENTED");
                    break;
                case "artist":
                    System.out.println("Artist: NOT IMPLEMENTED");
                    break;
                case "owner":
                    System.out.println("Owner: NOT IMPLEMENTED");
                    break;
                case "release":
                    System.out.println("Release: NOT IMPLEMENTED");
                    break;
                case "genre":
                    System.out.println("Genre: NOT IMPLEMENTED");
                    break;
                case "tags":
                    temp.addAll(music.getByTags(s));
                    temp.addAll(image.getByTags(s));
                    temp.addAll(video.getByTags(s));
                    break;

            }
        }
        for(IEntity m : temp) {

            MediaEntity me = ((MediaEntity) m);

            if(!name.contains(me.getName()))
            {
                name.add(me.getName());
                out.add(m);
            }
        }

        return out;
    }
}