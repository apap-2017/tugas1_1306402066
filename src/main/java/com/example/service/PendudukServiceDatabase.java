package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {

	@Autowired
	private PendudukMapper pendudukMapper;
	
	@Override
	public PendudukModel selectPenduduk(String nik) {
		// TODO Auto-generated method stub
		log.info("select penduduk with nik {}", nik);
		return pendudukMapper.selectPenduduk(nik);
	}

	@Override
	public KeluargaModel selectKeluarga(String nkk) {
		// TODO Auto-generated method stub
		log.info("select keluarga with nkk {}", nkk);
		return pendudukMapper.selectKeluarga(nkk);
	}

	@Override
	public void addPendudukBaru(PendudukModel penduduk) {
		// TODO Auto-generated method stub
		log.info("successfully built penduduk with nik {}", penduduk.getNik());
		pendudukMapper.addPendudukBaru(penduduk);
	}
	
	@Override
	public void addKeluargaBaru(KeluargaModel keluarga) {
		// TODO Auto-generated method stub
		log.info("successfully built keluarga with nkk {}", keluarga.getNomorKk());
		pendudukMapper.addKeluargaBaru(keluarga);
	}
	
	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		// TODO Auto-generated method stub
		log.info("successfully updated penduduk with nik {}", penduduk.getNik());
		pendudukMapper.updatePenduduk(penduduk);
	}

	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		// TODO Auto-generated method stub
		log.info("successfully updated keluarga with nkk {}", keluarga.getNomorKk());
		pendudukMapper.updateKeluaga(keluarga);
	}

	@Override
	public void updateKematian(PendudukModel penduduk) {
		// TODO Auto-generated method stub
		log.info("penduduk nik {} wafat", penduduk.getNik());
		pendudukMapper.updateKematian(penduduk);
	}

	@Override
	public void updateBerlaku(KeluargaModel keluarga) {
		// TODO Auto-generated method stub
		log.info("nkk {} sudah tidak berlaku", keluarga.getNomorKk());
		pendudukMapper.updateBerlaku(keluarga);
	}

	@Override
	public List<PendudukModel> selectPendudukbyKelurahan(long id) {
		// TODO Auto-generated method stub
		return pendudukMapper.daftarPendudukbyKelurahan(id);
	}

	@Override
	public long getIdKelurahan(String namaKelurahan) {
		// TODO Auto-generated method stub
		return pendudukMapper.getIdKelurahan(namaKelurahan);
	}

	@Override
	public KelurahanModel getKelurahanbyId(long id) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectKelurahanbyId(id);
	}

	@Override
	public KeluargaModel getKeluargabyId(long id) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectKeluargabyId(id);
	}

	@Override
	public List<KotaModel> daftarKota() {
		// TODO Auto-generated method stub
		return pendudukMapper.daftarKota();
	}

	@Override
	public List<KecamatanModel> daftarKecamatanbyKota(long id) {
		// TODO Auto-generated method stub
		return pendudukMapper.daftarKecamatanbyKota(id);
	}

	@Override
	public List<KelurahanModel> daftarKelurahanbyKecamatan(long id) {
		// TODO Auto-generated method stub
		return pendudukMapper.daftarKelurahanbyKecamatan(id);
	}

}
