package entity;


public class User {
    
    private long id ;
    private String nickname ;
    private String password ;
    private String nameSurname ;
    private String address ;
    private String country ;

    public User() {
    }

    public User(long id, String nickname, String password, String nameSurname, String address, String country) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.nameSurname = nameSurname;
        this.address = address;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
