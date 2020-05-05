package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.FilmEntity;
import com.homestream.HomeStream.vo.ArtistVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = FilmEntity.class, idClass = Long.class)
public interface FilmDAO extends CrudRepository<FilmEntity, Long> {

    Optional<FilmEntity> getById(long id);

    List<FilmEntity> getByName(String name);

    List<FilmEntity> findAll();

    List<FilmEntity> getByReleaseDate(Date date);

    List<FilmEntity> getByTags(String tags);

    List<FilmEntity> getByUploadedOn(Date uploadedOn);

    List<FilmEntity> getByLastStreamed(Date lastStreamed);

    List<FilmEntity> getByMainActors(ArtistVO artist);
}
