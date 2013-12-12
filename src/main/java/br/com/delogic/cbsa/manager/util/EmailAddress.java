package br.com.delogic.cbsa.manager.util;

public class EmailAddress {

    private final String name;
    private final String address;

    public EmailAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name + " - " + address;
    }

}
