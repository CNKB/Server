package lkd.namsic.cnkb.controller;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.Response;
import lkd.namsic.cnkb.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {
    
    private final Config config;
    private final PlayerService playerService;
    
    @PostMapping("/create-player/t")
    public ResponseEntity<Response> createPlayer(HttpServletRequest request,
                                                 @RequestBody Player player) {
        Response response = playerService.createPlayer(request, player);
        log.info("createPlayer - {} {}", response.getStatus(), request.getAttribute("id"));
        return config.getResponseEntity(response);
    }
    
}