/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 * @author jorda
 */

public class ImageRenderer extends JLabel implements TableCellRenderer {

    public ImageRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER); // Mengatur gambar di tengah secara horizontal
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) value;
            
            // Mendapatkan lebar dan tinggi sel
            int cellWidth = table.getColumnModel().getColumn(column).getWidth();
            int cellHeight = table.getRowHeight(row);

            // Mengubah ukuran gambar agar sesuai dengan sel
            Image scaledImage = imageIcon.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Mengatur label dengan ikon gambar yang sudah diubah ukurannya
            setIcon(scaledIcon);
        } else {
            setIcon(null);
        }

        // Mengatur tampilan default (warna latar belakang, dll.) sesuai seleksi
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        return this;
    }
}
