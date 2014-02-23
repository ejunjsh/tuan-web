package com.sky.tuan.web.job;

import java.io.IOException;
import java.util.List;

import com.sky.tuan.web.analyser.IAnalyser;

public class AnalyserJob {
	
   private List<IAnalyser> analysers;
   public void work() throws IOException
   {
	   for(IAnalyser a : analysers)
	   {
		   a.analyse();
	   }
   }

	public void setAnalysers(List<IAnalyser> analysers) {
		this.analysers = analysers;
	}
}
