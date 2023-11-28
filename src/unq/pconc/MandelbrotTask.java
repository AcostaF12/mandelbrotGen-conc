package unq.pconc;

import java.awt.image.WritableRaster;

public class MandelbrotTask extends Task {

	private int            y;
	private double         x_inicial;
	private double         y_inicial;
	private double         x_rango;
	private double         y_rango;
	private int            ancho;
	private int            alto;
	private int            cant_iteraciones;
	private int[][]        colorsToPaint;
	private WritableRaster raster;

    public MandelbrotTask(int y, double x_inicial, double y_inicial, double x_rango, 
    			          int ancho, int cant_iteraciones, int[][] colorsToPaint, 
    			          WritableRaster raster, int alto, double y_rango) {
        super();
        this.y                = y;
        this.x_inicial        = x_inicial;
        this.y_inicial        = y_inicial;
        this.x_rango          = x_rango;
        this.y_rango          = y_rango;
        this.ancho            = ancho;
        this.alto             = alto;
        this.cant_iteraciones = cant_iteraciones;
        this.colorsToPaint    = colorsToPaint;
        this.raster           = raster;
    }

    @Override
    public void run() {    	
    	for (int x = 0; x < ancho; x++) {
    		pintarPixelActual(x);
        }
    } 

	private void pintarPixelActual(int x) {
		
		double parteReal       = 0;
        double parteImaginaria = 0; 
        
        double constanteParteReal       = x_inicial + x * (x_rango / ancho);
        double constanteParteImaginaria = y_inicial + y * (y_rango / alto);
        
        int iteracionActual  = 0;
        
        while (esParteDelConjunto(parteReal, parteImaginaria) && iteracionActual < cant_iteraciones) {
        	
            double temporal = parteReal * parteReal - parteImaginaria * parteImaginaria + constanteParteReal;
            
            parteImaginaria = 2.0 * parteReal * parteImaginaria + constanteParteImaginaria;
            parteReal       = temporal;
            
            iteracionActual++;
        }
		
		pintar(x, y, iteracionActual);
		
	}
	
	private boolean esParteDelConjunto(double parteReal, double parteImaginaria) {
		return parteReal * parteReal + parteImaginaria * parteImaginaria < 4;
	}
	
	private void pintar(int x, int y, int numIteraciones) {
		
		int[] colorToPaint = new int[3];
	
		if( numIteraciones > 4 ) { 
			colorToPaint[0] = this.colorsToPaint[0][numIteraciones];
	        colorToPaint[1] = this.colorsToPaint[1][numIteraciones];
	        colorToPaint[2] = this.colorsToPaint[2][numIteraciones];
		} else {
			colorToPaint[0] = this.colorsToPaint[0][0];
	        colorToPaint[1] = this.colorsToPaint[1][0];
	        colorToPaint[2] = this.colorsToPaint[2][0];
		}
		
	    raster.setPixel(x, y, colorToPaint);
	    
	}
	
}