package si.uni_lj.fe.tnuv.studybuddy;

import java.util.HashMap;
import java.util.UUID;

public class User {

    private String name, surname, profession, email;
    private HashMap<String, Sessions> sessions;

//    HashMap<String, Session> Session

    public User(){

    }

    public User(String name, String surname, String profession, String email, HashMap<String, Sessions> sessions){
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.email = email;
        this.sessions = sessions;
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

    public HashMap<String, Sessions> getSessions(){
        return sessions;
    }

    public void setSessions(){
        this.sessions = sessions;
    }

}
