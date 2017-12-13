package project.othello.breton.viewFx;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import project.othello.breton.model.OthelloImpl;

/**
 * This class is used to creates and manage the scene where the line charts for 
 * the scores are displayed. 
 * 
 * @author G43397
 */
public class ChartsScoresLayout extends BorderPane {

    private final LineChart chartScores;

    private XYChart.Series blackSeries;
    private XYChart.Series whiteSeries;
    
    public ChartsScoresLayout() {
        super();
        makeBlackSeries();
        makeWhiteSeries();
       
        chartScores = makeChartScores();
        this.setCenter(chartScores);
    }

    private LineChart makeChartScores() {
        NumberAxis xAxis = makeAxis("Evolution of the game (percent)", 100, 10);
        NumberAxis yAxis = makeAxis("Score", 64, 4);
        
        LineChart chart = new LineChart(xAxis, yAxis);
        chart.setTitle("Scores Evolution");
        chart.getData().addAll(blackSeries, whiteSeries);
        
        return chart;
    }
    
    private void makeBlackSeries() {
        blackSeries = new XYChart.Series();
        blackSeries.setName("Score Black");
        blackSeries.getData().add(new XYChart.Data(0, 2)); //0 is the percentage of 
        //evolution, 2 is the
        //initial score.
    }
    
    private void makeWhiteSeries() {
        whiteSeries = new XYChart.Series();
        whiteSeries.setName("Score White");
        whiteSeries.getData().add(new XYChart.Data(0, 2));
        //0 is the percentage of 
        //evolution, 2 is the
        //initial score.
    }

    private NumberAxis makeAxis(String label, int max, int range) {
        NumberAxis axis = new NumberAxis(0, max, range);
        axis.setLabel(label);
        return axis;
    }
    
    private double getEvolutionOfGame(OthelloImpl game) {
        return ((double) game.getCounterPawnsOnBoard() / 64) * 100;
    }     
    
    void refresh(OthelloImpl game) {
        blackSeries.getData().add(new XYChart.Data(
                                  getEvolutionOfGame(game), 
                                  game.getScoreBlack()));

        whiteSeries.getData().add(new XYChart.Data(
                                  getEvolutionOfGame(game), 
                                  game.getScoreWhite()));
    }
}
