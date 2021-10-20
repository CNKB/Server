package lkd.namsic.cnkb.domain;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
public class UserRole {

    @EmbeddedId
    UserRolePk pk;

}
