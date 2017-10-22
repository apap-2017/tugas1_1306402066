package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	@Select("select id, nik, nama, tempat_lahir, tanggal_lahir, "
			+ "jenis_kelamin, is_wni, id_keluarga, agama, "
			+ "pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, "
			+ "is_wafat from penduduk where nik = #{nik}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nik", column="nik"),
			@Result(property="nama", column="nama"),
			@Result(property="tempatLahir", column="tempat_lahir"),
			@Result(property="tanggalLahir", column="tanggal_lahir"),
			@Result(property="jenisKelamin", column="jenis_kelamin"),
			@Result(property="isWni", column="is_wni"),
			@Result(property="idKeluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="statusPerkawinan", column="status_perkawinan"),
			@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
			@Result(property="golonganDarah", column="golongan_darah"),
			@Result(property="isWafat", column="is_wafat"),
			@Result(property="keluarga", column="id_keluarga",
			javaType = KeluargaModel.class, one=@One(select="selectKeluargabyId"))
	})
	PendudukModel selectPenduduk(@Param("nik") String nik);
	
	@Select("select id, nomor_kk, alamat, RT, RW, id_kelurahan, is_tidak_berlaku "
			+ "from keluarga where id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nomorKk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rT", column="RT"),
			@Result(property="rW", column="RW"),
			@Result(property="idKelurahan", column="id_kelurahan"),
			@Result(property="isTidakBerlaku", column="is_tidak_berlaku"),
			@Result(property="kelurahan", column="id_kelurahan",
			javaType=KelurahanModel.class, one=@One(select="selectKelurahanbyId")),
			@Result(property="daftarAnggota", column="id", 
			javaType=List.class, many=@Many(select="selectAnggotaKeluarga"))
	})
	KeluargaModel selectKeluargabyId(@Param("id") long id);
	
	@Select("select id, id_kecamatan, kode_kelurahan, nama_kelurahan, kode_pos "
			+ "from kelurahan where id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKecamatan", column="id_kecamatan"),
			@Result(property="kodeKelurahan", column="kode_kelurahan"),
			@Result(property="namaKelurahan", column="nama_kelurahan"),
			@Result(property="kodePos", column="kode_pos"),
			@Result(property="kecamatan", column="id_kecamatan", 
			javaType=KecamatanModel.class, one=@One(select="selectKecamatanbyId"))
	})
	KelurahanModel selectKelurahanbyId(@Param("id") long id);
	
	@Select("select id, id_kota, kode_kecamatan, nama_kecamatan "
			+ "from kecamatan where id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKota", column="id_kota"),
			@Result(property="kodeKecamatan", column="kode_kecamatan"),
			@Result(property="namaKecamatan", column="nama_kecamatan"),
			@Result(property="kota", column="id_kota", 
			javaType=KotaModel.class, one=@One(select="selectKotabyId"))
	})
	KecamatanModel selectKecamatanbyId(@Param("id") long id);
	
	@Select("select id, kode_kota, nama_kota from kota where id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="kodeKota", column="kode_kota"),
			@Result(property="namaKota", column="nama_kota")
	})
	KotaModel selectKotabyId(@Param("id") long id);
	
	@Select("select id, nomor_kk, alamat, RT, RW, id_kelurahan, is_tidak_berlaku from keluarga where nomor_kk = #{nkk}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nomorKk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rT", column="RT"),
			@Result(property="rW", column="RW"),
			@Result(property="idKelurahan", column="id_kelurahan"),
			@Result(property="kelurahan", column="id_kelurahan",
			javaType=KelurahanModel.class, one=@One(select="selectKelurahanbyId")),
			@Result(property="isTidakBerlaku", column="is_tidak_berlaku"),
			@Result(property="daftarAnggota", column="id", 
			javaType=List.class, many=@Many(select="selectAnggotaKeluarga"))
	})
	KeluargaModel selectKeluarga(@Param("nkk") String nkk);
	
	@Select("select id, nik, nama, tempat_lahir, tanggal_lahir, "
			+ "jenis_kelamin, is_wni, id_keluarga, agama, "
			+ "pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, "
			+ "is_wafat from penduduk where id_keluarga = #{idKeluarga}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nik", column="nik"),
			@Result(property="nama", column="nama"),
			@Result(property="tempatLahir", column="tempat_lahir"),
			@Result(property="tanggalLahir", column="tanggal_lahir"),
			@Result(property="jenisKelamin", column="jenis_kelamin"),
			@Result(property="isWni", column="is_wni"),
			@Result(property="idKeluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="statusPerkawinan", column="status_perkawinan"),
			@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
			@Result(property="golonganDarah", column="golongan_darah"),
			@Result(property="isWafat", column="is_wafat")
	})
	List<PendudukModel> selectAnggotaKeluarga(@Param("idKeluarga") long idKeluarga);
	
	@Insert("insert into penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, "
			+ "is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, "
			+ "golongan_darah, is_wafat) "
			+ "values (#{nik}, #{nama}, #{tempatLahir}, #{tanggalLahir}, #{jenisKelamin}, #{isWni}, #{idKeluarga}, "
			+ "#{agama}, #{pekerjaan}, #{statusPerkawinan}, #{statusDalamKeluarga}, #{golonganDarah}, #{isWafat})")
	void addPendudukBaru(PendudukModel penduduk);
	
	@Insert("insert into keluarga (nomor_kk, alamat, RT, RW, id_kelurahan) "
			+ "values (#{nomorKk}, #{alamat}, #{rT}, #{rW}, #{idKelurahan})")
	void addKeluargaBaru(KeluargaModel keluarga);
	
	@Select("select id from kelurahan where nama_kelurahan = #{namaKelurahan}")
	long getIdKelurahan(@Param("namaKelurahan") String namaKelurahan);
	
	@Update("update penduduk set nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempatLahir}, "
			+ "tanggal_lahir = #{tanggalLahir}, jenis_kelamin = #{jenisKelamin}, is_wni = #{isWni}, "
			+ "id_keluarga = #{idKeluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}, "
			+ "status_perkawinan = #{statusPerkawinan}, "
			+ "status_dalam_keluarga = #{statusDalamKeluarga}, golongan_darah = #{golonganDarah} "
			+ "where id = #{id}")
	void updatePenduduk(PendudukModel penduduk);
	
	@Update("update keluarga set nomor_kk = #{nomorKk}, alamat = #{alamat}, RT = #{rT}, "
			+ "RW = #{rW}, id_kelurahan = #{idKelurahan} where id = #{id}")
	void updateKeluaga(KeluargaModel keluarga);
	
	@Update("update penduduk set is_wafat = 1 where nik = #{nik}")
	void updateKematian(PendudukModel penduduk);
	
	@Update("update keluarga set is_tidak_berlaku = 1 where id = #{id}")
	void updateBerlaku(KeluargaModel keluarga);
	
	@Select("select * from penduduk "
			+ "join keluarga on penduduk.id_keluarga = keluarga.id "
			+ "join kelurahan on keluarga.id_kelurahan = kelurahan.id "
			+ "join kecamatan on kelurahan.id_kecamatan = kecamatan.id "
			+ "join kota on kecamatan.id_kota = kota.id "
			+ "where kota.id = #{idKota} and kecamatan.id = #{idKecamatan} and kelurahan.id = #{idKelurahan}")
	List<PendudukModel> selectPendudukbyKotaKecamatanKelurahan(@Param("idKota") long idKota, 
			@Param("idKecamatan") long idKecamatan, 
			@Param("idKelurahan") long idKelurahan);
	
	@Select("select * from kota")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="kodeKota", column="kode_kota"),
			@Result(property="namaKota", column="nama_kota"),
	})
	List<KotaModel> daftarKota();
	
	@Select("select * from kecamatan where id_kota = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKota", column="id_kota"),
			@Result(property="kodeKecamatan", column="kode_kecamatan"),
			@Result(property="namaKecamatan", column="nama_kecamatan")
	})
	List<KecamatanModel> daftarKecamatanbyKota(@Param("id") long id);
	
	@Select("select * from kelurahan where id_kecamatan = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKecamatan", column="id_kecamatan"),
			@Result(property="kodeKelurahan", column="kode_kelurahan"),
			@Result(property="namaKelurahan", column="nama_kelurahan"),
			@Result(property="kodePos", column="kode_pos")
	})
	List<KelurahanModel> daftarKelurahanbyKecamatan(@Param("id") long id);
	
	@Select("select penduduk.id, nik, nama, tempat_lahir, tanggal_lahir, "
			+ "jenis_kelamin, is_wni, id_keluarga, agama, "
			+ "pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, "
			+ "is_wafat from penduduk "
			+ "join keluarga on penduduk.id_keluarga = keluarga.id "
			+ "where keluarga.id_kelurahan = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nik", column="nik"),
			@Result(property="nama", column="nama"),
			@Result(property="tempatLahir", column="tempat_lahir"),
			@Result(property="tanggalLahir", column="tanggal_lahir"),
			@Result(property="jenisKelamin", column="jenis_kelamin"),
			@Result(property="isWni", column="is_wni"),
			@Result(property="idKeluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="statusPerkawinan", column="status_perkawinan"),
			@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
			@Result(property="golonganDarah", column="golongan_darah"),
			@Result(property="isWafat", column="is_wafat"),
			@Result(property="keluarga", column="id_keluarga",
			javaType = KeluargaModel.class, one=@One(select="selectKeluargabyId"))
	})
	List<PendudukModel> daftarPendudukbyKelurahan(@Param("id") long id);
}
