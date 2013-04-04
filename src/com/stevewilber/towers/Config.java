package com.stevewilber.towers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
  private int[] discs; //index is radius of disc, value is the peg
  private Config previous;
  private String move;
  
  public Config(Config previous, int[] discs, String move) {
    this.previous = previous;
    this.discs = discs;
    this.move = move;
  }
  
  public List<Config> getNextConfigs(int k) {
    List<Config> configs = new ArrayList<Config>();
    for (int radius = 0; radius < discs.length; radius++) {
      int currentPeg = discs[radius];
      for (int pegNumber = 1; pegNumber <= k; pegNumber++) {
        if (pegNumber != currentPeg && !discCovered(radius) && discIsSmallerThan(pegNumber, radius)) {
          int[] newDiscs = Arrays.copyOf(discs, discs.length);
          newDiscs[radius] = pegNumber;
          configs.add(new Config(this, newDiscs, currentPeg + " " + pegNumber));
        }
      }
    }
        
    return configs;
  }
  
  private boolean discCovered(int radius) {
    int pegNumber = discs[radius];
    for (int i = 0; i < radius; i++) {
      if (discs[i] == pegNumber) {
        return true;
      }
    }
    return false;
  }

  private boolean discIsSmallerThan(int pegNumber, int radius) {
    for (int i = 0; i < radius; i++) {
      if (discs[i] == pegNumber) {
        return false;
      }
    }
    
    return true;
  }

  public boolean equals(Object o) {
    if (o instanceof Config) {
      return Arrays.equals(discs, ((Config)o).discs);
    }
    
    return false;
  }
  
  public Config getPrevious() {
    return previous;
  }
  
  public String getMove() {
    return move;
  }
}
