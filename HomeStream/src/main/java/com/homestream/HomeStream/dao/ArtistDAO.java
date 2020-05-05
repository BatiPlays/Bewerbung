package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.vo.ArtistVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = ArtistVO.class, idClass = Long.class)
public interface ArtistDAO extends CrudRepository<ArtistVO, Long> {

    Optional<ArtistVO> getByDbid(long id);

    List<ArtistVO> getByName(String name);

    List<ArtistVO> findAll();

}
