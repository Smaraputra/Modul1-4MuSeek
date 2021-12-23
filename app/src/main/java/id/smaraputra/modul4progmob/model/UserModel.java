package id.smaraputra.modul4progmob.model;

public class UserModel {
    private String name, phone, address, username, password, gender, skill, note;
    private int id, waktu,valid;

    public UserModel(int id, String namein, String phonein,
                     String addressin, String usernamein, String passwordin,
                     String genderin, String skillin, int waktuin, String notein, int validin) {
        this.id = id;
        this.name = namein;
        this.phone = phonein;
        this.address = addressin;
        this.username = usernamein;
        this.password = passwordin;
        this.gender = genderin;
        this.skill = skillin;
        this.waktu = waktuin;
        this.note = notein;
        this.valid = validin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
