package factory;

import com.github.javafaker.Faker;

public class FakeAccountFactory {

    private String accountName;
    private String phone;
    private String fax;
    private String website;
    private String employees;
    private String annualRevenue;

    public void setRandomData(){
        Faker fake = new Faker();
        accountName = fake.artist().name();
        phone = fake.phoneNumber().cellPhone();
        fax = String.valueOf(fake.number().numberBetween(100000, 600000));
        website = fake.internet().url();
        employees = String.valueOf(fake.number().numberBetween(10, 30));
        annualRevenue = String.valueOf(fake.number().numberBetween(10000, 30000));
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmployees() {
        return employees;
    }

    public String getAnnualRevenue() {
        return annualRevenue;
    }
}
