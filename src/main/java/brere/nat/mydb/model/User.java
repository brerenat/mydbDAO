package brere.nat.mydb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "User")
@NamedQueries({
	@NamedQuery(name = "User.findWithUserName", query="SELECT u FROM User u WHERE u.userName = :userName"),
})
public class User extends AbstractDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id = 0;
	@Column(unique = true, nullable = false)
	private String userName;
	@Column(unique = false, nullable = false)
	private String password;
	@Column(unique = false, nullable = false)
	private String salt;
	
	public User() {
	}
	
	public User(final String userName, final String password, final String salt) {
		this.userName = userName;
		this.password = password;
		this.salt = salt;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
