package com.wyl.lotterycommon.utils.print;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.slf4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The type Jasper util.
 */
public class JasperUtil {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JasperUtil.class);

    public static final String PRINT_TYPE = "print";
    public static final String PDF_TYPE = "pdf";
    public static final String PDF_TYPE_DOWNLOAD = "pdf_download";
    public static final String EXCEL_TYPE = "excel";
    public static final String HTML_TYPE = "html";
    public static final String WORD_TYPE = "word";


    /**
     * 导出pdf，注意此处中文问题，
     * <p>
     * 这里应该详细说：主要在ireport里变下就行了。看图
     * <p>
     * 1）在ireport的classpath中加入iTextAsian.jar 2）在ireport画jrxml时，看ireport最左边有个属性栏。
     * <p>
     * 下边的设置就在点字段的属性后出现。 pdf font name ：STSong-Light ，pdf encoding ：UniGB-UCS2-H
     */
    private static void exportPdf(JasperPrint jasperPrint, String type,
                                  String defaultFilename, HttpServletRequest request,
                                  HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        String defaultname = null;
        if (defaultFilename != null && defaultFilename.trim() != null) {
            defaultname = defaultFilename + ".pdf";
        } else {
            defaultname = "export.pdf";
        }
        String fileName = new String(defaultname.getBytes("GBK"), "ISO8859-1");
        if (JasperUtil.PDF_TYPE_DOWNLOAD.equals(type)) {
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        }
        ServletOutputStream ouputStream = response.getOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ouputStream));

        //configuration
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCompressed(true);

        //set the configuration
        exporter.setConfiguration(configuration);
        //finally exporting the PDF
        exporter.exportReport();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
//        ouputStream.flush();
//        ouputStream.close();
    }


    /**
     * 按照类型导出不同格式文件
     *
     * @param defaultFilename 默认的导出文件的名称
     * @param datas                     数据
     * @param type                      文件类型
     * @param is                        jasper文件的来源
     * @param request
     * @param response
     */
    private static void export(Collection datas, String type, Map parameters,
                               String defaultFilename, InputStream is, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        logger.debug("导出判断     The method======= export() start.......................");
        try {
            response.setContentType("application/pdf");
            long currentTimeMillis = System.currentTimeMillis();
            JRDataSource ds = new JRBeanCollectionDataSource(datas);
            String defaultname = null;
            if (defaultFilename != null && defaultFilename.trim() != null) {
                defaultname = defaultFilename + ".pdf";
            } else {
                defaultname = "export.pdf";
            }
            String fileName = new String(defaultname.getBytes("GBK"), "ISO8859-1");
            if (JasperUtil.PDF_TYPE_DOWNLOAD.equals(type)) {
                response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            }
            parameters.put(JRPdfExporterParameter.IS_COMPRESSED, true);
            byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, ds);
            response.setContentLength(bytes.length);
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
            long endTimeMillis = System.currentTimeMillis();
            logger.info("报表导出耗时：" + (endTimeMillis - currentTimeMillis) / 1000 + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.getOutputStream().close();
        }
    }

    /**
     * 导出入口
     *
     * @param exportType      导出文件的类型
     * @param parameters
     * @param jaspername      jasper文件的名字 如： xx.jasper
     * @param lists           导出的数据
     * @param defaultFilename 默认的导出文件的名称
     * @param request
     * @param response
     * @author Tony
     * @date 2017年08月02日 18:30
     */
    public static void exportmain(String exportType, Map parameters, String jaspername,
                                  List lists, String defaultFilename, HttpServletRequest request,
                                  HttpServletResponse response) {
        logger.debug("进入导出    The method======= exportmain() start.......................");
        String filenurl = request.getRealPath("/") + "/ireport/" + jaspername;// jasper文件放在WebRoot/ireport/xx.jasper</span>
        System.out.println(filenurl);
        File file = new File(filenurl);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            export(lists, exportType, parameters, defaultFilename, is, request, response);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    private static void export1(Collection datas, String type, Map parameters,
                                String defaultFilename, InputStream is, HttpServletRequest request,
                                HttpServletResponse response) {
        logger.debug("导出1判断     The method======= export() start.......................");
        try {
            long currentTimeMillis = System.currentTimeMillis();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
            JRDataSource ds = new JRBeanCollectionDataSource(datas);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

            exportPdf(jasperPrint, type, defaultFilename, request, response);
            long endTimeMillis = System.currentTimeMillis();
            logger.info("报表导出耗时：" + (endTimeMillis - currentTimeMillis) / 60 + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
