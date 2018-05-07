package id.nullpointr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.nullpointr.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long>{
	Users findByEmail(String email);
}
