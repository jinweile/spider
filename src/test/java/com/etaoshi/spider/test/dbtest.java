package com.etaoshi.spider.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.etaoshi.spider.analysis.IResultInDb;
import com.etaoshi.spider.analysis.RuleExtractor;
import com.etaoshi.spider.analysis.SpiderWorker;
import com.etaoshi.spider.comm.*;
import com.etaoshi.spider.service.intf.*;
import com.etaoshi.spider.job.MainJob.SchedulerHelper;
import com.etaoshi.spider.model.*;

public class dbtest {

	@Test
	public void test() throws SQLException {
		//获取某个模版
		/*ISourceSpiderService ssservice = SpringContext.getInstance().getBean("ISourceSpiderService", ISourceSpiderService.class);
		final ISpiderColumnService scservice = SpringContext.getInstance().getBean("ISpiderColumnService", ISpiderColumnService.class);
		ISpiderRegTemplateService srtservice = SpringContext.getInstance().getBean("ISpiderRegTemplateService", ISpiderRegTemplateService.class);
		
		SourceSpider ss = ssservice.Find(3);
		List<SpiderRegTemplate> srtlist = srtservice.FindByTemplateid(ss.getTemplateid());
		Map<Integer, String> scmap = scservice.FindAllMap();
		
		String[] result = SpiderWorker.SpiderEntry(ss.getSpiderentryrule());
		
		SpiderWorker.RecursiveExtractTemplateInDb(ss.getId(), result[0], result[1], 0, ss.getTemplateid(), srtlist, scmap, null, 
				new IResultInDb(){
					public void Insert(List<String> insert_sql_list) {
						for(String sql : insert_sql_list){
							//if(sql.contains("insert into area"))
								System.out.println(sql);
							try {
								scservice.InsertIntoDataModel(sql);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
		});*/

	}
	
	@Test
	public void split(){
		//RuleExtractor.ExtractPage("1-2|1");
		
		String ss = "\r\n                                                    			人均消费：70.22元                                                                ";
		System.out.println(ss.replaceAll("\\s", ""));
	}

	@Test
	public void path() {
		/*try {  
            // FileSystems.getDefault().getPath(first, more)  
            Path path = Paths.get(ToolsUtils.GetClassPath(), "quartz.properties");  
            Path real_path = path.toRealPath(LinkOption.NOFOLLOW_LINKS);  
            System.out.println("Path to real path: " + real_path);  
  
            System.out.println("Number of name elements in path: "  
                    + path.getNameCount());  
            for (int i = 0; i < path.getNameCount(); i++) {  
                System.out.println("Name element " + i + " is: "  
                        + path.getName(i));  
            }  
            System.out.println("Subpath (0,3): " + path.subpath(0, 3));  
  
            File path_to_file = path.toFile();  
            Path file_to_path = path_to_file.toPath();  
            System.out.println("Path to file name: " + path_to_file.getName());  
            System.out.println("File to path: " + file_to_path.toString());  
  
            Path base = Paths.get(System.getProperty("user.home"), "www", "pyweb.settings");  
            // resolve AEGON.txt file  
            Path path1 = base.resolve("django.wsgi");  
            System.out.println(path1.toString());  
  
            Path path2 = base.resolveSibling(".bashrc");  
            System.out.println(path2.toString());  
  
            Path path01_to_path02 = path1.relativize(path2);  
            System.out.println(path01_to_path02);  
  
            try {  
                boolean check = Files.isSameFile(path1.getParent(),  
                        path2.getParent());  
                if (check) {  
                    System.out.println("The paths locate the same file!"); // true  
                } else {  
                    System.out  
                            .println("The paths does not locate the same file!");  
                }  
            } catch (IOException e) {  
                System.out.println(e.getMessage());  
            }  
  
            boolean sw = path1.startsWith("/rafaelnadal/tournaments");  
            boolean ew = path1.endsWith("django.wsgi");  
            System.out.println(sw);  
            System.out.println(ew);  
  
            for (Path name : path1) {  
                System.out.println(name);  
            }  
  
        } catch (NoSuchFileException e) {  
            System.err.println(e);  
        } catch (IOException e) {  
            System.err.println(e);  
        }*/
	}

}
