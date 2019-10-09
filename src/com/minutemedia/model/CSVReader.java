package com.minutemedia.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

	public static List<String[]> read(String file) {
		File myFile = new File(file);
		List<String[]> content = new ArrayList<>();
		int iteration = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(myFile))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				if (iteration == 0) {
					iteration++;
					continue;
				}

				content.add(line.split(","));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return content;
	}
}
