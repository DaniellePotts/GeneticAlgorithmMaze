package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import geneticalgorithm.*;

public class GATests {

	@Test
	public void TestMaxFitness(){ //check that we're returned the maximum fitness
		List<Gene>genes = new ArrayList<>();
		Gene gene1 = new Gene();
		Gene gene2 = new Gene();
		Gene gene3 = new Gene();
		Gene gene4 = new Gene();
		Gene gene5 = new Gene();
		Gene gene6 = new Gene();
		Gene gene7 = new Gene();
		Gene gene8 = new Gene();
		Gene gene9 = new Gene();
		Gene gene10 = new Gene();

		gene1.Fitness = 50;
		gene2.Fitness = 60;
		gene3.Fitness = 46;
		gene4.Fitness = 38;
		gene5.Fitness = 19;
		gene6.Fitness = 78;
		gene7.Fitness = 32;
		gene8.Fitness = 45;
		gene9.Fitness = 23;
		gene10.Fitness = 51;
		
		genes.add(gene1);
		genes.add(gene2);
		genes.add(gene3);
		genes.add(gene4);
		genes.add(gene5);
		genes.add(gene6);
		genes.add(gene7);
		genes.add(gene8);
		genes.add(gene9);
		genes.add(gene10);
		
		Fitness f = new Fitness();
		
		double expected = 78;
		double actual = f.MaxFitness(genes);
		
		assertEquals(expected, actual,0.1);
	}
	
	@Test
	public void TestGetFittestGene(){
		Gene g1 = new Gene();
		Gene g2 = new Gene();
		Gene g3 = new Gene();
		
		g1.Fitness = 13;
		g2.Fitness = 18;
		g3.Fitness = 26;
		
		Population pop = new Population(3);
		pop.Genes = new ArrayList<>();
		pop.Genes.add(g1);
		pop.Genes.add(g2);
		pop.Genes.add(g3);
		
		Gene actualFittest = pop.GetFittest(pop);
		Gene expectedFittest = g3;
		assertEquals(expectedFittest,actualFittest);
	}
	
	@Test
	public void TestGenesInit(){
		Population pop = new Population(3);
		assertNotNull(pop.Genes);
	}
	
	@Test
	public void TestPossibilities(){
		int expectedListSize = 64;
		Population pop = new Population(1);
		int actualPopSize = pop.GetGene(0).possibleMovements.size();
		
		assertEquals(expectedListSize,actualPopSize);
	}
}
