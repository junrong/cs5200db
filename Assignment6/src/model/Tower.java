package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;


/**
 * The persistent class for the tower database table.
 * 
 */
@Entity
@NamedQuery(name="Tower.findAll", query="SELECT t FROM Tower t")
@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Tower implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@XmlAttribute
	private int id;
	@XmlAttribute
	private double height;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private int sides;

	//bi-directional many-to-one association to Equipment
	@OneToMany(mappedBy="tower",  cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="towerId")
	private List<Equipment> equipments;

	//bi-directional many-to-one association to Site
	@ManyToOne
	@JoinColumn(name="siteId")
	@XmlTransient
	private Site site;

	public Tower() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSides() {
		return this.sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}

	public List<Equipment> getEquipments() {
		return this.equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Equipment addEquipment(Equipment equipment) {
		getEquipments().add(equipment);
		equipment.setTower(this);

		return equipment;
	}

	public Equipment removeEquipment(Equipment equipment) {
		getEquipments().remove(equipment);
		equipment.setTower(null);

		return equipment;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}