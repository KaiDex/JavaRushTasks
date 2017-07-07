package userdatabase.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name="AGE")
    private int age;

    @Column(name="IS_ADMIN")
    private boolean isAdmin;

    @Column(name="CREATED_DATE", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isAdmin=" + isAdmin +
                ", createdDate=" + createdDate +
                '}';
    }
}
