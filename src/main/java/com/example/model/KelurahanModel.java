package com.example.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel implements Comparable<KelurahanModel> {
	private long id;
	private long idKecamatan;
	private KecamatanModel kecamatan;
	private String kodeKelurahan;
	private String namaKelurahan;
	private String kodePos;
	private List<PendudukModel> daftarPenduduk;

	@Override
	public int compareTo(KelurahanModel o) {
		if (this.id < o.id) {
			return -1;
		} else if (this.id > o.id) {
			return 1;
		}
		return 0;
	}
}
