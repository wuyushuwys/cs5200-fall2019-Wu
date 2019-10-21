package edu.northeastern.cs5200.models;

import java.sql.Date;

public class User extends Developer{
    private boolean user_agreement;

    public User() {
        super();
    }

    public User(int person_id, String first_name, String last_name, String username, String password, String email, Date dob, String developer_key, boolean user_agreement) {
        super(person_id, first_name, last_name, username, password, email, dob, developer_key);
        this.user_agreement = user_agreement;
    }

    public User(boolean user_agreement, int id, String first_name, String last_name) {
        super();
        this.user_agreement = user_agreement;
        super.setFirst_name(first_name);
        super.setLast_name(last_name);
    }

    public User(boolean user_agreement) {
        this.user_agreement = user_agreement;
    }

    public User(int person_id, String first_name, String last_name, String username, String password, String email, Date dob, String developer_key) {
        super(person_id, first_name, last_name, username, password, email, dob, developer_key);
    }

    public boolean isUser_agreement() {
        return user_agreement;
    }

    public void setUser_agreement(boolean user_agreement) {
        this.user_agreement = user_agreement;
    }
}
