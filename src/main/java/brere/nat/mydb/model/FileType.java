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
@Table(name = "FileType")
@NamedQueries({
	@NamedQuery(name = "FileType.findWithName", query="SELECT ft FROM FileType ft WHERE ft.type = :typeName"),
})
public class FileType extends AbstractDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
