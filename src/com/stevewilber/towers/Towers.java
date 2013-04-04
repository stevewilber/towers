package com.stevewilber.towers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Start with initial config
 * generate all 1-move configs
 * ..and so on until finding answer, less than 8 moves
 * 
 * @author swilber
 *
 */
public class Towers {
  public static final int MAX_COUNT = 7;
  
  private int n;
  private int k;
  private Config initial;
  private Config desired;
  private int count;
  
  public static void main(String[] args) {
    Towers towers = new Towers();
    towers.readInput();
    towers.solve();
  }

  private void readInput() {
    Scanner scanner = new Scanner(System.in);
    n = scanner.nextInt(); //discs
    k = scanner.nextInt(); //pegs
    int[] initialArr = new int[n]; //index is radius, value is peg
    for (int i = 0; i < n; i++) {
      initialArr[i] = scanner.nextInt();
    }
    initial = new Config(null, initialArr, null);
    int[] desiredArr = new int[n];
    for (int i = 0; i < n; i++) {
      desiredArr[i] = scanner.nextInt();
    }
    desired = new Config(null, desiredArr, null);
  }
  
  private void solve() {
    solve(initial.getNextConfigs(k));
  }

  private void solve(List<Config> configs) {
    count++;
    for (Config config : configs) {
      if (config.equals(desired)) {
        displaySolution(config);
        return;
      }
    }
    
    if (count >= MAX_COUNT) {
      System.out.println("No solution in <= 7 moves");
      return;
    }
    
    List<Config> nextLevel = new ArrayList<Config>();
    for (Config config : configs) {
      nextLevel.addAll(config.getNextConfigs(k));
    }
    solve(nextLevel);
  }

  private void displaySolution(Config config) {
    System.out.println(count);
    List<String> moves = new ArrayList<String>();
    do {
      if (config.getMove() != null) {
        moves.add(config.getMove());
      }
    } while ((config = config.getPrevious()) != null);
    Collections.reverse(moves);
    for (String move : moves) {
      System.out.println(move);
    }
  }
  
}
