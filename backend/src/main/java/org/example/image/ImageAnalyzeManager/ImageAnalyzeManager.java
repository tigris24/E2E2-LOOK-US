package org.example.image.ImageAnalyzeManager;
import java.io.IOException;

import org.example.image.ImageAnalyzeManager.type.AnalyzeData;

public interface ImageAnalyzeManager {

	// analyze image and store to DB
	void requestAnalyze(Long resourceLocationId) throws IOException;

	// get analyzed data from DB
	AnalyzeData getAnaylzedData(Long resourceLocationId);
}
