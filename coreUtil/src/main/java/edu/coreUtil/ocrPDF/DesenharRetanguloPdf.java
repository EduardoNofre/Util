package edu.coreUtil.ocrPDF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;

public class DesenharRetanguloPdf extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<Rectangle2D> rectList = new ArrayList<Rectangle2D>();

	private static final Color RECT_DRAW_COLOR = Color.blue.darker();

	private static final Color DRAGGING_RECT_COLOR = Color.pink;

	private Rectangle2D draggingRect = null;

	BufferedImage image = null;

	PDDocument document = null;

	Dimension size = new Dimension();

	Image imagem = null;

	int indexPagina = 0;

	private JPanel jPanel1 = null;

	protected DesenharRetanguloPdf() {
	}

	protected DesenharRetanguloPdf(BufferedImage image, PDDocument document, int indexPagina) {

		this.image = image;

		this.document = document;

		this.indexPagina = indexPagina;

		size.setSize(image.getWidth(), image.getHeight());

		MouseAdapter mouseAdapter = new DesenharRetanguloPdf.MyMouseAdapter();

		addMouseListener(mouseAdapter);

		addMouseMotionListener(mouseAdapter);

		initComponents();
	}

	protected void paintComponent(Graphics g){

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		int x = (getWidth() - size.width) / 2;

		int y = (getHeight() - size.height) / 2;

		g.drawImage(image, x, y, this);

		for (Rectangle2D rect : rectList) {

			g2.setBackground(new Color(255, 255, 255, 0));

			g2.setColor(new Color(255, 255, 255, 0));

			g2.fill(rect);

			g2.setColor(RECT_DRAW_COLOR);

			g2.draw(rect);
		}
		if (draggingRect != null) {

			g2.setColor(DRAGGING_RECT_COLOR);

			g2.draw(draggingRect);

		}

	}

	protected void addRectangle(Rectangle2D r){
		rectList.add(r);
	}

	public Dimension getPreferredSize(){

		return size;
	}

	protected void setDraggingRect(Rectangle2D r){
		draggingRect = r;
	}

	private Point start = null;

	protected void mousePressed(MouseEvent e){
		start = e.getPoint();
	}

	private class MyMouseAdapter extends MouseAdapter {

		private Point start = null;

		@Override
		public void mousePressed(MouseEvent e){
			start = e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e){

			PdfUtil pdfUtil = new PdfUtil();

			int larguraRect = (int) draggingRect.getWidth();

			int AlturaRect = (int) draggingRect.getHeight();

			int x = (int) draggingRect.getX();

			int y = (int) draggingRect.getY();

			try {

				System.out.println(
						"X= " + x + " Y= " + y + " L= " + larguraRect + " A= " + AlturaRect + " Pagina= " + indexPagina);

				pdfUtil.coordenadasPdf(x, y, larguraRect, AlturaRect, document, indexPagina);

			} catch (IOException e1) {

				e1.printStackTrace();
			}

		}

		@Override
		public void mouseDragged(MouseEvent e){

			setDraggingRect(createRect2D(e));

			DesenharRetanguloPdf.this.repaint();

		}

		protected Rectangle2D createRect2D(MouseEvent e){

			Point end = e.getPoint();

			int width = Math.abs(start.x - end.x);

			int height = Math.abs(start.y - end.y);

			int x = Math.min(start.x, end.x);

			int y = Math.min(start.y, end.y);

			Rectangle2D r = new Rectangle2D.Double(x, y, width, height);

			return r;
		}
	}

	protected void initComponents(){

		jPanel1 = new javax.swing.JPanel();

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);

		jPanel1.setLayout(jPanel1Layout);

		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 100, Short.MAX_VALUE));

		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 100, Short.MAX_VALUE));

		setBackground(new java.awt.Color(204, 204, 204));

		setAutoscrolls(true);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 900, Short.MAX_VALUE));

		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 980, Short.MAX_VALUE));
	}

}
