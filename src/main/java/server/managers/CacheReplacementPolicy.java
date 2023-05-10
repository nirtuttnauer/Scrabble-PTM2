package server.managers;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
