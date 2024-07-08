package com.devyurakim.devschool.model;

/***/
public class Contact {

    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;

    @Override
    public String toString() {
        return String.format("name: %s, mobile: %s, email: %s, subject: %s, message: %s",
                name, mobileNum, email, subject, message);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
