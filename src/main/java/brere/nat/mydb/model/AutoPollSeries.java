package brere.nat.mydb.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "AutoPollSeries")
public class AutoPollSeries extends AbstractDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id = 0;
	@Column(unique = true, nullable = false)
	private String imdbID;
	@Column(unique = false, nullable = false)
	private String posterUrl;
	@Column(unique = false, nullable = false)
	private String title;
	@Column(unique = false, nullable = false)
	private String year;
	@Column(unique = false, nullable = false)
	private boolean active;
	@Column(unique = true, nullable = false)
	private String folderName;
	@Column(unique = false, nullable = false)
	private boolean startPoll;
	@OneToMany(mappedBy="autoPollSeries")
	private Set<AutoPollDownload> activeDownloads = new HashSet<>();
	
	@Transient
	private Map<Integer, Set<Integer>> seasonMap = new HashMap<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public boolean isStartPoll() {
		return startPoll;
	}

	public void setStartPoll(boolean startPoll) {
		this.startPoll = startPoll;
	}

	public Set<AutoPollDownload> getActiveDownloads() {
		return activeDownloads;
	}

	public void setActiveDownloads(Set<AutoPollDownload> activeDownloads) {
		this.activeDownloads = activeDownloads;
	}
	

	
	public Map<Integer, Set<Integer>> getSeasonMap() {
		return seasonMap;
	}

	public void setSeasonMap(Map<Integer, Set<Integer>> seasonMap) {
		this.seasonMap = seasonMap;
	}



	public static class Queries {
		public static List<AutoPollSeries> getAll(final EntityManager em) throws NoResultException {
			final List<AutoPollSeries> item;
			if (em != null) {
				item = em.createQuery("SELECT aps FROM AutoPollSeries AS aps LEFT OUTER JOIN aps.activeDownloads AS apd", AutoPollSeries.class).getResultList();
			} else {
				item = getEm().createQuery("SELECT aps FROM AutoPollSeries AS aps LEFT OUTER JOIN aps.activeDownloads AS apd", AutoPollSeries.class).getResultList();
			}
			return item;
		}
		
		public static List<AutoPollSeries> getAllByActive(final boolean active, final EntityManager em) throws NoResultException {
			final List<AutoPollSeries> item;
			if (em != null) {
				item = em.createQuery("SELECT aps FROM AutoPollSeries AS aps LEFT OUTER JOIN aps.activeDownloads AS apd WHERE aps.active = :active", AutoPollSeries.class)
						.setParameter("active", active).getResultList();
			} else {
				item = getEm().createQuery("SELECT aps FROM AutoPollSeries AS aps LEFT OUTER JOIN aps.activeDownloads AS apd WHERE aps.active = :active", AutoPollSeries.class)
						.setParameter("active", active).getResultList();
			}
			return item;
		}
		
		public static AutoPollSeries getAllByStartPoll(final boolean startPoll, final EntityManager em) throws NoResultException {
			final AutoPollSeries item;
			if (em != null) {
				item = em.createQuery("SELECT aps FROM AutoPollSeries AS aps LEFT OUTER JOIN aps.activeDownloads AS apd WHERE aps.startPoll = :startPoll", AutoPollSeries.class)
				.setParameter("startPoll", startPoll).getSingleResult();
			} else {
				item = getEm().createQuery("SELECT aps FROM AutoPollSeries AS aps LEFT OUTER JOIN aps.activeDownloads AS apd WHERE aps.startPoll = :startPoll", AutoPollSeries.class)
				.setParameter("startPoll", startPoll).getSingleResult();
			}
			return item;
		}
	}
}
