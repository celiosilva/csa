package br.com.delogic.showcase.repository;

import java.util.Date;

import br.com.delogic.showcase.entity.User;
import br.com.delogic.showcase.entity.enums.Sexo;

public class UserRepository extends GenericRepository<User> {

	private static final UserRepository REPOSITORY = new UserRepository();

	public static final UserRepository get() {
		return REPOSITORY;
	}

	@Override
	public User create(int i) {
		User user = new User();
		user.setDataNascimento(new Date());
		user.setSexo(Sexo.MASCULINO);
		user.setId((long) i);
		user.setNome("User Name " + i);
		user.setImagem("picture" + i + ".png");
		return user;
	}

}
