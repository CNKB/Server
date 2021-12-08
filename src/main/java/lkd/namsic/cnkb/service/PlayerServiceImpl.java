package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.base.Location;
import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.domain.game.map.GameMap;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.domain.game.player.PlayerTitle;
import lkd.namsic.cnkb.dto.Response;
import lkd.namsic.cnkb.exception.StatusException;
import lkd.namsic.cnkb.repository.GameMapRepository;
import lkd.namsic.cnkb.repository.PlayerRepository;
import lkd.namsic.cnkb.repository.PlayerTitleRepository;
import lkd.namsic.cnkb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    
    private final Config config;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final PlayerTitleRepository playerTitleRepository;
    private final GameMapRepository gameMapRepository;
    
    @Override
    public Response createPlayer(HttpServletRequest request, Player player) {
        return config.safeCall("createPlayer", () -> {
            String name = player.getName();
            
            if(name == null) {
                throw new StatusException(412, "Requires name");
            }
            
            if(name.length() < 2 || name.length() > 16) {
                throw new StatusException(406, "Invalid length - 2,16");
            }
            
            String replacedName = name.replace(config.REGEX, "");
            if(!name.equals(replacedName)) {
                throw new StatusException(406, "Invalid regex - " + replacedName);
            }
            
            for(String invalidWord : config.INVALID_WORD_LIST) {
                if(name.contains(invalidWord)) {
                    throw new StatusException(406, "Invalid word - " + invalidWord);
                }
            }
            
            long userId = Long.parseLong((String) request.getAttribute("id"));
            
            List<Player> playerList = playerRepository.findAllByUserId(userId);
            if(playerList.size() >= config.MAX_PLAYER_COUNT) {
                throw new StatusException(400, "Player count reached max(" + config.MAX_PLAYER_COUNT + ")");
            }
            
            User user = userRepository.findById(userId).orElseThrow(
                () -> new StatusException(409, "Unknown user")
            );
    
            GameMap gameMap = gameMapRepository.findById(Location.toHex(1, 1))
                .orElseThrow(RuntimeException::new);
            
            Player createdPlayer = Player.builder()
                .user(user)
                .name(name)
                .gameMap(gameMap)
                .baseGameMap(gameMap)
                .build();
            createdPlayer = playerRepository.save(createdPlayer);
            
            playerTitleRepository.save(
                PlayerTitle.builder()
                    .player(createdPlayer)
                    .title("초심자")
                    .build()
            );
            
            return Response.builder()
                .data(createdPlayer.getId())
                .build();
        });
    }
}