package edu.miu.eshop.eshopadmin.repository;

// EB

import edu.miu.eshop.eshopadmin.domain.Employee;
import edu.miu.eshop.eshopadmin.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<Employee> findByPersonId(String personId);

    @Query("{role : {$eq : ?0}}")
    List<Employee> findByRoleQuery(Role roleEngineer);

    @Query("{role : {$ne : ROLE_VENDOR}}")
    List<Employee> findAll();
}
