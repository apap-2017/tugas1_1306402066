package com.example.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel implements Comparable<PendudukModel> {
	private long id;
	private String nik;
	private String nama;
	private String tempatLahir;
	private Date tanggalLahir;
	private int jenisKelamin;
	private byte isWni;
	private long idKeluarga;
	private KeluargaModel keluarga;
	private String agama;
	private String pekerjaan;
	private String statusPerkawinan;
	private String statusDalamKeluarga;
	private String golonganDarah;
	private byte isWafat;

	@Override
	public int compareTo(PendudukModel o) {
		if(this.id < o.id) {
			return -1;
		} else if(this.id > o.id) {
			return 1;
		}
		return 0;
	}
}
