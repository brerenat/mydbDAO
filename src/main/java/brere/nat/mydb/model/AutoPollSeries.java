package brere.nat.mydb.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "AutoPollSeries")
@NamedQueries({ @NamedQuery(name = "AutoPollSeries_getAll", query = "SELECT aps FROM AutoPollSeries AS aps"),
		@NamedQuery(name = "AutoPollSeries_getAllByActive", query = "SELECT aps FROM AutoPollSeries AS aps WHERE aps.active = :active"),
		@NamedQuery(name = "AutoPollSeries_getAllByStartPoll", query = "SELECT aps FROM AutoPollSeries AS aps WHERE aps.startPoll = :startPoll"),
		@NamedQuery(name = "AutoPollSeries_countAll", query = "SELECT COUNT(aps) FROM AutoPollSeries AS aps"),
		@NamedQuery(name = "AutoPollSeries_getByID", query = "SELECT aps FROM AutoPollSeries AS aps WHERE aps.id = :id"),
		@NamedQuery(name = "AutoPollSeries_getAllSearch", query = "SELECT aps FROM AutoPollSeries AS aps WHERE aps.title like :title"),
		@NamedQuery(name = "AutoPollSeries_countSearch", query = "SELECT COUNT(aps) FROM AutoPollSeries AS aps WHERE aps.title like :title"),
		@NamedQuery(name = "AutoPollSeries_getAllSorted", query = "SELECT aps FROM AutoPollSeries AS aps ORDER BY :sorting"),
		@NamedQuery(name = "AutoPollSeries_getAllSearchSorted", query = "SELECT aps FROM AutoPollSeries AS aps WHERE aps.title like :title ORDER BY :sorting"), })
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
	@Column(unique = false, nullable = false)
	private int seasonFrom = 0;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "autoPollSeries")
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

	public int getSeasonFrom() {
		return seasonFrom;
	}

	public void setSeasonFrom(int seasonFrom) {
		this.seasonFrom = seasonFrom;
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

}
