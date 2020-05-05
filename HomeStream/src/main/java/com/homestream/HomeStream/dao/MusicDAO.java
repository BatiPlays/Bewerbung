package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.MusicEntity;
import com.homestream.HomeStream.vo.ArtistVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@RepositoryDefinition(domainClass = MusicEntity.class, idClass = Long.class)
@Transactional(readOnly = true)
public interface MusicDAO extends CrudRepository<MusicEntity, Long> {

    /**
     * Get the object with a specific <code>id</code>
     * @param id The ID of the object that shall be returned
     * @return The object with that <code>id</code>, hidden inside the Optional that is returned. If none if found, Optional.empty() is returned
     */
    Optional<MusicEntity> getById(long id);

    List<MusicEntity> getByName(String name);

    List<MusicEntity> findAll();

    List<MusicEntity> getByReleaseDate(Date date);

    List<MusicEntity> getByTags(String tags);

    List<MusicEntity> getByUploadedOn(Date uploadedOn);

    List<MusicEntity> getByLastStreamed(Date lastStreamed);

    List<MusicEntity> getByArtists(ArtistVO artist);

}