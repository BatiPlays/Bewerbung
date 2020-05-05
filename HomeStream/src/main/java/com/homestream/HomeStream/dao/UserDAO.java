package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.vo.UserVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = UserVO.class, idClass = Long.class)
public interface UserDAO extends CrudRepository<UserVO, Long> {

    Optional<UserVO> getByDbid(long id);

    List<UserVO> getByName(String name);

    List<UserVO> findAll();

}
