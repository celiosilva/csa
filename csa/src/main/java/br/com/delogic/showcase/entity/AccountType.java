package br.com.delogic.showcase.entity;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.showcase.repository.LongEntityId;

public class AccountType extends LongEntityId {

    private Long   id;

    @NotEmpty
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
