package com.example.scrabble.server.managers;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
