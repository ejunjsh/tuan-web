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
public class LaShouAnalyser extends BaseAnalyser implements IAnalyser {
    
	@Value("#{lashouSetting['lashou.cityUrl']}")
	private String cityUrl;
	
	@Value("#{lashouSetting['lashou.key']}")
	private String key;
	
	@Value("#{lashouSetting['lashou.dealUrl']}")
	private String dealUrl;
	
	@Value("#{analyseSetting['analyse.isUpdateCity']}")
	private int isUpdateCity;
	
	@Value("#{analyseSetting['analyse.isUpdateTag']}")
	private int isUpdateTag;
	
	static Logger logger = Logger.getLogger(LaShouAnalyser.class.getName());
	
	@Override
	public void analyse() throws IOException
	{
		Document cityDoc = Jsoup.connect(cityUrl)
				.timeout(60000)
     		   .maxBodySize(Integer.MAX_VALUE)
     		   .ignoreContentType(true)
     		   .parser(new Parser(new XmlTreeBuilder()))
				.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)") 
				.get();

		Elements eCitys = cityDoc.select("url");
		
		for(Element eCity:eCitys)
		{
           String name=eCity.getElementsByTag("city_name").first().text();
           
           String cityId=eCity.getElementsByTag("city_id").first().text();
           
           logger.debug("reading deals from city "+name);
           
           Document dealDoc=null;
           try
           { 
        	   dealDoc = Jsoup.connect(dealUrl+cityId+".xml")
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
           
           if(isUpdateCity==1)
           {
               City city=getCityRepository().findByName(name);
               if(city==null)
        	   {
	        	   String cnSpell=Pinyin4jUtil.converterToSpell(name);
	        	   String[] s=cnSpell.split("\\,");
        	   
        		   city=new City();
        		   city.setCnSpell(s[0]);
            	   city.setName(name);
            	   city.setRefCount(1);
            	   getCityRepository().insert(city);
        	   }
               else
               {
            	   city.setRefCount(city.getRefCount()+urls.size());
            	   getCityRepository().update(city);
               }
           }
           
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
        		   deal.setDealType(TuanTypeEnum.Lashou.getValue());
        	   }
        	   
        	   deal.setBoughtCount(Integer.parseInt(display.getElementsByTag("bought").first().text()));
        	   

        	   if(deal.getCity()==null)
        	   {
        		   deal.setCity(new ArrayList<City>());
        	   }
        	   boolean isContain=false;
        	   for(City c:deal.getCity())
        	   {
        		   if(c.getName()==name)
        		   {
        			   isContain=true;
        		   }
        		   break;
        	   }
        	   if(!isContain)
        	   {
        		   City c=new City();
            	   c.setName(name);
        		   deal.getCity().add(c);
        	   }
        	   
        	   deal.setCurrentPrice(Float.parseFloat(display.getElementsByTag("price").first().text()));
           
               deal.setEndTime(new Date(Long.parseLong(display.getElementsByTag("endTime").first().text())*1000));
           
               deal.setImageUrl(display.getElementsByTag("image").first().text());
               
               deal.setKey(this.key+display.getElementsByTag("identifier").first().text());
               
               deal.setOriginalPrice(Float.parseFloat(display.getElementsByTag("value").first().text()));
               
               deal.setRebate(Float.parseFloat(display.getElementsByTag("rebate").first().text()));
               
               deal.setShortTitle(display.getElementsByTag("shortTitle").first().text());
               
               deal.setSiteUrl(loc);
               
               deal.setStartTime(new Date(Long.parseLong(display.getElementsByTag("startTime").first().text())*1000));
               
               deal.setTitle(display.getElementsByTag("title").first().text());
               
               List<Tag> tags=new ArrayList<Tag>(2);
               
               splitTags(tags,display.getElementsByTag("class").first().text());
               
               splitTags(tags,display.getElementsByTag("subClass").first().text());
               
      
               
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
