package unq.pconc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		int    alto             = Integer.parseInt(args[0]); 
		int    ancho            = Integer.parseInt(args[1]);
		
		double x_inicial        = Double.parseDouble(args[2]);
		double y_inicial        = Double.parseDouble(args[3]); 
		double x_rango          = Double.parseDouble(args[4]);
		double y_rango          = Double.parseDouble(args[5]);
		
		int    cant_iteraciones = Integer.parseInt(args[6]);
		int    cant_threads     = Integer.parseInt(args[7]); 
		int    tamano_buffer    = Integer.parseInt(args[8]);
		
		int[][] colorsToPaint = setColorsToPaint(cant_iteraciones);
		
		BufferedImage bi      = new BufferedImage (ancho, alto, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = bi.getRaster();
		
		ThreadPool threadPool = new ThreadPool(cant_threads, alto, tamano_buffer);

        for (int i = 0; i < alto; i++) {
            threadPool.launch(new MandelbrotTask(i, x_inicial, y_inicial, x_rango, ancho, cant_iteraciones, colorsToPaint, raster, alto, y_rango));
        }

        threadPool.stop();
        
        createImageResult(bi);
        
        long finishTime = System.currentTimeMillis();
        long resultTime = (finishTime - startTime) / 10;
        
        System.out.println("Tiempo de ejecucion: " + resultTime);
	}
	
	private static int[][] setColorsToPaint(int cant_iteraciones) {
		int [] r = new int[cant_iteraciones + 1];
		int [] g = new int[cant_iteraciones + 1];
		int [] b = new int[cant_iteraciones + 1];
		
		r[cant_iteraciones] = 0;
		g[cant_iteraciones] = 0;
		b[cant_iteraciones] = 0;
		
		for (int i =0; i < cant_iteraciones ; i ++) {
			
			int argb = Color.HSBtoRGB( i /256f , 1 , 1);
			r [ i ] = argb >> 16;
			g [ i ] = ( argb >> 8) & 255;
			b [ i ] = argb & 255;
			
		}
		
		return new int[][] {r, g, b};
	}

	private static void createImageResult(BufferedImage bi) {
		File outputfile = new File ("salida.png");
        try {
        	ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
	        e.printStackTrace();
        }
	}
}





