package com.homestream.HomeStream.dao;

import com.homestream.HomeStream.entity.RoleEntity;
import com.homestream.HomeStream.vo.UserVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = RoleEntity.class, idClass = Long.class)
public interface RoleDAO extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> getById(long id);

    List<RoleEntity> getByName(String name);

    List<RoleEntity> findAll();

    List<RoleEntity> getByUsers(UserVO user);
}
