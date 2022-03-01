package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;
    private int paperPick;
    private int rockPick;
    private int scissorsPick;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }




    private ArrayList<Result> countScores(ArrayList<Result> countResults){
        if(!countResults.isEmpty()) {
            Result lastMove = countResults.get(countResults.size() - 1);
            if (lastMove.getWinnerMove() == Move.Paper){
                paperPick++;
            }
            if (lastMove.getWinnerMove() == Move.Rock){
                rockPick++;
            }
            if(lastMove.getWinnerMove() == Move.Scissor){
                scissorsPick++;
            }
        }
        return countResults;
    }
    /**
     * Decides the next move for the bot...
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        Move move = null;
        System.out.println(paperPick);

        if(results.size() < 1 ){
            move = Move.Paper;
        }
        countScores(results);
        return move;
    }

    private Move randomMove(){
        Random randomMove = new Random();
        int i = randomMove.nextInt(4);
        switch (i){
            case 1 -> {
                return Move.Rock;
            }
            case 2 -> {
                return Move.Scissor;
            }
            case 3 -> {
                return Move.Paper;
            }
        }
        return null;
    }
}
