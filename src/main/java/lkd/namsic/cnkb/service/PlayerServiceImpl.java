package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.Config;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.domain.game.player.PlayerTitle;
import lkd.namsic.cnkb.dto.response.Response;
import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.repository.PlayerRepository;
import lkd.namsic.cnkb.repository.PlayerTitleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    Config config;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerTitleRepository playerTitleRepository;

    @Override
    public Response createPlayer(HttpServletRequest request, Player player) {
        return config.safeCall("createPlayer", () -> {
            String name = player.getName();

            if(name == null) {
                throw new CommonException(412, "Requires name");
            }

            if(name.length() < 2 || name.length() > 16) {
                throw new CommonException(406, "Invalid name length - 2,16");
            }

            String replacedName = name.replace(name, config.REGEX);
            if(!name.equals(replacedName)) {
                throw new CommonException(406, "Invalid regex name - " + replacedName);
            }

            for(String invalidWord : config.INVALID_WORD_LIST) {
                if(name.contains(invalidWord)) {
                    throw new CommonException(406, "Contains invalid word - " + invalidWord);
                }
            }

            long userId = Long.parseLong((String) request.getAttribute("id"));

            List<Player> playerList = playerRepository.findAllByUserId(userId);
            if(playerList.size() >= config.MAX_PLAYER_COUNT) {
                throw new CommonException(400,
                        "Player count reached max(" + config.MAX_PLAYER_COUNT + ")");
            }

            Player createdPlayer = Player.builder().name(name).build();
            createdPlayer = playerRepository.save(createdPlayer);

            playerTitleRepository.save(
                    PlayerTitle.builder()
                            .player(player)
                            .title("초심자")
                            .build()
            );

            return Response.builder()
                    .data(createdPlayer.getId())
                    .build();
        });
    }
}
