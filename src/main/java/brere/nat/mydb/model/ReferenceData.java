package brere.nat.mydb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Table;

@Entity
@Table(name = "ReferenceData")
public class ReferenceData extends AbstractDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = false, nullable = false)
	private String value;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public static class Queries {
		public static ReferenceData findWithName(String name) throws NoResultException {
			return getEm().createQuery("SELECT rd FROM ReferenceData rd WHERE rd.name = :name", ReferenceData.class)
					.setParameter("name", name).getSingleResult();
		}
	}

}
