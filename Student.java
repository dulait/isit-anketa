package database_conn;

import java.time.LocalDate;

public class Student {

	private int id;
	private int brojGodina;
	private String pol;
	private String godinaStudija;
	private String finansiranje;
	private String mestoUcenja;
	private String organizacija;
	private int[] ocene;
	private String praksa;
	private String zavrsni;
	private String insertedAt;

	public Student(int id, int brojGodina, String pol, String godinaStudija, String finansiranje, String mestoUcenja,
			String organizacija, int[] ocene, String praksa, String zavrsni, String insertedAt) {
		super();
		this.id = id;
		this.brojGodina = brojGodina;
		this.pol = pol;
		this.godinaStudija = godinaStudija;
		this.finansiranje = finansiranje;
		this.mestoUcenja = mestoUcenja;
		this.organizacija = organizacija;
		this.ocene = ocene;
		this.praksa = praksa;
		this.zavrsni = zavrsni;
		this.insertedAt = insertedAt;
	}

	public int getId() {
		return id;
	}

	public int getBrojGodina() {
		return brojGodina;
	}

	public String getPol() {
		return pol;
	}

	public String getGodinaStudija() {
		return godinaStudija;
	}

	public String getFinansiranje() {
		return finansiranje;
	}

	public String getMestoUcenja() {
		return mestoUcenja;
	}

	public String getOrganizacija() {
		return organizacija;
	}

	public int[] getOcene() {
		return ocene;
	}

	public String getPraksa() {
		return praksa;
	}

	public String getZavrsni() {
		return zavrsni;
	}

	public String getInsertedAt() {
		return insertedAt;
	}

	public double prosek() {
		int suma = 0;
		int brOcena = 0;
		for (int i = 0; i < ocene.length; i++) {
			if (ocene[i] != 0) {
				suma += ocene[i];
				brOcena++;
			}
		}
		return Math.round((double) suma / brOcena * 100) / 100.0;
	}

	public boolean prvaGodina() {
		return godinaStudija.equals("prva");
	}

	public boolean drugaGodina() {
		return godinaStudija.equals("druga");
	}

	public boolean trecaGodina() {
		return godinaStudija.equals("treca");
	}

	public boolean cetvrtaGodina() {
		return godinaStudija.equals("cetvrta");
	}

	public int semestar() {
		LocalDate insertedDate = LocalDate.parse(insertedAt.substring(0, 10));
		LocalDate currentDate = LocalDate.now();
		int semestar = 0;
		switch (godinaStudija) {
		case "prva":
			if (insertedDate.isAfter(currentDate.withMonth(10).minusYears(1))
					&& insertedDate.isBefore(currentDate.withMonth(3))) {
				semestar = 1;
			} else {
				semestar = 2;
			}
			break;
		case "druga":
			if (insertedDate.isAfter(currentDate.withMonth(10).minusYears(1))
					&& insertedDate.isBefore(currentDate.withMonth(3))) {
				semestar = 3;
			} else {
				semestar = 4;
			}
		case "treca":
			if (insertedDate.isAfter(currentDate.withMonth(10).minusYears(1))
					&& insertedDate.isBefore(currentDate.withMonth(3))) {
				semestar = 5;
			} else {
				semestar = 6;
			}
		case "cetvrta":
			if (insertedDate.isAfter(currentDate.withMonth(10).minusYears(1))
					&& insertedDate.isBefore(currentDate.withMonth(3))) {
				semestar = 7;
			} else {
				semestar = 8;
			}
		}
		return semestar;
	}

}
