package com.example.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.service.PendudukService;

@Controller
public class PendudukController {
	
	@Autowired
	PendudukService pendudukDAO;

	private String templateName;
	
	@RequestMapping("/")
	public String index(Model model) {
		templateName = "index";
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/penduduk", method = RequestMethod.GET)
	public String viewPenduduk(Model model, 
			@RequestParam(value = "nik", required = false) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk == null) {
			templateName = "not-found-penduduk";
			model.addAttribute("nik", nik);
			model.addAttribute("templateName", templateName);
			return templateName;
		}
		templateName = "view-penduduk";
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/keluarga", method = RequestMethod.GET)
	public String viewKeluarga(Model model, 
			@RequestParam(value = "nkk", required = false) String nkk) {
		KeluargaModel keluarga = pendudukDAO.selectKeluarga(nkk);
		if (keluarga == null) {
			templateName = "not-found-keluarga";
			model.addAttribute("nkk", nkk);
			model.addAttribute("templateName", templateName);
			return templateName;
		}
		templateName = "view-keluarga";
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/penduduk/tambah")
	public String addPenduduk(Model model) {
		PendudukModel penduduk = new PendudukModel();
		templateName = "form-add-penduduk";
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
	public String addPendudukSubmit(Model model, PendudukModel penduduk) {
		penduduk.setKeluarga(pendudukDAO.getKeluargabyId(penduduk.getIdKeluarga()));
		String a = penduduk.getTanggalLahir().toString();
		String[] b = a.split("-");
		if(penduduk.getJenisKelamin() == 1) {
			int x = Integer.parseInt(b[2]) + 40;
			b[2] = Integer.toString(x);
		}
		int c = 1;
		while(c >= 1) {
			String d = penduduk.getKeluarga().getKelurahan().getKecamatan().getKodeKecamatan().substring(0, 6) 
					+ b[2] + b[1] + b[0].substring(2) + String.format("%04d", c);
			PendudukModel e = pendudukDAO.selectPenduduk(d);
			if(e == null) {
				penduduk.setNik(d);
				c = -1;
			} else {
				c = c + 1;
			}
		}
		pendudukDAO.addPendudukBaru(penduduk);
		model.addAttribute("nik", penduduk.getNik());
		templateName = "success-add-penduduk";
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/keluarga/tambah")
	public String addKeluarga(Model model) {
		KeluargaModel keluarga = new KeluargaModel();
		model.addAttribute("keluarga", keluarga);
		templateName = "form-add-keluarga";
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
	public String addKeluargaSubmit(Model model, KeluargaModel keluarga) {
		keluarga.setIdKelurahan(pendudukDAO.getIdKelurahan(keluarga.getKelurahan().getNamaKelurahan()));
		keluarga.setKelurahan(pendudukDAO.getKelurahanbyId(keluarga.getIdKelurahan()));
		Date sqldate = new Date(Calendar.getInstance().getTime().getTime());
		String a = sqldate.toString();
		String[] b = a.split("-");
		int c = 1;
		while (c >= 1) {
			String d = keluarga.getKelurahan().getKecamatan().getKodeKecamatan().substring(0, 6)
					+ b[2] + b[1] + b[0].substring(2) + String.format("%04d", c);
			KeluargaModel e = pendudukDAO.selectKeluarga(d);
			if (e == null) {
				keluarga.setNomorKk(d);
				c = -1;
			} else {
				c = c + 1;
			}
		}
		pendudukDAO.addKeluargaBaru(keluarga);
		model.addAttribute("nkk", keluarga.getNomorKk());
		templateName = "success-add-keluarga";
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/penduduk/ubah/{nik}")
	public String updatePenduduk(Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk  = pendudukDAO.selectPenduduk(nik);
		model.addAttribute("nik", nik);
		if (penduduk == null) {
			templateName = "not-found-penduduk";
			model.addAttribute("templateName", templateName);
			return templateName;
		}
		templateName = "form-update-penduduk";
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String updatePendudukSubmit(Model model, PendudukModel penduduk, @PathVariable(value = "nik") String nik) {
		model.addAttribute("nik", nik);
		penduduk.setKeluarga(pendudukDAO.getKeluargabyId(penduduk.getIdKeluarga()));
		String a = penduduk.getTanggalLahir().toString();
		String[] b = a.split("-");
		if(penduduk.getJenisKelamin() == 1) {
			int x = Integer.parseInt(b[2]) + 40;
			b[2] = Integer.toString(x);
		}
		int c = 1;
		while(c >= 1) {
			String d = penduduk.getKeluarga().getKelurahan().getKecamatan().getKodeKecamatan().substring(0, 6) 
					+ b[2] + b[1] + b[0].substring(2) + String.format("%04d", c);
			PendudukModel e = pendudukDAO.selectPenduduk(d);
			if(e == null) {
				penduduk.setNik(d);
				c = -1;
			} else if(e.getId() == penduduk.getId()) {
				c = -1;
			} else {
				c = c + 1;
			}
		}
		templateName = "success-update-penduduk";
		pendudukDAO.updatePenduduk(penduduk);
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}")
	public String updateKeluarga(Model model, @PathVariable(value = "nkk") String nkk) {
		KeluargaModel keluarga = pendudukDAO.selectKeluarga(nkk);
		model.addAttribute("nkk", nkk);
		if (keluarga == null) {
			templateName = "not-found-keluarga";
			model.addAttribute("templateName", templateName);
			return templateName;
		}
		templateName = "form-update-keluarga";
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}", method = RequestMethod.POST)
	public String updateKeluargaSubmit(Model model, KeluargaModel keluarga, @PathVariable(value = "nkk") String nkk) {
		model.addAttribute("nkk", nkk);
		keluarga.setIdKelurahan(pendudukDAO.getIdKelurahan(keluarga.getKelurahan().getNamaKelurahan()));
		keluarga.setKelurahan(pendudukDAO.getKelurahanbyId(keluarga.getIdKelurahan()));
		String a = keluarga.getNomorKk().substring(6, 12);
		int c = 1;
		while (c >= 1) {
			String d = keluarga.getKelurahan().getKecamatan().getKodeKecamatan().substring(0, 6)
					+ a + String.format("%04d", c);
			KeluargaModel e = pendudukDAO.selectKeluarga(d);
			if (e == null) {
				keluarga.setNomorKk(d);
				c = -1;
			} else if (e.getId() == keluarga.getId()){
				c = -1;
			} else {
				c = c + 1;
			}
		}
		pendudukDAO.updateKeluarga(keluarga);
		List<PendudukModel> anggota = pendudukDAO.selectKeluarga(keluarga.getNomorKk()).getDaftarAnggota();
		if(anggota != null) {
			for (PendudukModel p : anggota) {
				p.setKeluarga(pendudukDAO.getKeluargabyId(p.getIdKeluarga()));
				String aa = p.getTanggalLahir().toString();
				String[] b = aa.split("-");
				if(p.getJenisKelamin() == 1) {
					int x = Integer.parseInt(b[2]) + 40;
					b[2] = Integer.toString(x);
				}
				int cc = 1;
				while(cc >= 1) {
					String d = p.getKeluarga().getKelurahan().getKecamatan().getKodeKecamatan().substring(0, 6) 
							+ b[2] + b[1] + b[0].substring(2) + String.format("%04d", cc);
					PendudukModel e = pendudukDAO.selectPenduduk(d);
					if(e == null) {
						p.setNik(d);
						cc = -1;
					} else if(e.getId() == p.getId()) {
						cc = -1;
					} else {
						cc = cc + 1;
					}
				}
				pendudukDAO.updatePenduduk(p);
			}
		}
		templateName = "success-update-keluarga";
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	public String changeStatusKematian(Model model, PendudukModel penduduk) {
		pendudukDAO.updateKematian(penduduk);
		KeluargaModel keluarga = pendudukDAO.getKeluargabyId(penduduk.getIdKeluarga());
		List<PendudukModel> anggota = keluarga.getDaftarAnggota();
		int x = 0;
		for(PendudukModel p : anggota) {
			if (p.getIsWafat() == 1) {
				x = x + 1;
			}
		}
		if (x == anggota.size()) {
			pendudukDAO.updateBerlaku(keluarga);
		}
		templateName = "success-update-kematian";
		model.addAttribute("nik", penduduk.getNik());
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/penduduk/cari")
	public String cariPenduduk(Model model) {
		List<KotaModel> listKota = pendudukDAO.daftarKota();
		model.addAttribute("hasKotaSelected", false);
		model.addAttribute("hasKecamatanSelected", false);
		model.addAttribute("isAssignedtoKelurahan", false);
		model.addAttribute("listKota", listKota);
		templateName = "cari-penduduk";
		model.addAttribute("templateName", templateName);
		return templateName;
	}
	
	@RequestMapping(value = "/penduduk/cari", method = RequestMethod.GET)
	public String cariPendudukParam(Model model,
			@RequestParam(value = "id_kota", required = false) String idKota,
			@RequestParam(value = "id_kecamatan", required = false) String idKecamatan,
			@RequestParam(value = "id_kelurahan", required = false) String idKelurahan) {
		List<KotaModel> listKota = pendudukDAO.daftarKota();
		boolean hasKotaSelected = false;
		boolean hasKecamatanSelected = false;
		boolean hasKelurahanSelected = false;
		model.addAttribute("listKota", listKota);
		if (idKota != null) {
			hasKotaSelected = true;
			model.addAttribute("idKota", idKota);
			List<KecamatanModel> listKecamatan = pendudukDAO.daftarKecamatanbyKota(Long.parseLong(idKota));
			model.addAttribute("listKecamatan", listKecamatan);
			if(idKecamatan != null) {
				hasKecamatanSelected = true;
				model.addAttribute("idKecamatan", idKecamatan);
				List<KelurahanModel> listKelurahan = pendudukDAO.daftarKelurahanbyKecamatan(Long.parseLong(idKecamatan));
				model.addAttribute("listKelurahan", listKelurahan);
				if(idKelurahan != null) {
					hasKelurahanSelected = true;
					model.addAttribute("idKelurahan", idKelurahan);
					List<PendudukModel> listPenduduk = pendudukDAO.selectPendudukbyKelurahan(Long.parseLong(idKelurahan));
					model.addAttribute("listPenduduk", listPenduduk);
				}
			}
		}
		model.addAttribute("hasKotaSelected", hasKotaSelected);
		model.addAttribute("hasKecamatanSelected", hasKecamatanSelected);
		model.addAttribute("isAssignedtoKelurahan", hasKelurahanSelected);
		templateName = "cari-penduduk";
		model.addAttribute("templateName", templateName);
		return templateName;
	}
}
