package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the site database table.
 * 
 */
@Entity
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@XmlAttribute
	private int id;
	@XmlAttribute
	private double latitude;
	@XmlAttribute
	private double longitude;
	@XmlAttribute
	private String name;

	//bi-directional many-to-one association to Tower
	@OneToMany(mappedBy="site",  cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="siteId")
	private List<Tower> towers;

	public Site() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public Tower addTower(Tower tower) {
		getTowers().add(tower);
		tower.setSite(this);

		return tower;
	}

	public Tower removeTower(Tower tower) {
		getTowers().remove(tower);
		tower.setSite(null);

		return tower;
	}

}