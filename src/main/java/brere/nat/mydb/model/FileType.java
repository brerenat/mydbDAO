package brere.nat.mydb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Table;

@Entity
@Table(name = "FileType")
public class FileType extends AbstractDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	@Column(unique = true, nullable = false)
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static class Queries {
		public static FileType findWithName(String name) throws NoResultException {
			return getEm().createQuery("SELECT ft FROM FileType ft WHERE ft.type = :typeName", FileType.class)
					.setParameter("typeName", name).getSingleResult();
		}
	}

}
