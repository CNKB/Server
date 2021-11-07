package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.Response;

import javax.servlet.http.HttpServletRequest;

public interface PlayerService {

    Response createPlayer(HttpServletRequest request, Player player);

}
