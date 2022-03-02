package rps.bll.player;

//Project imports

import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;

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
    int rockPick = 0;
    int paperPick = 0;
    int scissorPick = 0;
    float rockPercent = 0;
    float paperPercent = 0;
    float scissorPercent = 0;
    float counterMovePercent = 0;

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


    /**
     * Decides the next move for the bot...
     *
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();

        for (Result r : results) {
            if (r.getWinnerPlayer().getPlayerType() == PlayerType.Human && r.getType() != ResultType.Tie) {
                if (r.getWinnerMove() == Move.Rock) {
                    rockPick++;
                }
            }
            if (r.getWinnerPlayer().getPlayerType() == PlayerType.Human && r.getType() != ResultType.Tie) {
                if (r.getWinnerMove() == Move.Paper) {
                    paperPick++;
                }
            }
            if (r.getWinnerPlayer().getPlayerType() == PlayerType.Human && r.getType() != ResultType.Tie) {
                if (r.getWinnerMove() == Move.Scissor) {
                    paperPick++;
                }
            }
        }

        Move move = randomMove();
        Move counterMove = counterFavoriteMove(paperPick, scissorPick, rockPick);

        Random random = new Random();
        if (counterMove != null && random.nextInt(100) <40) {
            move = counterMove;
        }

        return move;
    }

    private Move counterFavoriteMove(int paperPick, int scissorPick, int rockPick) {
        Move move = null;
        float total = rockPick + paperPick + scissorPick;
        rockPercent = rockPick / total * 100;
        paperPercent = paperPick / total * 100;
        scissorPercent = scissorPick / total * 100;


        if (rockPercent > paperPercent + scissorPercent) {
            move = Move.Paper;
        }
        if (paperPercent > rockPercent + scissorPercent) {
            move = Move.Scissor;
        }
        if (scissorPercent > paperPercent + rockPercent) {
            move = Move.Rock;
        }
        return move;
    }

    private Move randomMove() {
        Random randomMove = new Random();
        int i = randomMove.nextInt(3);
        switch (i) {
            case 0 -> {
                return Move.Rock;
            }
            case 1 -> {
                return Move.Scissor;
            }
            case 2 -> {
                return Move.Paper;
            }
        }
        return null;
    }
}
