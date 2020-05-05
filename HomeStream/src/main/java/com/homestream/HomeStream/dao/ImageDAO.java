package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.ImageEntity;
import com.homestream.HomeStream.vo.ArtistVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = ImageEntity.class, idClass = Long.class)
public interface ImageDAO extends CrudRepository<ImageEntity, Long> {

    Optional<ImageEntity> getById(long id);

    List<ImageEntity> getByName(String name);

    List<ImageEntity> findAll();

    List<ImageEntity> getByReleaseDate(Date date);

    List<ImageEntity> getByTags(String tags);

    List<ImageEntity> getByUploadedOn(Date uploadedOn);

    List<ImageEntity> getByLastStreamed(Date lastStreamed);

    List<ImageEntity> getByArtists(ArtistVO artist);
}
