package com.emall.management.space;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.emall.management.floor.Floor;

@Entity
@Table(name = "space")
public class Space {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "space_id")
	private Integer spaceId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id")
	private Floor floor;

	public Integer getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	
}
