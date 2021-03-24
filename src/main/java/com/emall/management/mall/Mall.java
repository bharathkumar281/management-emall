package com.emall.management.mall;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.emall.management.admin.Admin;
import com.emall.management.floor.Floor;

@Entity
@Table(name = "mall")
public class Mall {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mall_id")
	private Integer mallId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "space_cost")
	private String spaceCost;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mall", cascade = CascadeType.ALL)
	private List<Floor> floors;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Admin admin;
	
	public Mall() {
		floors = new ArrayList<>();
	}

	public Integer getMallId() {
		return mallId;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getSpaceCost() {
		return spaceCost;
	}

	public void setSpaceCost(String spaceCost) {
		this.spaceCost = spaceCost;
	}
	
}
