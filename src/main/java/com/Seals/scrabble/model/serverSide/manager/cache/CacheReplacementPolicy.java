package com.Seals.scrabble.model.serverSide.manager.cache;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
