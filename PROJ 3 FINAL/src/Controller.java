import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {
	
	/**
	 * Element declarations below for sliders, text fields, buttons, labels, and graph components
	 */
	
    @FXML
    private Slider diameterSlider;

    @FXML
    private Slider wavelengthSlider;

    @FXML
    private Slider distanceSlider;

    @FXML
    private TextField diameterBox;

    @FXML
    private Button diameterApplyButton;

    @FXML
    private TextField wavelengthBox;

    @FXML
    private Button wavelengthApplyButton;

    @FXML
    private TextField distanceBox;

    @FXML
    private Button distanceApplyButton;
    
    @FXML
    private Label diameterLabel;
    
    @FXML
    private Label wavelengthLabel;
    
    @FXML 
    private Label distanceLabel;
    
    @FXML
    private LineChart<Number, Number> intensityGraph;

    @FXML
    private NumberAxis intensityXaxis;

    @FXML
    private NumberAxis intensityYaxis;

    @FXML
    private Circle circle;

    @FXML
    private Circle diffractionCircle;


    /**
     * when distance slider is set by user, the corresponding label is updated to display value specified 
     * @param event
     */
    
    @FXML
    void getDistance(MouseEvent event) {
        //this.mmScreenDistance = distanceSlider.getValue();
    	String distanceBoxValue = String.valueOf(distanceSlider.getValue());
    	distanceLabel.setText(distanceBoxValue);
        update();
    }

    /**
     * when diameter slider is set by user, the corresponding label is updated to display value specified 
     * @param event
     */
    
    @FXML
    void getHoleDiameter(MouseEvent event) {
        //this.mmDiameter = diameterSlider.getValue();
    	String diameterBoxValue = String.valueOf(diameterSlider.getValue());
    	diameterLabel.setText(diameterBoxValue);
        update();
    }

    /**
     * when wavelength slider is set by user, the corresponding label is updated to display value specified 
     * @param event
     */

    @FXML
    void getWavelength(MouseEvent event) {
        String wavelengthBoxValue = String.valueOf(wavelengthSlider.getValue());
        wavelengthLabel.setText(wavelengthBoxValue + " nm");
        if(wavelengthSlider.getValue() >= 400 && wavelengthSlider.getValue() <= 420) {
            circle.setFill(Color.VIOLET);
            diffractionCircle.setFill(Color.VIOLET);
        }
        else if(wavelengthSlider.getValue()> 420 && wavelengthSlider.getValue() <= 440) {
            circle.setFill(Color.INDIGO);
            diffractionCircle.setFill(Color.INDIGO);
        }
        else if(wavelengthSlider.getValue()> 440 && wavelengthSlider.getValue() <= 490) {
            circle.setFill(Color.BLUE);
            diffractionCircle.setFill(Color.BLUE);
        }
        else if(wavelengthSlider.getValue()> 490 && wavelengthSlider.getValue() <= 570) {
            circle.setFill(Color.GREEN);
            diffractionCircle.setFill(Color.GREEN);
        }
        else if(wavelengthSlider.getValue()> 570 && wavelengthSlider.getValue() <= 585) {
            circle.setFill(Color.YELLOW);
            diffractionCircle.setFill(Color.YELLOW);
        }
        else if(wavelengthSlider.getValue()> 585 && wavelengthSlider.getValue() <= 620) {
            circle.setFill(Color.ORANGE);
            diffractionCircle.setFill(Color.ORANGE);
        }
        else if(wavelengthSlider.getValue()> 620 && wavelengthSlider.getValue() <= 700) {
            circle.setFill(Color.RED);
            diffractionCircle.setFill(Color.RED);
        }
        update();
    }
    
    /** 
     * when diameter Apply button is clicked, if value in text box is within the appropriate range, the corresponding slider and label are updated to match the entered value.
     * @param event
     */
    
    @FXML
    void setDiameterSlider(MouseEvent event) {
    	String diameterBoxValue = diameterBox.getText();
    	double newDiameterSliderValue = Double.parseDouble(diameterBoxValue);
    	
    	if(newDiameterSliderValue <= 3 && newDiameterSliderValue >= 0.5) {
    		diameterSlider.setValue(newDiameterSliderValue);
    		diameterLabel.setText(diameterBoxValue);
    	}
        update();
    }

    /** 
     * when distance Apply button is clicked, if value in text box is within the appropriate range, the corresponding slider and label are updated to match the entered value.
     * @param event
     */
    
    @FXML
    void setDistanceSlider(MouseEvent event) {
    	String distanceBoxValue = distanceBox.getText();
    	double newDistanceSliderValue = Double.parseDouble(distanceBoxValue);
    	
    	if(newDistanceSliderValue <= 1000 && newDistanceSliderValue >= 500) {
    		distanceSlider.setValue(newDistanceSliderValue);
    		distanceLabel.setText(distanceBoxValue);
    	}
        update();
    }

    /** 
     * when wavelength Apply button is clicked, if value in text box is within the appropriate range, the corresponding slider and label are updated to match the entered value.
     * @param event
     */
    
    @FXML
    void setWavelengthSlider(MouseEvent event) {
    	String wavelengthBoxValue = wavelengthBox.getText();
    	double newWavelengthSliderValue = Double.parseDouble(wavelengthBoxValue);
    	
    	if(newWavelengthSliderValue <= 700 && newWavelengthSliderValue >= 400) {
    		wavelengthSlider.setValue(newWavelengthSliderValue);
    		wavelengthLabel.setText(wavelengthBoxValue);
    	}
    	update();
    }

    private void update() {
        CircleDiffraction circle = new CircleDiffraction(diameterSlider.getValue(), wavelengthSlider.getValue(), distanceSlider.getValue());
        plotIntensity(circle);
    }


    private void plotIntensity(CircleDiffraction circle){

       intensityGraph.getData().clear();
       XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();

        for(double x = -0.0015; x <= 0.0015; x += 0.000005) {
            series.getData().add(new XYChart.Data<Number,Number>(x, circle.calculateIntensity(x)));
        }
        intensityGraph.getData().add(series);
    }

    public void initialize(){
        wavelengthSlider.setValue(500);
        wavelengthBox.setText("500");
        diameterSlider.setValue(3);
        diameterBox.setText("3");
        distanceSlider.setValue(600);
        distanceBox.setText("600");
        update();
    }
}

