package com.moviepremierebackend.utilityFunctions;

import java.util.ArrayList;



public class ArrayListUtitlity<T> {
	public static<T> ArrayList<T> sliceData(ArrayList<T>data , int startIndex, int endIndex ){
		
		return  new ArrayList<T>(data.subList(startIndex, Math.min(data.size(), endIndex)));
		
	}

}
