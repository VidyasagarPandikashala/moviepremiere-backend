package com.moviepremierebackend.utilityFunctions;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PaginationUtils {
	public final static String startIndex = "startIndex";
	public final static String endIndex = "endIndex";

	public static HashMap<String, Integer> calculateStartIndexAndLastIndex1(int pageNumber, int size) {
		HashMap<String, Integer> index = new HashMap<>();
		index.put(endIndex, pageNumber * size);
		index.put(startIndex, index.get(endIndex) - size);
		return index;
	}
}
