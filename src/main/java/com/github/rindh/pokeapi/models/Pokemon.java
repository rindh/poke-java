package com.github.rindh.pokeapi.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Pokemon {
    int id;
    String name;
    List<Moves> moves;
    Species species;
    List<Stats> stats;
}
