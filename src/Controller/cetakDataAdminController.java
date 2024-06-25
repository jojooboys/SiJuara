/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Profil;
import Model.User;
import Model.UserDao;
import View.DashboardClient;
import View.cetakDataAdminPanel;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author jorda
 */

public class cetakDataAdminController {
    private cetakDataAdminPanel view;
    private User user;
    private Profil profil;
    private DashboardClient dashboardClient;
    private JScrollPane contentScrollPane;

    public cetakDataAdminController(cetakDataAdminPanel view, User user, DashboardClient dashboardClient, JScrollPane contentScrollPane) {
        this.view = view;
        this.user = user;
        this.profil = UserDao.getProfil(user);
        this.dashboardClient = dashboardClient;
        this.contentScrollPane = contentScrollPane;
    }

    public void cetakButtonActionPerformed() {
        Document document = new Document(PageSize.A4);
        try {
            String home = System.getProperty("user.home");
            String path = home + "/SiJuara.pdf";

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();

            Dimension panelSize = view.getCetakPanel().getSize();
            float scaleX = (float) PageSize.A4.getWidth() / panelSize.width;
            float scaleY = (float) PageSize.A4.getHeight() / panelSize.height;
            float scale = Math.min(scaleX, scaleY);

            Graphics2D g2 = new PdfGraphics2D(cb, PageSize.A4.getWidth(), PageSize.A4.getHeight());
            g2.scale(scale, scale);
            view.getCetakPanel().print(g2);
            g2.dispose();

            JOptionPane.showMessageDialog(view, "PDF created successfully at: " + path, "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error creating PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            document.close();
        }
    }
    
    public void cetakCSVButtonActionPerformed() {
        TableModel model = view.getJTable().getModel();
        String home = System.getProperty("user.home");
        String path = home + "/SiJuara.csv";

        try (FileWriter csvWriter = new FileWriter(path)) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                csvWriter.write(model.getColumnName(i) + ",");
            }
            csvWriter.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csvWriter.write(model.getValueAt(i, j).toString() + ",");
                }
                csvWriter.write("\n");
            }

            JOptionPane.showMessageDialog(view, "CSV created successfully at: " + path, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error creating CSV: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cetakExcelButtonActionPerformed() {
        TableModel model = view.getJTable().getModel();
        String home = System.getProperty("user.home");
        String path = home + "/SiJuara.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // Create data rows
            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    Object value = model.getValueAt(i, j);

                    if (value instanceof ImageIcon) {
                        // Handle image icon
                        ImageIcon icon = (ImageIcon) value;
                        byte[] imageBytes = view.imageIconToByteArray(icon);
                        int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
                        Drawing<?> drawing = sheet.createDrawingPatriarch();
                        CreationHelper helper = workbook.getCreationHelper();
                        ClientAnchor anchor = helper.createClientAnchor();
                        anchor.setCol1(j);
                        anchor.setRow1(i + 1);
                        org.apache.poi.ss.usermodel.Picture pict = drawing.createPicture(anchor, pictureIdx);
                        pict.resize();
                    } else {
                        // Handle other cell types
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Auto-size columns
            for (int i = 0; i < model.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to file
            try (FileOutputStream fileOut = new FileOutputStream(path)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(view, "Excel created successfully at: " + path, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error creating Excel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    public class cetakButtonListener implements ActionListener {
        private cetakDataAdminPanel panel;

        public cetakButtonListener(cetakDataAdminPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cetakButtonActionPerformed();
        }
    }
    
    public class cetakCSVButtonListener implements ActionListener {
        private cetakDataAdminPanel panel;

        public cetakCSVButtonListener(cetakDataAdminPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cetakCSVButtonActionPerformed();
        }
    }
    
    public class cetakExcelButtonListener implements ActionListener {
        private cetakDataAdminPanel panel;

        public cetakExcelButtonListener(cetakDataAdminPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cetakExcelButtonActionPerformed();
        }
    }
}
