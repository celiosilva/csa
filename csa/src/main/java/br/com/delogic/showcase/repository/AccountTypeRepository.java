package br.com.delogic.showcase.repository;

import br.com.delogic.showcase.entity.AccountType;

public class AccountTypeRepository extends GenericRepository<AccountType> {

	private static final AccountTypeRepository ACCOUNT_TYPE_REPOSITORY = new AccountTypeRepository();

	@Override
	public AccountType create(int index) {
		AccountType accountType = new AccountType();
		accountType.setId((long) index);
		accountType.setName("AccountType:" + index);
		return accountType;
	}

	public static AccountTypeRepository get() {
		return ACCOUNT_TYPE_REPOSITORY;
	}

}
