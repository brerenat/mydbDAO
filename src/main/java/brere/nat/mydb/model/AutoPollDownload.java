package brere.nat.mydb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: AutoPollDownload
 *
 */
@Entity
@Table(name = "AutoPollDownload")
public class AutoPollDownload extends AbstractDAO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	@Column(unique = false, nullable = false)
	private int season;
	@Column(unique = false, nullable = false)
	private int episode;

	@ManyToOne
	@JoinColumn(name="autoPollSeries_id", nullable=false)
	private AutoPollSeries autoPollSeries;

	private static final long serialVersionUID = 1L;

	public AutoPollDownload() {
		super();
	}
	
	public AutoPollDownload(AutoPollSeries autoPollSeries) {
		super();
		this.autoPollSeries = autoPollSeries;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSeason() {
		return this.season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisode() {
		return this.episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public void setAutoPollSeries(AutoPollSeries autoPollSeries) {
		this.autoPollSeries = autoPollSeries;
	}

}
