import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class CircleDiffraction {

    /**
     * The Mm diameter.
     */
    private double mmDiameter = 0.0005;
    /**
     * The Mm wavelength.
     */
    private double mmWavelength = 0.000000633;
    /**
     * The Mm screen distance.
     */
    private double mmScreenDistance = 0.8;
    /**
     * The Bessel zeroes.
     */
    private final double[] besselZeroes = {3.8317, 7.0156, 10.1735, 13.3237, 16.4706};

    public CircleDiffraction() {}

    /**
     * constructor sets up the experiment
     *
     * @param mmDiameter       diameter of circular aperture in millimeters
     * @param mmWavelength     diameter of wavelength in nanometers
     * @param mmScreenDistance distance from aperture to screen in millimeters
     */
    public CircleDiffraction(double mmDiameter, double mmWavelength, double mmScreenDistance){
        this.mmDiameter = mmDiameter;
        this.mmWavelength = mmWavelength;
        this.mmScreenDistance = mmScreenDistance;
    }

    public double calculateIntensity(double angle) {
    	if(angle == 0)
        	return 1;
        double beta = (Math.PI * this.mmDiameter*Math.pow(10, -3) * Math.sin(angle)) / (this.mmWavelength*Math.pow(10, -9));
        double intensity = Math.pow(((2 * SpecialFunction.j1(beta)) / beta), 2);
        return intensity;
    }

    /**
     * computes the diffraction angle of the nth order light minima
     *
     * @param nthDiffractionOrder int - order of diffraction (1 being the Airy disk)
     * @return double - angle of diffraction in radians
     */
    public double calculateM(int nthDiffractionOrder) {
        if(nthDiffractionOrder < 1 || nthDiffractionOrder >5){
            throw new IndexOutOfBoundsException("Only diffraction orders 1-5 supported");
        }

        double factor = besselZeroes[nthDiffractionOrder - 1] / Math.PI;
        return (factor * mmWavelength)/ mmDiameter;
    }

    /**
     * getter for diameter
     *
     * @return diameter in millimeters
     */
    public double getMmDiameter() {
        return mmDiameter;
    }

    /**
     * setter for diameter
     *
     * @param mmDiameter diameter in millimeters
     */
    public void setMmDiameter(double mmDiameter) {
        this.mmDiameter = mmDiameter;
    }


    /**
     * getter for wavelength
     *
     * @return wavlength in milimeters
     */
    public double getMmWavelength() {
        return mmWavelength;
    }

    /**
     * setter for wavelength
     *
     * @param mmWavelength in millimeters
     */
    public void setMmWavelength(double mmWavelength) {
        this.mmWavelength = mmWavelength;
    }


    /**
     * getter for distance from aperture to screen
     *
     * @return distance in millimeters
     */
    public double getMmScreenDistance() {
        return mmScreenDistance;
    }


    /**
     * setter for distance from aperture to screen
     *
     * @param mmScreenDistance distance in millimeters
     */
    public void setMmScreenDistance(double mmScreenDistance) {
        this.mmScreenDistance = mmScreenDistance;
    }

}
