package com.emall.management.floor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.emall.management.mall.Mall;
import com.emall.management.space.Space;

@Entity
@Table(name = "floor")
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "floor_id")
	private Integer floorId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "floor",cascade = CascadeType.ALL)
	private List<Space> spaces;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mall_id")
	private Mall mall;
	
	public Floor() {
		spaces = new ArrayList<>();
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public List<Space> getSpaces() {
		return spaces;
	}

	public void setSpaces(List<Space> spaces) {
		this.spaces = spaces;
	}

	public void setMall(Mall mall) {
		this.mall = mall;
	}
}
