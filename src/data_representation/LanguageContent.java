package data_representation;

import java.util.List;
import java.util.Random;

public class LanguageContent {

	private String languageName;
	private List<Example> examples;
	
	public Example getRandomExample()
	{
		Random rnd = new Random();
		int randomRange=examples.size();
		int rand = rnd.nextInt(randomRange);
		return(examples.get(rand));
	}
	
	
	public void setLanguageName(String input)
	{
		languageName=input;
	}
	
	public String getLanguageName()
	{
		return(languageName);
	}
	
	public void setExaples(List<Example> input)
	{
		examples=input;
	}
	
	public List<Example> getExamples()
	{
		return(examples);
	}
	
}
