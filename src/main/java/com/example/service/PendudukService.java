package com.example.service;

import java.util.List;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;


public interface PendudukService {
	PendudukModel selectPenduduk(String nik);
	
	KeluargaModel selectKeluarga(String nkk);
	
	void addPendudukBaru(PendudukModel penduduk);
	
	void addKeluargaBaru(KeluargaModel keluarga);
	
	long getIdKelurahan(String namaKelurahan);
	
	KelurahanModel getKelurahanbyId(long id);
	
	KeluargaModel getKeluargabyId(long id);
	
	void updatePenduduk(PendudukModel penduduk);
	
	void updateKeluarga(KeluargaModel keluarga);
	
	void updateKematian(PendudukModel penduduk);
	
	void updateBerlaku(KeluargaModel keluarga);
	
	List<KotaModel> daftarKota();
	
	List<KecamatanModel> daftarKecamatanbyKota(long id);
	
	List<KelurahanModel> daftarKelurahanbyKecamatan(long id);
	
	List<PendudukModel> selectPendudukbyKelurahan(long id);
}
