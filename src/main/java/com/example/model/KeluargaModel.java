package com.example.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel implements Comparable<KeluargaModel> {
	private long id;
	private String nomorKk;
	private String alamat;
	private String rT;
	private String rW;
	private long idKelurahan;
	private KelurahanModel kelurahan;
	private byte isTidakBerlaku;
	private List<PendudukModel> daftarAnggota;
	
	@Override
	public int compareTo(KeluargaModel o) {
		if(this.id < o.id) {
			return -1;
		} else if(this.id > o.id) {
			return 1;
		}
		return 0;
	}
}
