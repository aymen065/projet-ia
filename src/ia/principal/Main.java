package ia.principal;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Aetoile aetoile = new Aetoile(5, 5, 0, 0, 3, 2, 
				new int[][] {
			{0,4},{2,2},{3,1},{3,3},{2,1},{2,3}
				}	
				);
		Depth depth = new Depth(5, 5, 0, 0, 3, 2, 
				new int[][] {
			{0,4},{2,2},{3,1},{3,3},{2,1},{2,3}
				});
		Breadth breadth = new Breadth(5, 5, 0, 0, 3, 2, 
				new int[][] {
			{0,4},{2,2},{3,1},{3,3},{2,1},{2,3}
				});
		String Q2 = "O";
		while(Q2.equals("O")){
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir A pour A* , L pour largeur dabord ou P pour profondeur");
		String Q1 = sc.nextLine();
		if(Q1.equals("A")) {
			aetoile.display();
			//début
			Clock clockDebut = Clock.systemDefaultZone();
			Instant instantDeb = clockDebut.instant();
			aetoile.process();
			Clock clockFin = Clock.systemDefaultZone();
			Instant instantFin = clockFin.instant();
			Duration duree = Duration.between(instantDeb, instantFin);
			aetoile.displayScores();
			clockDebut = Clock.systemDefaultZone();
			instantDeb = clockDebut.instant();
			aetoile.displaySolution();
			clockFin = Clock.systemDefaultZone();
			instantFin = clockFin.instant();
			//fin
			Duration duree2 = Duration.between(instantDeb, instantFin);
			System.out.println("la durée de A* en nano seconde est  : "+(duree.getNano()+duree2.getNano()));

		}
		else if(Q1.equals("L")) {
			breadth.setAdjacentCells();
			breadth.display();
			//début
			Clock clockDebut = Clock.systemDefaultZone();
			Instant instantDeb = clockDebut.instant();
			breadth.displaySolution();
			Clock clockFin = Clock.systemDefaultZone();
			Instant instantFin = clockFin.instant();
			Duration duree = Duration.between(instantDeb, instantFin);
			System.out.println("la durée de Larguer dabord en nano seconde est  : "+duree.getNano());

		}
		else if(Q1.equals("P")){
			depth.setAdjacentCells();
			depth.display();
			//début
			Clock clockDebut = Clock.systemDefaultZone();
			Instant instantDeb = clockDebut.instant();
			depth.displaySolution();
			Clock clockFin = Clock.systemDefaultZone();
			Instant instantFin = clockFin.instant();
			Duration duree = Duration.between(instantDeb, instantFin);
			System.out.println("la durée de profondeur en nano seconde est  : "+duree.getNano());

		}
		else {
			System.out.println("choix incorrecte !!!!! ");
		}
		System.out.println("voulez-vous répéter ? O/N");
		Q2 = sc.nextLine();
	}
		System.out.println("d'accord ,au revoir");

		
		
		
		
		
		
		
	}
}
