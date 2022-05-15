package org.dng;

public interface IGameService {
    public boolean addPlayer(Player player);
    public Player ChooseWinner();
    public boolean Play();
}
