package br.com.delogic.showcase.repository;

import java.math.BigDecimal;

import br.com.delogic.showcase.entity.Account;

public class AccountRepository extends GenericRepository<Account> {

	private static final AccountTypeRepository accountTypeRepository = AccountTypeRepository
			.get();
	private static final UserRepository userRepository = UserRepository.get();

	private static final AccountRepository ACCOUNT_REPOSITORY = new AccountRepository();

	@Override
	public Account create(int index) {
		Account account = new Account();
		account.setAccountType(accountTypeRepository.find((long) index));
		account.setBalance(new BigDecimal(index * 1000));
		account.setBankAccount(index * 12345 + index);
		account.setId((long) index);
		account.setUser(userRepository.find((long) index));
		return account;
	}

	public static AccountRepository get() {
		return ACCOUNT_REPOSITORY;
	}

}
