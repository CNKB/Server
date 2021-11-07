package lkd.namsic.cnkb.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserRole {
    
    @EmbeddedId
    UserRolePk pk;
    
}