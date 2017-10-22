package com.example.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel implements Comparable<KecamatanModel> {
	private long id;
	private long idKota;
	private KotaModel kota;
	private String kodeKecamatan;
	private String namaKecamatan;
	private List<KelurahanModel> listKelurahan;
	
	@Override
	public int compareTo(KecamatanModel o) {
		if (this.id < o.id) {
			return -1;
		} else if (this.id > o.id) {
			return 1;
		}
		return 0;
	}
}
