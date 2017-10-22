package com.example.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KotaModel implements Comparable<KotaModel> {
	private long id;
	private String kodeKota;
	private String namaKota;
	private List<KecamatanModel> listKecamatan;
	
	@Override
	public int compareTo(KotaModel o) {
		if(this.id < o.id) {
			return -1;
		} else if (this.id > o.id) {
			return 1;
		}
		return 0;
	}
}
