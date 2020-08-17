package edu.miu.shop.eshopreport.controller;

import java.io.*;
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import edu.miu.shop.eshopreport.domain.SearchHistory;
import edu.miu.shop.eshopreport.service.HistorySearchService;
import edu.miu.shop.eshopreport.service.impl.HistorySearchServiceImpl;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/reports/export")
public class SearchHistoryController {

	@Autowired
	HistorySearchServiceImpl hisService;
//
//	@GetMapping("/export/html")
//	private String exportReport() throws FileNotFoundException, JRException {
//		return hisService.exportReport("html");
//	}


//	@GetMapping
//	    @Path("/download")
//	    @Produces(MediaType.APPLICATION_OCTET_STREAM)
//	    public Response downloadFileWithGet(@QueryParam("file") String file) {
//	        System.out.println("Download file "+file);
//	        File fileDownload = new File(Config.UPLOAD_FOLDER + File.separator + file);
//	        ResponseBuilder response = Response.ok((Object) fileDownload);
//	        response.header("Content-Disposition", "attachment;filename=" + file);
//	        return response.build();
//	    }

	@RequestMapping("/pdf")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
		File file = new File(hisService.exportReport("pdf"));
		if (file.exists()) {
			//get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);

			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			//Here we have mentioned it to show as attachment
			//response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
	}
}