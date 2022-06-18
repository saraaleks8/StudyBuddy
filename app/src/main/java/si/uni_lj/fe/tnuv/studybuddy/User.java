package si.uni_lj.fe.tnuv.studybuddy;

public class User {

    private String name, surname, profession, email;

    public User(){

    }

    public User(String name, String surname, String profession, String email){
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(){
        this.surname = surname;
    }

    public String getProfession(){
        return profession;
    }

    public void setProfession(){
        this.profession = profession;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

}
