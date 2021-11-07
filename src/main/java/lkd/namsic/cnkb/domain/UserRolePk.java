package lkd.namsic.cnkb.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRolePk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
    
    @Override
    public int hashCode() {
        return user.id.hashCode() ^ role.id.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj instanceof UserRolePk pk) {
            return user.id.equals(pk.user.id) &&
                role.id.equals(pk.role.id);
        } else {
            return false;
        }
    }
    
}