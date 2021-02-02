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
@Table(name = "ReferenceData")
@NamedQueries({
	@NamedQuery(name = "ReferenceData.findWithName", query="SELECT rd FROM ReferenceData rd WHERE rd.name = :name"),
})
public class ReferenceData extends AbstractDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = false, nullable = false)
	private String value;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
