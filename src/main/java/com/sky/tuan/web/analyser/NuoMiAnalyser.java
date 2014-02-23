package com.sky.tuan.web.analyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sky.tuan.enumeration.TuanTypeEnum;
import com.sky.tuan.mongodb.entity.City;
import com.sky.tuan.mongodb.entity.Deal;
import com.sky.tuan.mongodb.entity.Tag;
import com.sky.tuan.web.utils.Pinyin4jUtil;


@Component
public class NuoMiAnalyser extends BaseAnalyser implements IAnalyser {
    
	@Value("#{nuomiSetting['nuomi.allCity']}")
	private String allCity;
	
	@Value("#{nuomiSetting['nuomi.key']}")
	private String key;
	
	@Value("#{nuomiSetting['nuomi.dealUrl']}")
	private String dealUrl;
	
	@Value("#{analyseSetting['analyse.isUpdateCity']}")
	private int isUpdateCity;
	
	@Value("#{analyseSetting['analyse.isUpdateTag']}")
	private int isUpdateTag;
	
	static Logger logger = Logger.getLogger(NuoMiAnalyser.class.getName());
	
	@Override
	public void analyse() throws IOException
	{
        
		String[] eCitys = allCity.split("\\,");
		
		for(String cityId:eCitys)
		{    
           logger.debug("reading deals from city "+cityId);
           
           Document dealDoc=null;
           try
           { 
        	   dealDoc = Jsoup.connect(dealUrl+cityId)
        		   .timeout(60000)
        		   .maxBodySize(Integer.MAX_VALUE)
        		   .ignoreContentType(true)
        		   .parser(new Parser(new XmlTreeBuilder()))
        		   .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)") 
        		   .get();
           }
           catch(Exception e)
           {
        	   logger.debug(e);
        	   continue;
           }
           
           Elements urls=dealDoc.select("url");
           
          
           
           for(Element url :urls)
           {
        	   try
        	   {
        	   Element data=url.getElementsByTag("data").first(); 

        	   String loc=url.getElementsByTag("loc").first().text(); 
        	   
        	   Element display=data.getElementsByTag("display").first();
        	   
        	   String key =display.getElementsByTag("identifier").first().text();
        	   
        	   Deal deal=getDealRepository().findByKey(this.key+key);
        	   
        	   boolean isAddData=false;
        	   if(deal==null)
        	   {
        		   deal=new Deal();
        		   isAddData=true;
        		   deal.setDealType(TuanTypeEnum.Nuomi.getValue());
        	   }
        	   
        	   deal.setBoughtCount(Integer.parseInt(display.getElementsByTag("bought").first().text()));
        	   
        	   String cityName=display.getElementsByTag("city").first().text();
        	   if(isUpdateCity==1)
               {
                   City city=getCityRepository().findByName(cityName);
                   if(city==null)
            	   {
            		   city=new City();
            		   city.setCnSpell(cityId);
                	   city.setName(cityName);
                	   city.setRefCount(1);
                	   getCityRepository().insert(city);
            	   }
                   else
                   {
                	   city.setRefCount(city.getRefCount()+1);
                	   getCityRepository().update(city);
                   }
               }
        	   
        	   if(deal.getCity()==null)
        	   {
        		   deal.setCity(new ArrayList<City>());
        	   }
        	   boolean isContain=false;
        	   for(City c:deal.getCity())
        	   {
        		   if(c.getName()==cityName)
        		   {
        			   isContain=true;
        		   }
        		   break;
        	   }
        	   if(!isContain)
        	   {
        		   City c=new City();
            	   c.setName(cityName);
        		   deal.getCity().add(c);
        	   }
        	   
        	   deal.setCurrentPrice(Float.parseFloat(display.getElementsByTag("price").first().text()));
           
               deal.setEndTime(new Date(Long.parseLong(display.getElementsByTag("endTime").first().text())*1000));
           
               deal.setImageUrl(display.getElementsByTag("image").first().text());
               
               deal.setKey(this.key+display.getElementsByTag("identifier").first().text());
               
               deal.setOriginalPrice(Float.parseFloat(display.getElementsByTag("value").first().text()));
               
               deal.setRebate(Float.parseFloat(display.getElementsByTag("rebate").first().text()));
               
               deal.setShortTitle(display.getElementsByTag("title").first().text());
               
               deal.setSiteUrl(loc);
               
               deal.setStartTime(new Date(Long.parseLong(display.getElementsByTag("startTime").first().text())*1000));
               
               deal.setTitle(display.getElementsByTag("title").first().text());
               
               List<Tag> tags=new ArrayList<Tag>(2);
               
               splitTags(tags,display.getElementsByTag("firstCategory").first().text());
               
               splitTags(tags,display.getElementsByTag("secondCategory").first().text());
               
      
               
               if(isUpdateTag==1)
               {
            	   for(Tag t:tags)
            	   {
            		   Tag t1=getTagRepository().findByName(t.getName());
            		   if(t1==null)
            		   {
            			   t1=new Tag();
            			   t1.setName(t.getName());
            			   String cnSpell=Pinyin4jUtil.converterToSpell(t.getName());
                    	   String[] s=cnSpell.split("\\,");
                    	   t1.setCnSpell(s[0]);
                    	   getTagRepository().insert(t1);
            		   }
            		   else
            		   {
            			   t1.setRefCount(t1.getRefCount()+1);
            			   getTagRepository().update(t1);
            		   }
            	   }
               }
               
               deal.setTags(tags);
               
               if(isAddData)
               {
            	   getDealRepository().insert(deal);
               }
               else
               {
            	   getDealRepository().update(deal);
               }
               }
        	   catch(Throwable e)
        	   {
        		   continue;
        	   }
           }

		}
	}
}
