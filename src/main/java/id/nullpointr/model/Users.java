package id.nullpointr.model;


import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {


  @Id
  @Column(name="id")
  private long id;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;


  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
