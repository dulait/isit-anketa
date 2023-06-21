package database_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class DatabaseConnection {

	private static List<Student> unesiStudenta() {
		List<Student> studenti = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "");
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM student");

			while (rs.next()) {
				int id = rs.getInt("ID");
				int brojGodina = rs.getInt("broj_godina");
				String pol = rs.getString("pol");
				String godinaStudija = rs.getString("godina_studija");
				String finansiranje = rs.getString("finansiranje");
				String mestoUcenja = rs.getString("mesto_ucenja");
				String organizacija = rs.getString("organizacija");

				int[] ocene = new int[43];
				for (int i = 8; i <= 50; i++) {
					ocene[i - 8] = rs.getInt(i);
				}

				String praksa = rs.getString("strucna_praksa");
				String zavrsni = rs.getString("zavrsni");
				String insertedAt = rs.getString("inserted_at");

				Student student = new Student(id, brojGodina, pol, godinaStudija, finansiranje, mestoUcenja,
						organizacija, ocene, praksa, zavrsni, insertedAt);
				studenti.add(student);
			}

		} catch (Exception e) {
			System.err.println("Greska");
			System.err.println(e.getMessage());
		}
		return studenti;
	}

	private static void ispisiStudenta(List<Student> studenti) {
		String[] ispiti = new String[] { "Matematika 1", "Ekonomija", "Menadzment", "OIKT",
				"Sociologija ili Psihologija", "Strani jezik 1", "Matematika 2", "Programiranje 1",
				"Osnovi organizacije", "Proizvodni sistemi", "UIS", "AROS", "Programiranje 2", "Matematika 3",
				"Marketing", "Teorija verovatnoce", "Strani jezik 2", "Strukture podatka i algoritmi", "Statistika",
				"MTR", "FMiR", "DMS ili Numericka analiza", "RMT", "Operaciona istrazivanja 1", "Teorija sistema",
				"EPOS", "MLJR ili Upravljanje projektima", "Operaciona istrazivanja 2", "Baze podataka",
				"Programski jezici", "POIS", "MPP", "Teorija odlucivanja ili Lins",
				"Projektovanje informacionih sistema", "Internet tehnologije", "Simulacija i simulacioni jezici",
				"Projektovanje softvera", "Izborni ISiT-1", "Inteligentni sistemi", "Osnove kvaliteta",
				"Izborni ISiT-2", "Izborni ISiT-3", "Izborni ISiT-4" };

		for (Student student : studenti) {
			System.out.println("ID: " + student.getId());
			System.out.println("Broj godina: " + student.getBrojGodina());
			System.out.println("Pol: " + student.getPol());
			System.out.println("Godina studija: " + student.getGodinaStudija());
			System.out.println("Semestar: " + student.semestar() + ".");
			System.out.println("Finansiranje: " + student.getFinansiranje());
			System.out.println("Mesto ucenja: " + student.getMestoUcenja());
			System.out.println("Organizacija: " + student.getOrganizacija());

			for (int i = 0; i < student.getOcene().length; i++) {
				if (student.getOcene()[i] == 0) {
					System.out.println(ispiti[i] + ": " + "Nije jos polozio");
				} else {
					System.out.println(ispiti[i] + ": " + student.getOcene()[i]);
				}
			}
			if (student.getPraksa().equals("0")) {
				System.out.println("Strucna praksa: Nije odradjena");
			} else {
				System.out.println("Strucna praksa: " + student.getPraksa());
			}

			if (student.getZavrsni().equals("0")) {
				System.out.println("Zavrsni rad: Nije odbranjen");
			} else {
				System.out.println("Zavrsni rad: " + student.getZavrsni());
			}
			System.out.println("Prosek: " + student.prosek());
			System.out.println("Inserted at: " + student.getInsertedAt());
		}
	}

	private static int brMuskih(List<Student> studenti) {
		int brMuskih = 0;
		for (Student student : studenti) {
			if (student.getPol().equals("muski")) {
				brMuskih++;
			}
		}
		return brMuskih;
	}

	private static int brZenskih(List<Student> studenti) {
		int brZenskih = 0;
		for (Student student : studenti) {
			if (student.getPol().equals("zenski")) {
				brZenskih++;
			}
		}
		return brZenskih;
	}

	private static int brBudzet(List<Student> studenti) {
		int brBudzet = 0;
		for (Student student : studenti) {
			if (student.getFinansiranje().equals("budzet")) {
				brBudzet++;
			}
		}
		return brBudzet;
	}

	private static int brSamofin(List<Student> studenti) {
		int brSamofin = 0;
		for (Student student : studenti) {
			if (student.getFinansiranje().equals("samostalno")) {
				brSamofin++;
			}
		}
		return brSamofin;
	}

	private static int brKodKuce(List<Student> studenti) {
		int brKodKuce = 0;
		for (Student student : studenti) {
			if (student.getMestoUcenja().equals("kod_kuce")) {
				brKodKuce++;
			}
		}
		return brKodKuce;
	}

	private static int brCitaonica(List<Student> studenti) {
		int brCitaonica = 0;
		for (Student student : studenti) {
			if (student.getMestoUcenja().equals("citaonica")) {
				brCitaonica++;
			}
		}
		return brCitaonica;
	}

	private static int brUOrg(List<Student> studenti) {
		int brUOrg = 0;
		for (Student student : studenti) {
			if (student.getOrganizacija().equals("da")) {
				brUOrg++;
			}
		}
		return brUOrg;
	}

	private static int brNeOrg(List<Student> studenti) {
		int brNeOrg = 0;
		for (Student student : studenti) {
			if (student.getOrganizacija().equals("ne")) {
				brNeOrg++;
			}
		}
		return brNeOrg;
	}

	private static double maksProsek(List<Student> studenti) {
		double prosek = 0.0;
		for (Student student : studenti) {
			if (student.prosek() > prosek) {
				prosek = student.prosek();
			}
		}
		return prosek;
	}

	private static double minProsek(List<Student> studenti) {
		double prosek = Integer.MAX_VALUE;
		for (Student student : studenti) {
			if (student.prosek() < prosek) {
				prosek = student.prosek();
			}
		}
		return prosek;
	}

	public static Map<Integer, Integer> calculateGradeFrequencies(List<Student> students) {
		Map<Integer, Integer> frequencies = new HashMap<>();
		for (Student student : students) {
			for (int grade : student.getOcene()) {
				int count = frequencies.getOrDefault(grade, 0);
				frequencies.put(grade, count + 1);
			}
		}
		return frequencies;
	}

	public static void drawGradeHistogram(Map<Integer, Integer> frequencies) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
			if (entry.getKey() != 0) {
				dataset.addValue(entry.getValue(), "Ocene", entry.getKey());
			}
		}

		JFreeChart chart = ChartFactory.createBarChart("Histogram Ocena", "Ocena", "Frekvencija", dataset,
				PlotOrientation.VERTICAL, false, true, false);
		ChartFrame frame = new ChartFrame("Histogram Ocena", chart);
		frame.pack();
		frame.setVisible(true);
	}

	public static Map<Integer, Double> calculateAverageGradeByExam(List<Student> students, int startExamIndex,
			int endExamIndex) {
		Map<Integer, Integer> gradeSum = new HashMap<>();
		Map<Integer, Integer> gradeCount = new HashMap<>();
		Map<Integer, Double> averageGradeByExam = new HashMap<>();

		for (Student student : students) {
			int[] ocene = student.getOcene();
			for (int i = startExamIndex; i <= endExamIndex; i++) {
				int grade = ocene[i];
				if (grade != 0) {
					gradeSum.put(i, gradeSum.getOrDefault(i, 0) + grade);
					gradeCount.put(i, gradeCount.getOrDefault(i, 0) + 1);
				}
			}
		}

		for (Map.Entry<Integer, Integer> entry : gradeSum.entrySet()) {
			int examIndex = entry.getKey();
			double averageGrade = (double) gradeSum.get(examIndex) / gradeCount.get(examIndex);
			averageGradeByExam.put(examIndex, averageGrade);
		}

		return averageGradeByExam;
	}

	public static void drawAverageGradeByExamHistogram(Map<Integer, Double> averageGradeByExam, String[] nazivIspita,
			String chartTitle) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Map.Entry<Integer, Double> entry : averageGradeByExam.entrySet()) {
			dataset.addValue(entry.getValue(), "Prosecna Ocena", nazivIspita[entry.getKey()]);
		}

		JFreeChart chart = ChartFactory.createBarChart(chartTitle, "Ispit", "Prosecna Ocena", dataset,
				PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		ChartFrame frame = new ChartFrame(chartTitle, chart);
		frame.pack();
		frame.setVisible(true);
	}

	public static void drawGradeLineChart(List<Student> students, String[] examNames) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int[][] yearExamIndices = { { 0, 10 }, { 11, 21 }, { 22, 32 }, { 33, 42 } };

		for (int i = 0; i < 4; i++) {
			Map<Integer, Double> averageGradeByExam = calculateAverageGradeByExam(students, yearExamIndices[i][0],
					yearExamIndices[i][1]);
			for (Map.Entry<Integer, Double> entry : averageGradeByExam.entrySet()) {
				int examIndex = entry.getKey();
				double averageGrade = entry.getValue();
				dataset.addValue(averageGrade, "Godina " + (i + 1), examNames[examIndex]);
			}
		}

		CategoryAxis xAxis = new CategoryAxis("Ispiti");
		NumberAxis yAxis = new NumberAxis("Prosecne Ocene");
		LineAndShapeRenderer renderer = new LineAndShapeRenderer(true, true);
		CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

		JFreeChart chart = new JFreeChart("Prosecne Ocene po Ispitu", JFreeChart.DEFAULT_TITLE_FONT, plot, true);

		JFrame frame = new JFrame("Prosecne Ocene po Ispitu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ChartPanel(chart));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		List<Student> studenti = unesiStudenta();

		System.out.println("--Studenti--");
		for (Student student : studenti) {
			ispisiStudenta(studenti);
			System.out.println("-----------------------------------");
		}

		System.out.println("Broj muskih studenata je: " + brMuskih(studenti));
		System.out.println("Broj zenskih studenata je: " + brZenskih(studenti));
		System.out.println("Broj studenata na budzetu je: " + brBudzet(studenti));
		System.out.println("Broj studenata na samofinansiranju je: " + brSamofin(studenti));
		System.out.println("Broj studenata koji vise uce kod kuce je: " + brKodKuce(studenti));
		System.out.println("Broj studenata koji vise uce u citaonici je: " + brCitaonica(studenti));
		System.out.println("Broj studenata u organizacijama je: " + brUOrg(studenti));
		System.out.println("Broj studenata koji ne pripadaju organizacijama je: " + brNeOrg(studenti));
		System.out.println("Najveci prosek je: " + maksProsek(studenti));
		System.out.println("Najmanji prosek je: " + minProsek(studenti));

		Map<Integer, Integer> frequencies = calculateGradeFrequencies(studenti);
		drawGradeHistogram(frequencies);

		String[] nazivIspita = { "M1", "EKO", "MEN", "OIKT", "SOC ili PSIH", "SJ1", "M2", "P1", "OO", "PS", "UIS",
				"AROS", "P2", "M3", "MRKT", "TV", "SJ2", "SPA", "STAT", "MTR", "FMIR", "DMS ili NUM", "RMT", "OI1",
				"TS", "EPOS", "MLJR ili UP", "OI2", "BP", "PJ", "POIS", "MPP", "TO ili LINS", "PIS", "IT", "SIM", "PRS",
				"ISIT1", "INT", "OK", "ISIT2", "ISIT3", "ISIT4" };

		Map<Integer, Double> firstYearAverage = calculateAverageGradeByExam(studenti, 0, 10);
		drawAverageGradeByExamHistogram(firstYearAverage, nazivIspita, "Prosek Ocena Ispita Prve Godine");

		Map<Integer, Double> secondYearAverage = calculateAverageGradeByExam(studenti, 11, 21);
		drawAverageGradeByExamHistogram(secondYearAverage, nazivIspita, "Prosek Ocena Ispita Druge Godine");

		Map<Integer, Double> thirdYearAverage = calculateAverageGradeByExam(studenti, 22, 32);
		drawAverageGradeByExamHistogram(thirdYearAverage, nazivIspita, "Prosek Ocena Ispita Trece Godine");

		Map<Integer, Double> fourthYearAverage = calculateAverageGradeByExam(studenti, 33, 42);
		drawAverageGradeByExamHistogram(fourthYearAverage, nazivIspita, "Prosek Ocena Ispita Cetvrte Godine");

		drawGradeLineChart(studenti, nazivIspita);

	}

}
