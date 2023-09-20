package br.com.desbugando.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.desbugando.locadora.entities.User;
import br.com.desbugando.locadora.projections.UserDetailsProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(nativeQuery = true, value = """
				SELECT tb_users.txt_email AS txt_nome, tb_users.bcr_password, tb_roles.oid_role AS roleId, tb_roles.txt_authority
				FROM tb_users
				INNER JOIN tb_user_role ON tb_users.oid_user = tb_user_role.oid_user
				INNER JOIN tb_roles ON tb_roles.oid_role = tb_user_role.oid_role
				WHERE tb_user.txt_email = :email
			""")
	List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
