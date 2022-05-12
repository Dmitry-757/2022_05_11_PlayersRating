package org.dng;

public interface IGameService {
    public void addPlayer(Player player);
    public Player ChooseWinner();
    public boolean Play();
}
