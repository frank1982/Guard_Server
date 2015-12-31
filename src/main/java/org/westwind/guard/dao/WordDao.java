package org.westwind.guard.dao;

import java.util.List;

import org.westwind.guard.model.Product;
import org.westwind.guard.model.Word;

public interface WordDao {
	
	public List<Word> getWord(int limit,int offset);
	public void addWord(Word word);
	public void delWord(int id);
}
