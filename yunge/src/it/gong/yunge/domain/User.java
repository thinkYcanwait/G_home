package it.gong.yunge.domain;

public class User {
    private int id;
    private String username;
    private String password;
    private String gender;
    private Integer age;
    private String address;
    private String tel;
    private String email;
    private String aboutword;

    public User() {}

    public User(int id, String username, String password, String gender, Integer age, String address, String tel, String email, String aboutword) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.aboutword = aboutword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutword() {
        return aboutword;
    }

    public void setAboutword(String aboutword) {
        this.aboutword = aboutword;
    }
}
